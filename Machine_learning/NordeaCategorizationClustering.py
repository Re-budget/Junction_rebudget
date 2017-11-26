import string
import collections
from nltk import word_tokenize
from nltk.stem import PorterStemmer
from nltk.stem.snowball import FinnishStemmer
from nltk.corpus import stopwords
from sklearn.cluster import KMeans
from sklearn.feature_extraction.text import TfidfVectorizer
import string,re
from pprint import pprint
import json
 
 
def process_text(text, stem=True):
    """ Tokenize text and stem words removing punctuation """
    text = ''.join([i for i in text if not i.isdigit()])
    text = text.translate(None, string.punctuation)
    tokens = word_tokenize(text)
    regions = ['espoo','helsinki','vantaa', 'roma', 'london', 'riga', 'tampere', 'oy', 'haaga', 'india', 'pohjois']

    if stem:
        stemmer = PorterStemmer()
        tokens = [stemmer.stem(t) for t in tokens]
        filtered_words = [word for word in tokens if word not in stopwords.words('finnish')] 
        filtered_words = [word for word in tokens if word not in stopwords.words('english')] 
    else:
        text = text.lower()
        tokens = text.split()
        #filtered_words = [word for word in tokens if word not in stopwords.words('finnish')] 
        #filtered_words = [word for word in tokens if word not in stopwords.words('english')] 
        filtered_words = [word for word in tokens if word not in regions]
        
    return filtered_words
 
 
def cluster_texts(texts, clusters):
    """ Transform texts to Tf-Idf coordinates and cluster texts using K-Means """
    vectorizer = TfidfVectorizer(lowercase=True,max_df=0.4,min_df=1)
 
    tfidf_model = vectorizer.fit_transform(texts)
    km_model = KMeans(n_clusters=clusters,init='k-means++', max_iter=100, n_init=1)
    km_model.fit(tfidf_model)
 
    clustering = collections.defaultdict(list)
 
    for idx, label in enumerate(km_model.labels_):
        clustering[label].append(idx) 
    
    print("\n\n\nTop terms per cluster:")
    order_centroids = km_model.cluster_centers_.argsort()[:, ::-1]
    terms = vectorizer.get_feature_names()
    for i in range(clusters):
        print("Cluster %d:" % i),
        for ind in order_centroids[i, :10]:
            print(' %s' % terms[ind]),
        print "\n"
    

    #Prediction
    Y = vectorizer.transform(["supermarket"])
    prediction = km_model.predict(Y)
    #print("Prediction: "+str(prediction))
    
    Y = vectorizer.transform(texts)
    p2 = km_model.predict(Y)
    #print("Prediction 2: "+str(p2))
    
    return p2

  
 
 
if __name__ == "__main__":
    data = pd.read_csv('/Users/omershafiq/Desktop/NordeaChallange/Data/data/sdata.csv', encoding="utf-8")
    articles = []
    tokens = []
    for obj in data['place']:
        discription = str(obj)
        token = process_text(discription,False)
        tokens.append(token)
        articles.append(' '.join(token))
    
    clusters = cluster_texts(articles, 7)
    results = pd.DataFrame({'cluster': clusters,'result': articles})
    for index, row in results.iterrows():
        print row['cluster'], row['result']
    print("\n")
    #pprint(dict(clusters))
    
    

 
    
