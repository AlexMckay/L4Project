
import csv
import sys
import math
import enthought
import matplotlib.pyplot as plt
import scipy.stats.stats 
from scipy.stats.stats import pearsonr
import Image

fileName = sys.argv[1]

f = open(fileName,'r')
imageList = f.readlines()

for image in imageList:
    
    imageList[imageList.index(image)] = image[0:len(image)-1]

for image in imageList:

    im = Image.open(image)
    im.show()

    label = sys.stdin.readline()
    print image.split('/')[len(image.split('/'))-1], label
    
    #im.close()






