
import csv
import sys
import math
import enthought
import matplotlib.pyplot as plt
import scipy.stats.stats 
from scipy.stats.stats import pearsonr


# This program plots the correlations between structural features
# and personality traits.


# Initialization

personalityFileName = sys.argv[1]
featureFileName = sys.argv[2]

vectorLen = 10
personalityLen = 5
traitNames = ['Openness','Conscientiousness','Extraversion','Agreeableness','Neuroticism']


# The first part of the program acquires the data and calculates
# the correlations.

# Acquisition of the feature vectors.

csvFeatureFile = open(featureFileName,'r')
csvFeatures = csv.reader(csvFeatureFile)

# Acquisition of the personality assessments

csvPersonalityFile = open(personalityFileName,'r')
csvPersonality = csv.reader(csvPersonalityFile)


# Creation of a dictionary where the keys are the image IDs
# and the values are the personality assessments

dictPersonality = {}

for assessment in csvPersonality:
    
    dictPersonality[assessment[0]] = assessment[1:len(assessment)]
    #print dictPersonality[assessment[0]]


# Creation of a dictionary where the keys are the image IDs
# and the values are the feature vectors

dictFeatures = {}

for vector in csvFeatures:
    
    dictFeatures[vector[0]] = vector[1:len(vector)]
    #print dictFeatures[vector[0]]
    
    
# Calculate and plot correlations

fig = plt.figure()

width = 0.8

for traitIndex in range(len(traitNames)):
    
    correlationList = []
    
    
    for k in range(vectorLen):
    
        traitList = []
        featureList = []
    
        for image in dictFeatures.keys():
            
            traitList.append(float(dictPersonality[image][traitIndex]))
            featureList.append(float(dictFeatures[image][k]))
            #print k,traitIndex,float(dictFeatures[image][k]),float(dictPersonality[image][traitIndex]) 
            
        correlationList.append(scipy.stats.stats.pearsonr(traitList,featureList)[0])
        
        #fig2 = plt.figure()
        #ax = fig2.add_subplot(111)
        #ax.scatter(featureList,traitList)
        #ax.set_title(str(k)+"-"+traitNames[traitIndex])
        #fig2.savefig(str(k)+"-"+traitNames[traitIndex]+".pdf",format='pdf')
        ##plt.show()
        
    
    
    binsList=range(1,vectorLen+1)
    for k in range(len(binsList)): binsList[k]=binsList[k]-0.4
    
    stringPlot = 510+traitIndex+1
    
    ax = fig.add_subplot(stringPlot)
    ax.bar(binsList,correlationList,width,color='b')
    ax.set_xlim(0.5,10.5)
    ax.set_ylim(-0.4,0.4)
    #ax.set_xticks([])
    ax.set_yticks([-0.4,-0.2,0.0,0.2,0.4])
    ax.set_yticklabels(['-0.4','-0.2','0.0','0.2','0.4'],size=10)
    ax.grid()
    
    if traitIndex == len(traitNames) - 1:
    
        ax.set_xticks([1,2,3,4,5,6,7,8,9,10])
        ax.set_xticklabels(['f1','f2','f3','f4','f5','f6','f7','f8','f9','f10'],size=10)
        
    else:
    
        ax.set_xticks([1,2,3,4,5,6,7,8,9,10])
        ax.set_xticklabels(['','','','','','','','','',''],size=10)
        
    ax.text(0.55,0.30,traitNames[traitIndex],size=10)
    #ax.set_title('Self-Assessed Traits')
    #ax.set_ylabel("No. of Subjects",size=10)


#fig.set_size_inches(8.0,3.5)
plt.subplots_adjust(bottom=0.06, left=.085, right=0.98, top=.92)#, hspace=.35)
fig.savefig('correlations.pdf',format='pdf')

plt.show()    
        
        
