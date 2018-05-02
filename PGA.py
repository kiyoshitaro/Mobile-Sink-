import HCG
import file
import math
import GraphandCluster as gc


# return [mindist,[Xx,Xy]]


def get_coordinates_of_replay(X,Y,Rs):
    """
    Parameters:
    -------------
        two points and sensing range
    Return:
    -------------
    a list of Relay Node
    
    """
    R1 = []
    # print("input here",X,"Y la ccc",Y)
    d = gc.get_distance(X,Y)
    l = 1 + d//(2*Rs)
    if d % (2*Rs) == 0:
        l -= 1
    else:
        pass
    for i in range(1,l+1):
        record = []
        
        x = (2*i-1)*(Rs/d)* abs((X[0]-Y[0]))+min(X[0],Y[0])
        y = (2*i-1)*(Rs/d)* abs((X[1]-Y[1]))+min(X[1],Y[1])
        record.append(x)
        record.append(y)
        R1.append(record)
    return R1
    

def PGA(folder,numberOfTimes,numberOfSinks,indexOfSamples,S,R,Rs,Rc):
    for i in range(numberOfTimes):
        P = file._get_sink(folder,numberOfSinks,indexOfSamples,i)
        V = S + R
        C = gc._get_components(V,Rc)
        C2 = []
        C2.append(P)


        C1 = list(C)
        while len(C1) > 0:
            minDistance = gc.get_min_distance_from_graphs_and_graphs(C1,C2,Rc)
            R1 = get_coordinates_of_replay(minDistance[1],minDistance[2],Rs)
            C2.append(R1)
            R = R + R1
        print("do dai R:",len(R))
    cpR = list(R)
    for i in range(len(cpR)):
        for j in range(len(cpR)):
            if i == j:
                pass
            else:
                if cpR[i] == cpR[j]:
                    if cpR[i] in R:
                        R.remove(cpR[i])
                    else:
                        pass

    return R



