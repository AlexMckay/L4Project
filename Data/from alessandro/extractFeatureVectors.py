
import csv
import sys
import math
import enthought
import matplotlib.pyplot as plt
import scipy.stats.stats 
from scipy.stats.stats import pearsonr


# This program reads the raw feature vectors and it creates
# a matrix where the first element is the image ID and the other
# elements are the features.


# Initialization

listFileName = sys.argv[1]


# Reading the list of the input files

f = open(listFileName,'r')
fileList = f.readlines()
f.close()


for item in fileList:
    
    fileName = item.split('\n')[0]
    
    imageIDList = fileName.split('/')
    imageID = imageIDList[len(imageIDList)-1]
    
    f = open(fileName,'r')
    lineList = f.readlines()
    
    print imageID.split('.')[0], ",",\
    float(lineList[1].split('=')[1]),",",float(lineList[3].split('=')[1]),",",float(lineList[5].split('=')[1]), \
    ",",float(lineList[7].split('=')[1]),",",float(lineList[9].split('=')[1]),",",float(lineList[11].split('=')[1]), \
    ",",float(lineList[18].split('=')[1]),",",float(lineList[20].split('=')[1]),",",float(lineList[22].split('=')[1]), \
    ",",float(lineList[24].split('=')[1])
    
    f.close()
    