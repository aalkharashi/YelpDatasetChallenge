#@author : Naren Suri
# This is basically used to extract the relevant features we want from th json of business file.
# the extracted fields were merged with the other data files created using java.
# all these were joined and a final data set is created. 
import json
from pprint import pprint
from datetime import datetime

timeformat = '%H:%M'
totalhours = 0
output = ""

def write_headers():
    """
    This function writes the headers
    into the output file and returns None
    """

    with open('headerCreation.json') as ro:
        head = json.load(ro)
    # read the various routes stops
    outfile.write(str(head['Business']+'\n'))
    # write the headers into the output file
    
outfile = open('Businessdata.csv', 'a')
write_headers()    
 

with open("businessdata.json") as jsondata:
    for eachrow in jsondata:
        totalhours =0; 
        output=""
        currentrecord=""
        currentrecord= json.loads(eachrow)        
        #print currentrecord['business_id']
        output = currentrecord['business_id']
        if 'hours' in currentrecord:
            totalhours=0
            for eachkey in currentrecord['hours']:
                if (('close' not in currentrecord['hours'][eachkey]) or ('open' not in currentrecord['hours'][eachkey])):
                    pass
                else:   
                    #print currentrecord['hours'][eachkey]['open']
                    totalhours = totalhours+((datetime.strptime(currentrecord['hours'][eachkey]['close'],timeformat) - datetime.strptime(currentrecord['hours'][eachkey]['open'],timeformat)).seconds)/3600

            
        if totalhours == 0:
            output = output + ","+"?" 
        else:
            output = output + ","+str(totalhours)
            

        #print currentrecord['city']
        if 'city' in currentrecord:            
            output = output + ","+currentrecord['city'].replace(',', '')
        else:
            output = output + ","+"?"
        #--------------------
        if 'longitude' in currentrecord:
            output = output + ","+str(currentrecord['longitude']).replace(',', '')
        else:
            output = output + ","+"?"
        #print currentrecord['latitude']
        if 'latitude' in currentrecord:
            output = output + ","+str(currentrecord['latitude']).replace(',', '')
        else:
            output = output + ","+"?"
        #print currentrecord['stars']
        if 'stars' in currentrecord:
            output = output + ","+str(currentrecord['stars']).replace(',', '')
        else:
            output = output + ","+"?"
        #if currentrecord['Ambience']:
        #------------------------------------------------------------ 
        if 'attributes' in currentrecord:
            if 'Ambience' in currentrecord['attributes']:
                if 'casual' in currentrecord['attributes']['Ambience']:
                    casualval=False
                    casualval = bool(currentrecord['attributes']['Ambience']['casual'])
                    if casualval:                        
                        output = output + ","+"1"
                    else :
                        output = output + ","+"0"
                else:
                    output = output + ","+"?"
                    #------------------------------------------------
                if 'romantic' in currentrecord['attributes']['Ambience']:
                    romanticval = bool(currentrecord['attributes']['Ambience']['romantic'])
                    if romanticval:                        
                        output = output + ","+"1"
                    else :
                        output = output + ","+"0"
                else:
                    output = output + ","+"?"
                    #------------------------------------------------
                if 'classy' in currentrecord['attributes']['Ambience']:
                    classyval = bool(currentrecord['attributes']['Ambience']['classy'])
                    if classyval:                        
                        output = output + ","+"1"
                    else :
                        output = output + ","+"0"
                else:
                    output = output + ","+"?"
                #------------------------------------------------------
                if 'touristy' in currentrecord['attributes']['Ambience']:
                    touristyval = bool(currentrecord['attributes']['Ambience']['touristy'])
                    if touristyval:                        
                        output = output + ","+"1"
                    else :
                        output = output + ","+"0"
                else:
                    output = output + ","+"?"
                #--------------------------------------------------------
                if 'trendy' in currentrecord['attributes']['Ambience']:
                    trendyval = bool(currentrecord['attributes']['Ambience']['trendy'])
                    if trendyval:                        
                        output = output + ","+"1"
                    else :
                        output = output + ","+"0"
                else:
                    output = output + ","+"?"
                #---------------------------------------------------------
            else:
                output = output + ","+"?"+",?"+",?"+",?"+",?"                          
        #-----------------------------------------------------------        
        if "Good for Kids" in currentrecord['attributes']:
            good4kidsval = bool(currentrecord['attributes']['Good for Kids'])
            if good4kidsval:                        
                output = output + ","+"1"
            else :
                output = output + ","+"0"
        else :
            output = output + ","+"?"   
        #-----------------------------------------------------------
        if "Price Range" in currentrecord['attributes']:
            pricerangeval = bool(currentrecord['attributes']['Price Range'])
            if pricerangeval:                        
                output = output + ","+str(currentrecord['attributes']['Price Range'])
            else :
                output = output + ","+"?"
        else :
            output = output + ","+"?"  
        #-----------------------------------------------------------
        if "Delivery" in currentrecord['attributes']:
            Deliveryval = bool(currentrecord['attributes']['Delivery'])
            if Deliveryval:                        
                output = output + ","+"1"
            else :
                output = output + ","+"0"
        else :
            output = output + ","+"?"
        #------------------------------------------------------------
        if "Accepts Credit Cards" in currentrecord['attributes']:
            AcesCredCadval = bool(currentrecord['attributes']['Accepts Credit Cards'])
            if AcesCredCadval:                        
                output = output + ","+"1"
            else :
                output = output + ","+"0"
        else :
            output = output + ","+"?"
        #------------------------------------------------------------
        if "Takes Reservations" in currentrecord['attributes']:
            TakResrvval = bool(currentrecord['attributes']['Takes Reservations'])
            if TakResrvval:                        
                output = output + ","+"1"
            else :
                output = output + ","+"0"
        else :
            output = output + ","+"?"
        #------------------------------------------------------------
        if "Wi-Fi" in currentrecord['attributes']:
            WiFival = bool(currentrecord['attributes']['Wi-Fi'])
            if WiFival:                        
                output = output + ","+str(currentrecord['attributes']['Wi-Fi'])
            else :
                output = output + ","+"?"
        else :
            output = output + ","+"?"       
        #------------------------------------------------------------ 
        if "Happy Hour" in currentrecord['attributes']:
            HappyHourval = bool(currentrecord['attributes']['Happy Hour'])
            if HappyHourval:                        
                output = output + ","+"1"
            else :
                output = output + ","+"0"
        else :
            output = output + ","+"?"       
        #------------------------------------------------------------ 
        if 'attributes' in currentrecord:
            if 'Parking' in currentrecord['attributes']:
                if 'garage' in currentrecord['attributes']['Parking']:
                    garageval = bool(currentrecord['attributes']['Parking']['garage'])
                    if garageval:                        
                        output = output + ","+"1"
                    else :
                        output = output + ","+"0"
                else:
                    output = output + ","+"?"                
                    #------------------------------------------------
                if 'street' in currentrecord['attributes']['Parking']:
                    streetval = bool(currentrecord['attributes']['Parking']['street'])
                    if streetval:                        
                        output = output + ","+"1"
                    else :
                        output = output + ","+"0"
                else:
                    output = output + ","+"?"                        
                    #------------------------------------------------
                if 'validated' in currentrecord['attributes']['Parking']:
                    validatedval = bool(currentrecord['attributes']['Parking']['validated'])
                    if validatedval:                        
                        output = output + ","+"1"
                    else :
                        output = output + ","+"0"
                else:
                    output = output + ","+"?"                        
                #------------------------------------------------------
                if 'lot' in currentrecord['attributes']['Parking']:
                    lotval = bool(currentrecord['attributes']['Parking']['lot'])
                    if lotval:                        
                        output = output + ","+"1"
                    else :
                        output = output + ","+"0"
                else:
                    output = output + ","+"?"                        
                #--------------------------------------------------------
                if 'valet' in currentrecord['attributes']['Parking']:
                    valetval = bool(currentrecord['attributes']['Parking']['valet'])
                    if valetval:                        
                        output = output + ","+"1"
                    else :
                        output = output + ","+"0"
                else:
                    output = output + ","+"?"                        
                #---------------------------------------------------------
            else:
                output = output + ","+"0"+",0"+",0"+",0"+",0"
            #------------------------------------------------------------------------------------------------
        if "Good For Groups" in currentrecord['attributes']:
            GoodForGroupsval = bool(currentrecord['attributes']['Good For Groups'])
            if GoodForGroupsval:                        
                output = output + ","+"1"
            else :
                output = output + ","+"0"
        else :
            output = output + ","+"?"
        #------------------------------------------------------------                      
        
        print output
        outfile.write(output.encode('utf8')+ '\n')
        
        output = "" 
outfile.close()      
        
        
