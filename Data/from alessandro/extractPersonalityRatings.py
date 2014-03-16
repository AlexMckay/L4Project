
import csv
import sys
import math
import enthought
import matplotlib.pyplot as plt
import scipy.stats.stats 
from scipy.stats.stats import pearsonr


# This program reads the raw assessments (answers to the BFI-10)
# of the face images.


# Initialization

fileName = sys.argv[1]


# Acquisition of the data from the list of participants.

csvFile = open(fileName,'r')
csvData = csv.reader(csvFile)


# Extracting the personality ratings from the raw answers.
# The loop produces a list where every item includes three elements:
# - assessor ID
# - image ID
# - five-dimensional personality list

assessmentList = []

for judgment in csvData:
    
    individualAssessment = []
    individualAssessment.append(judgment[0])
    individualAssessment.append(judgment[1])
    
    assessment = judgment[2:len(judgment)]
    
    for k in range(len(assessment)):
        
        assessment[k] = int(assessment[k])-3
    
    
    # Calculating the ratings in the order OCEAN
    
    personality = []
    
    personality.append(assessment[9] - assessment[4])
    personality.append(assessment[7] - assessment[2])
    personality.append(assessment[5] - assessment[0])
    personality.append(assessment[1] - assessment[6])
    personality.append(assessment[8] - assessment[3])
    
    individualAssessment.append(personality)
    
    assessmentList.append(individualAssessment)
    
    
# The loop creates two dictionaries:
# - dictPersonality has the image ID as key and the five dimensional
#   rating as value.
# - dictCount has the image ID as key and the number of times the image
#   has been assessed.
    
dictPersonality = {}
dictCount = {}

for item in assessmentList:
    
    if item[1] not in dictPersonality.keys():
        
        dictPersonality[item[1]] = item[2]
        dictCount[item[1]] = 1
        
    else:
        
        for k in range(len(item[2])):
            
            dictPersonality[item[1]][k] = dictPersonality[item[1]][k] + item[2][k]
        
        dictCount[item[1]] = dictCount[item[1]] + 1
        

# In this loop, the assessments are divided by the number of times an image
# has been assessed.

for item in dictPersonality.keys():
    
    for k in range(len(dictPersonality[item])):
        
        dictPersonality[item][k] = float(dictPersonality[item][k]) / float(dictCount[item])
        


for item in dictPersonality.keys():
    
    print item,",",dictPersonality[item][0],",",dictPersonality[item][1],",",dictPersonality[item][2],",",dictPersonality[item][3],",",dictPersonality[item][4]
    

