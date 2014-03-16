
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
traitIndex = int(sys.argv[2])
numAssessors = 11
traitNames = ['Openness','Conscientiousness','Extraversion','Agreeableness','Neuroticism']


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
    

# The loop creates a dictionary where the key is the name of an
# image and the value is a list of individual assessments (individual
# five-dimensional vectors).

dictRatings = {}

for assessment in assessmentList:
    
    if assessment[1] not in dictRatings.keys():
        
        dictRatings[assessment[1]] = []
        dictRatings[assessment[1]].append(assessment[2])
        
    else:
        
        dictRatings[assessment[1]].append(assessment[2])

  
# The loop calculates the average correlation between assessors

corrSum = 0.0
counter = 0

for k in range(numAssessors-1):
    for l in range(k+1,numAssessors):

        firstRating = []
        secondRating = []

        for image in dictRatings.keys():
            
            if len(dictRatings[image]) == numAssessors:
            
                firstRating.append(dictRatings[image][k][traitIndex])
                secondRating.append(dictRatings[image][l][traitIndex])
        
        corrSum = corrSum + scipy.stats.stats.pearsonr(firstRating,secondRating)[0]
        counter = counter + 1
        #print k,l,scipy.stats.stats.pearsonr(firstRating,secondRating)[0]
        
        
        
averageCorrelation = corrSum/counter
reliability = (numAssessors*averageCorrelation) / (1.0+(numAssessors-1)*averageCorrelation)
print "The reliability for",traitNames[traitIndex],"is",reliability
print "The average correlation is",averageCorrelation
        
