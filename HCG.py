import GraphandCluster as gc
import file 


def HCG(folder,numberOfTarGets,indexOfSamples,Rs):
    
    listTarget = file._get_target_from_file(folder,numberOfTarGets,indexOfSamples)   
    S = []
    for k in range(34,35):
        clusters, centers = gc.implement_clustering(k,listTarget)
        S1 = []
        for i in range(len(clusters)):
            while len(clusters[i]) > 0:
                
                O = centers[i]
                furthestPoint = gc.get_max_distance_from_point_and_graph(O,clusters[i])
                d = furthestPoint[0]
                if d <= Rs:
                    s = O   
                else:
                    alpha = 1 - Rs/d
                    s = gc.get_sensor_position(O,furthestPoint[1],alpha)
                S1.append(s)
                kk = 0 
                T1 = list(clusters[i])
                for ii in range(len(T1)):                    
                    distance = gc.get_distance(T1[ii],s)
                    if distance - Rs <= 0:
                        clusters[i].remove(clusters[i][kk])
                    else:
                        kk += 1
                if len(clusters[i]) == 0:
                    break
                else:
                    centers[i] = gc.find_centroid(clusters[i])
        S.append(S1)
    print(len(S))
    return S
                
                
            


