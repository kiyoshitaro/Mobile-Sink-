import file
import GraphandCluster as gc
import PGA
# def SGA(S,numberOfTimes,numberOfSinks,folder,indexOfSamples,Rc,R,Rs):
#     R = list(S)
#     for i in range(numberOfTimes):
#         P = file._get_sink(folder,numberOfSinks,indexOfSamples,i)   
#         minDis = gc.get_min_distance_from_graph_and_graph(R,P)
#         A = list(R)
#         A.append(minDis[2])
#         tree = gc._spanning_tree(A)
#         for i in range(0,len(tree)-1):
#             dis = gc.get_distance(tree[i],tree[i+1])
#             if dis > Rc:
#                 R1 = PGA.get_coordinates_of_replay(tree[i],tree[i+1],Rs)
#                 R = R + R1
#             else:
#                 pass
        
#     return R
def SGA(S,numberOfTimes,numberOfSinks,folder,indexOfSamples,Rc,R,Rs):
    cluters, _ = gc.implement_clustering(150,S)
    for i in range(len(cluters)):
        tree = gc._spanning_tree(cluters[i])
        for k in range(len(tree)-1):
            dis = gc.get_distance(tree[k],tree[k+1])
            if dis > Rc:
                R1 = PGA.get_coordinates_of_replay(tree[k],tree[k+1],Rs)
                R = R + R1
            else:
                pass
    print("do dai input:",len(R))
    return PGA.PGA(folder,numberOfTimes,numberOfSinks,indexOfSamples,S,R,Rs,Rc)

# def SGA(S,numberOfTimes,numberOfSinks,folder,indexOfSamples,Rc,R,Rs,t):
#     cluters, _ = gc.implement_clustering(t,S)
#     for i in range(len(cluters)):
#         tree = gc._spanning_tree(cluters[i])
#         for k in range(len(tree)-1):
#             dis = gc.get_distance(tree[k],tree[k+1])
#             if dis > Rc:
#                 R1 = PGA.get_coordinates_of_replay(tree[k],tree[k+1],Rs)
#                 R = R + R1
#             else:
#                 pass
#     # return PGA.PGA(folder,numberOfTimes,numberOfSinks,indexOfSamples,S,R,Rs,Rc)
#     return R

# ve thuat toan SGA 
#1 input la cac cluster sensors o thuat toan 1 - HCG , nhung ma o thuat 
# toan thu nhat ta chi dua ra 1 danh sach cac sensors.
#2 ve viec tim cay khung trong moi cluster, t nhan thay co mot so van de nhu sau
# ta da chia thanh cac clusters sau de them cac node vao roi vay buoc chia thanh phan 
# dau tien se bi thua, thu hai la cac ta ket noi cac node trong cluster lai voi nhau
# nhung ma mot cau hoi dat ra la "chi phi de lien ket hai node bat ki trong cluster lieu
#  co tot hon khi mk ket not hai node do vs node khac hay sink trong doan sau "
# hay ns cach khac la khoang cach giua hai node ma mk ms lien ket do , tai PGA co thang
# sink nao do n gan hon thi sao.