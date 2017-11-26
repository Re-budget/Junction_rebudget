import pandas as pd
import numpy as np
import matplotlib.pylab as plt
from matplotlib.pylab import rcParams
from statsmodels.tsa.stattools import adfuller
from statsmodels.tsa.seasonal import seasonal_decompose
from statsmodels.tsa.stattools import acf, pacf
from statsmodels.tsa.arima_model import ARIMA

path = '/Users/omershafiq/Desktop/NordeaChallange/Data/data/sdata.csv'
data = pd.read_csv(path, delimiter=',')
data = pd.concat([data['date'],data['amount']], axis=1, keys=['date', 'amount'])
#data.to_csv('/Users/omershafiq/Desktop/NordeaChallange/Data/current/trans2.csv', index = False)

data.dtypes
data['amount'] = data['amount'].str.replace(',','.')
data['date'] = pd.to_datetime(data['date'], format="%d/%m/%Y").dt.date
data['amount'] = pd.to_numeric(data['amount'])
data.dtypes
#data.set_index('date', inplace=True)

#remove credit transactions +ve ones (Money Received)
data2 = data[data['amount'] < 0]

#remove debit outliers
data2 = data2[data2['amount'] > -900]

#new_dates = []
#for index, row in data2.iterrows():
#    new_dates.append(last_day_of_month(row['date']))
#data2['date'] = new_dates


#combine and form daily money consumption to create distinct dates in index
result = data2.groupby('date').sum()



#Removed day element from data


#Experimental thingi
result['amount'] = abs(result['amount'])


#-----------------
idx = pd.date_range('08-01-2017', '11-21-2017')

ts = pd.Series(result['amount'].values, index=result.index)
ts.index = pd.DatetimeIndex(ts.index)

s = ts.reindex(idx, fill_value=1) #added zeros to non-transactional days
#------------------


#s = pd.Series(result['amount'].values, index=result.index)
#s.index = pd.DatetimeIndex(s.index)

test_stationarity(s)




#Estimating & Eliminating Trend
#==================================

#Log transformation (we can also try sqrt)
ts_log = np.log(s)
#plt.plot(ts_log)
test_stationarity(ts_log)




#Moving Average [WINNER]
moving_avg = pd.rolling_mean(ts_log,1)
#plt.plot(ts_log)
#plt.plot(moving_avg, color='red')
ts_log_moving_avg_diff = ts_log - moving_avg
#ts_log_moving_avg_diff.head(7)
ts_log_moving_avg_diff.dropna(inplace=True)
test_stationarity(ts_log_moving_avg_diff)




#Exponential Weighted Avg diff
expwighted_avg = pd.ewma(ts_log, halflife=7)
#plt.plot(ts_log)
#plt.plot(expwighted_avg, color='red')
ts_log_ewma_diff = ts_log - expwighted_avg
test_stationarity(ts_log_ewma_diff)



#Eliminating Trend and Seasonality
#=================================

#Differencing
ts_log_diff = ts_log - ts_log.shift(periods=1)
#plt.plot(ts_log_diff)
ts_log_diff.dropna(inplace=True)
test_stationarity(ts_log_diff)

#Decomposing
decomposition = seasonal_decompose(ts_log)

trend = decomposition.trend
seasonal = decomposition.seasonal
residual = decomposition.resid

'''plt.subplot(411)
plt.plot(ts_log, label='Original')
plt.legend(loc='best')
plt.subplot(412)
plt.plot(trend, label='Trend')
plt.legend(loc='best')
plt.subplot(413)
plt.plot(seasonal,label='Seasonality')
plt.legend(loc='best')
plt.subplot(414)
plt.plot(residual, label='Residuals')
plt.legend(loc='best')
plt.tight_layout() '''

ts_log_decompose = residual
ts_log_decompose.dropna(inplace=True)
test_stationarity(ts_log_decompose)




#Set Winner
#===========

#winner = ts_log_moving_avg_diff




#Forcasting Time Series
#=========================

#For Finding p and q for confidance interval
#Using data from differencing we can also try data from Moving Average
lag_acf = acf(ts_log_diff, nlags=7)
lag_pacf = pacf(ts_log_diff, nlags=7, method='ols')

#Plot ACF: 
plt.subplot(121) 
plt.plot(lag_acf)
plt.axhline(y=0,linestyle='--',color='gray')
plt.axhline(y=-1.96/np.sqrt(len(ts_log_diff)),linestyle='--',color='gray')
plt.axhline(y=1.96/np.sqrt(len(ts_log_diff)),linestyle='--',color='gray')
plt.title('Autocorrelation Function')

#Plot PACF:
plt.subplot(122)
plt.plot(lag_pacf)
plt.axhline(y=0,linestyle='--',color='gray')
plt.axhline(y=-1.96/np.sqrt(len(ts_log_diff)),linestyle='--',color='gray')
plt.axhline(y=1.96/np.sqrt(len(ts_log_diff)),linestyle='--',color='gray')
plt.title('Partial Autocorrelation Function')
plt.tight_layout()


#AR Model
model = ARIMA(ts_log, order=(2, 1, 0))  
results_AR = model.fit(disp=-1)  
plt.plot(ts_log_diff)
plt.plot(results_AR.fittedvalues, color='red')
plt.title('RSS: %.4f'% sum((results_AR.fittedvalues-ts_log_diff)**2))


#MA Model
model = ARIMA(ts_log, order=(0, 1, 2))  
results_MA = model.fit(disp=-1)  
plt.plot(ts_log_diff)
plt.plot(results_MA.fittedvalues, color='red')
plt.title('RSS: %.4f'% sum((results_MA.fittedvalues-ts_log_diff)**2))


#Combined AR & MA

model = ARIMA(ts_log, order=(2, 1, 2))  
results_ARIMA = model.fit(disp=-1)  
plt.plot(ts_log_diff)
plt.plot(results_ARIMA.fittedvalues, color='red')
plt.title('RSS: %.4f'% sum((results_ARIMA.fittedvalues-ts_log_diff)**2))


#Take back to orginal scale for Combined Model

predictions_ARIMA_diff = pd.Series(results_ARIMA.fittedvalues, copy=True)
print predictions_ARIMA_diff.head()

predictions_ARIMA_diff_cumsum = predictions_ARIMA_diff.cumsum()
print predictions_ARIMA_diff_cumsum.head()

predictions_ARIMA_log = pd.Series(ts_log.ix[0], index=ts_log.index)
predictions_ARIMA_log = predictions_ARIMA_log.add(predictions_ARIMA_diff_cumsum,fill_value=0)
predictions_ARIMA_log.head()


predictions_ARIMA = np.exp(predictions_ARIMA_log)
plt.plot(s)
plt.plot(predictions_ARIMA)
plt.title('RMSE: %.4f'% np.sqrt(sum((predictions_ARIMA-s)**2)/len(s)))





def test_stationarity(timeseries):
    
    #Determing rolling statistics
    rolmean = pd.rolling_mean(timeseries, window=12)
    rolstd = pd.rolling_std(timeseries, window=12)
    #rolstd = pd.rolling_var(timeseries, window=12)

    #Plot rolling statistics:
    orig = plt.plot(timeseries, color='blue',label='Original')
    mean = plt.plot(rolmean, color='red', label='Rolling Mean')
    std = plt.plot(rolstd, color='black', label = 'Rolling Std')
    plt.legend(loc='best')
    plt.title('Rolling Mean & Standard Deviation')
    plt.show(block=False)
    
    #Perform Dickey-Fuller test:
    print 'Results of Dickey-Fuller Test:'
    dftest = adfuller(timeseries, 1)
    dfoutput = pd.Series(dftest[0:4], index=['Test Statistic','p-value','#Lags Used','Number of Observations Used'])
    for key,value in dftest[4].items():
        dfoutput['Critical Value (%s)'%key] = value
    print dfoutput
 
    
def last_day_of_month(any_day):
    next_month = any_day.replace(day=28) + datetime.timedelta(days=4)  # this will never fail
    return next_month - datetime.timedelta(days=next_month.day)
    
    