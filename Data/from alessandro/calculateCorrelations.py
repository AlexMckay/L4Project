
import csv
import sys
import math
import enthought
import matplotlib.pyplot as plt
import scipy.stats.stats 
from scipy.stats.stats import pearsonr


# This program calculates the correlations between structural face
# features and personality traits.


# Initialization

personalityFileName = sys.argv[1]
featureFileName = sys.argv[2]
vectorLen = 10
personalityLen = 5


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
    
    
# Calculate correlations

traitIndex = int(sys.argv[3])

for k in range(vectorLen):

    traitList = []
    featureList = []

    for image in dictFeatures.keys():
        
        traitList.append(float(dictPersonality[image][traitIndex]))
        featureList.append(float(dictFeatures[image][k]))
        
    print "The correlation between trait",traitIndex,"and feature",k,"is",scipy.stats.stats.pearsonr(traitList,featureList)
    
    
    
    
        
        
