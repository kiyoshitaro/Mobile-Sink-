import HCG
import os
import xlrd
import random

def _get_sink(folder,numberOfSinks,indexOfSamples,indexOfTimes):
    """ lay ra 1 list cac sink.
    Parameters 
    ------------
        folder: string 
            in which data is
        numberOfSinks: int
            number of sinks you want.
        indexOfSamples: int [0,1,2,3,4]
        indexOfTimes: int 0,1,2,3,....
            file in folder is a list of sink Positions accroding indexOfTimes
    Return
    -----------
        output : list
            tra ve 1 danh sach cac sinks    
    """
    sinks = []
    officalSinks = []
    output = []
    for file in os.listdir(folder):
        if file.endswith("sink",1,5) and file.endswith(str(indexOfSamples),0,1):
            file = os.path.join(folder,file)
            sinks.append(file)
    i = 0
    while i < numberOfSinks: 
        officalSinks.append(random.choice(sinks))
        i += 1
    for sink_loc in officalSinks:
        sink = _read_excel_by_index(sink_loc,indexOfTimes)
        output.append(sink)
    return output
def _get_target_from_file(folder,numberOfTarGets,indexOfSamples):
    """
    Parameters:
    ------------
        folder: string
        numberOfTarGet (int)
        indexOfSample:
    Return:
        list of  Targets
    """
    file_location = ''
    for file in os.listdir(folder):
        if file.endswith(str(indexOfSamples)+'Target',0,7):
            file_location = os.path.join(folder,file)

    targets = []
    index = random.sample(range(2000),numberOfTarGets)
    for i in index:
        targets.append(_read_excel_by_index(file_location,i))
    return targets
                 
    

    

def _read_excel_by_index(file_location,index):
    """ lay ra 1 sink dua vao chu ki cua sink or Target tuy vai file
    Parameters
    ----------
        file_location: string
            path den file chua sink
        index: int
            day la vi tri chu ki cua sink
    Return
    ----------
        vi tri cua sink theo chu ki
    """
    workbook = xlrd.open_workbook(file_location)
    worksheet = workbook.sheet_by_index(0)
    total_cols = worksheet.ncols
    sink = []
    for x in range(total_cols):
        sink.append(worksheet.cell(index,x).value)
    return sink
   
# def write_file(input):
#     f = open("outputt.txt","w+")
#     f.write(input)


