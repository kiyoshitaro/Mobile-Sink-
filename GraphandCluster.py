import numpy as np 
from sklearn.cluster import KMeans
import math
def get_min_distance_from_point_and_graph(node,graph):
    """
    Return
    -------
        [mindistance,[Xx, Xy]]
    """
    distances = []
    for i in range(len(graph)):
        record = []

        distance = get_distance(node,graph[i])
        record.append(distance)
        record.append(graph[i])
        distances.append(record)

    
    distances.sort()


    return distances[0]

def get_min_distance_from_graph_and_graph(firstGraph,secondGraph):
    """
    Parameters
    ----------
        2 do thi co dang [[],[],[]]
    Return
    -----------
        [min,[Xx,Xy],[Yx,Yy]]
    """
    distances = []
    for j in range(len(secondGraph)):
        minDistance = get_min_distance_from_point_and_graph(secondGraph[j],firstGraph)
        minDistance.append(secondGraph[j])

        distances.append(minDistance)
    distances.sort()

    return distances[0]


def get_min_distance_from_graphs_and_graphs(firstGraphs,secondGraphs,Rc):
    """
    
    Parameters
    ------------
    Return
    ------------
        [min,[Xx,Xy],[Yx,Yy],[index_subGr1,index_subGr2]]
    """
    # print("input graphs and graphs",firstGraphs,"grrap2",secondGraphs)
    distances = []
    for j in range(len(secondGraphs)):
        for i in range(len(firstGraphs)):
            minDistance = get_min_distance_from_graph_and_graph(firstGraphs[i],secondGraphs[j])
            recordIndexGraph = []

            recordIndexGraph.append(i)
            recordIndexGraph.append(j)
            minDistance.append(recordIndexGraph)
            distances.append(minDistance)
    distances.sort()
    index = 0 
    for i in range(len(distances)):
        if distances[i][0] > Rc:
            index = i
            break
    cpFristGraph = list(firstGraphs)
    for i in range(index+1):
        if cpFristGraph[distances[i][3][0]] in firstGraphs:
            secondGraphs.append(cpFristGraph[distances[i][3][0]])
            firstGraphs.remove(cpFristGraph[distances[i][3][0]])  
        else:
            pass
            
    return distances[index]                

def find_centroid(cluster):
    """
    Return 
    ----------
        centroid [Ox,Oy] of Cluster
    """
    O = [0,0]
    for point in cluster:
        O[0] += point[0]
        O[1] += point[1]
    length = len(cluster)
    O[0] = O[0]/length
    O[1] = O[1]/length
    return O


def get_max_distance_from_point_and_graph(node,graph):
    """
    Parameters:
    --------------
        point and graph 
    Return
    --------------
        maxDistance and point in graph [maxDistace,Graph[i]]
    """
    distances = []
    for i in range(len(graph)):
        record = []

        distance = get_distance(node,graph[i])
        record.append(distance)
        record.append(graph[i])
        distances.append(record)
    distances.sort()
    distances.reverse()
    return distances[0]

### number of clusters you want

def implement_clusteringv2(listTarget):
    print("number of clusters which you want:")
    i = int(input())
    X = np.array(listTarget)
    kmeans = KMeans(n_clusters=i, random_state=0).fit(X)
    clusters = []
    for j in range(i):
        X0 = X[np.where(kmeans.labels_ == j)]
        clusters.append(X0)
    return clusters
### 
def implement_clustering(i,listTarget):
    X = np.array(listTarget)
    kmeans = KMeans(n_clusters=i, random_state=0).fit(X)
    clusters = []
    for j in range(i):
        X0 = X[np.where(kmeans.labels_ == j)].tolist()
        clusters.append(X0)
    centers = kmeans.cluster_centers_.tolist()
    return clusters,centers
### 
def clustering(listTarget):
    X = np.array(listTarget)
    kmeans = KMeans(n_clusters=3, random_state=0).fit(X)
    X0 = X[np.where(kmeans.labels_ == 0)]
    X1 = X[np.where(kmeans.labels_ == 1)]
    X2 = X[np.where(kmeans.labels_ == 2)]

    return X0,X1,X2

def find_furthest_point(distlist,sample):
    """
    Return
    ---------
        furthest Point [Ox,Oy]
    """
    maxdist = distlist.index(max(distlist))
    point = sample[maxdist]
    return point
    
def get_distance(X,Y):
    """
    Return:
    ------------
        Distance (int)
    """
    dist = math.sqrt((Y[0]-X[0])**2 + (Y[1]-X[1])**2)
    dist = round(dist)
    return dist

def get_sensor_position(O,point,alpha):
    """
    Paramters:
    -----------
        Centroid (list) [Ox,Oy]
        furthest Point (list) [pointX,pointY]
        alpha (int)
    Return :
    -----------
        SenrSor Position [sensorX,sensorY]
    """
    sensorPosition = []
    sensorPosition.append(O[0]+alpha*(point[0]-O[0]))
    sensorPosition.append(O[1]+alpha*(point[1]-O[1]))
    return sensorPosition
def updateCluster(i,new,x0,x1,x2):
    if i == 0:
        x0 = new 
        print("vl k gan duoc gia tri ",len(x0),x0)
    elif i == 1:
        x1 = new        
        print("vl k gan duoc gia tri ",len(x1),x1)
    else:
        x2 = new
        print("vl k gan duoc gia tri ",len(x2),x2)
    return 
    


def _is_connected(newNode,component,Rc):
    for i in range(len(component)):
        if get_distance(component[i],newNode) <= Rc:
            return True
        else:
            return False

def _set_visited(graph):
    visited = dict()
    for i in range(len(graph)):
        visited[i] = False
    return visited


def _spanning_tree(graph):
    visited = _set_visited(graph)
    tree = []
    tree.append(graph[0])
    visited[0] = True
    for i in range(len(graph)-1):
        check = []
        for j in range(len(graph)):
            
            if visited[j] == False:
                record = []
                dis = get_distance(tree[-1],graph[j])
                record.append(dis)
                record.append(graph[j])
                check.append(record)
            else:
                pass
        check.sort()
        tree.append(check[0][1])  
        visited[graph.index(check[0][1])] = True
    return tree

def _get_components(graph,Rc):
    """
    Chia do thi thanh cac thanh phan lien ket, voi dieu kien la hai dinh phai la ket noi duoc voi nhau,
    kiem tra xem hai diem bat ki co ket noi duoc voi nhau hay ko?
    Parameters
    ---------
        Graph: list la mot list cac diem,
        Rc ban kinh truyen
    Return : 
    """
    visited = _set_visited(graph)
    output = []
    cout = 0
    for i in range(len(graph)):
        if visited[i] == False:
            cout += 1
            component = []
            component.append(graph[i])
            visited[i] = True
            for j in range(len(graph)):
                if visited[j] == False and _is_connected(graph[j],component,Rc) == True:
                    component.append(graph[j])
                    visited[j] = True
            output.append(component)
    return output

            
        
