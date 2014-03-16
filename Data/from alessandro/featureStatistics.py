
import csv
import sys
import math
import enthought
import matplotlib.pyplot as plt
import matplotlib.image as mpimg
import scipy.stats.stats 
from scipy.stats.stats import pearsonr
import Image


# This program extracts statistics of the structural features
# (including the name of the images corresponding to the extrema).


# Initialization

featureFileName = sys.argv[1]
path = "/Users/vincia/Dropbox/code/faces/data/colorferet-fa/"
vectorLen = 10


# Acquisition of the feature vectors.

csvFeatureFile = open(featureFileName,'r')
csvFeatures = csv.reader(csvFeatureFile)


# Creation of a dictionary where the keys are the image IDs
# and the values are the feature vectors

dictFeatures = {}

for vector in csvFeatures:
    
    dictFeatures[vector[0]] = vector[1:len(vector)]
    

# Finding minimum and maximum of the feautures

for k in range(vectorLen):
    
    featureList = []
    imageMin = ''
    imageMax = ''
    valueMin = 20.0
    valueMax = 0.0
    
    for image in dictFeatures.keys():
        
        if float(dictFeatures[image][k]) < valueMin:
            
            imageMin = image
            valueMin = float(dictFeatures[image][k])
            
        if float(dictFeatures[image][k]) > valueMax:
            
            imageMax = image
            valueMax = float(dictFeatures[image][k])
        
    print "The minimum for feature",k+1,"is",valueMin,"for image",imageMin    
    print "The maximum for feature",k+1,"is",valueMax,"for image",imageMax
    print

    
