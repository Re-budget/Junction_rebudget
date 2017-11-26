import pandas as pd
import datetime
import matplotlib.pyplot as plt
import matplotlib.cm as cm
import numpy as np
import mpld3
import plotly.plotly as py
import plotly.graph_objs as go


df = pd.read_csv('/Users/omershafiq/Desktop/NordeaChallange/Data/data/sdata.csv', encoding="utf-8")

df['place'] = df['place'].str.lower()
df['amount'] = df['amount'].str.replace(',','.')
df['amount'] = pd.to_numeric(df['amount'])



tf = df.groupby('place', as_index=False)['amount'].agg(['mean', 'count']).reset_index()



N=len(tf['place'])
labels=tf['place']
x = tf['count']
y = tf['mean']
size = tf['count']*100

# Choose some random colors
colors=cm.rainbow(np.random.rand(N))

# Use those colors as the color argument
plt.scatter(x,y,s=size,color=colors)
for i in range(N):
    plt.annotate(labels[i],xy=(x[i],y[i]))
plt.xlabel('Number of transactions')
plt.ylabel('Money Spent')

# Move title up with the "y" option
plt.title('Customer Spending Categories')
plt.show()
