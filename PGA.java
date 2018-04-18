import java.util.*;
import java.io.*;


class Point{
  double x = 1;
  double y = 2;
  double r = 15;
  public Point(double x, double y){
    this.x = x;
    this.y = y;
  }
}

// class Group{
//     int n;
//     LinkedList<Point> points = new LinkedList<>();
//     public Group(int n){
//         this.n = n;
//     }
// }


class k_means
{
static int count1,count2,count3;

LinkedList<Point> d = new LinkedList<>();
LinkedList<LinkedList<Point>> k = new LinkedList<>();
LinkedList<LinkedList<Point>> tempk = new LinkedList<>();
LinkedList<Point> m = new LinkedList<>();
static double diff[];
static int n,p;
// static double r =15;

static double distance(Point p1, Point p2){
    return Math.sqrt((p1.x-p2.x)*(p1.x - p2.x)+(p1.y - p2.y)*(p1.y -p2.y)) ;   
}
static int cal_diff(Point p1) 
{
    int temp1=0;
    for(int i=0;i<p;++i)
    {
        diff[i]=distance(p1,m[i]);
    }
    int val=0;
    double temp=diff[0];
        for(int i=0;i<p;++i){
        if(diff[i]<temp){
            temp=diff[i];
            val=i;
            }
        }
    return val;
}
// tim diem mean gan nhat voi diem dang xet 


// static int findNode(int i, int cnt){
//      int temp1=0;
//     for(int j=0;j<cnt;++i)
//     {
//         diff[j]=distance(k[i][j],m[i]);
//     }
//     int val;
//     double temp=diff[0];
//         for(int j=0;j<cnt;++j){
//         if(diff[j]>temp){
//             temp=diff[j];
//             val=j;
//             }
//         }
//     return val;
// }


static void cal_mean() 
{
    for(int i=0;i<p;++i){
        m[i].x = 0;
        m[i].y = 0;
    }
    int cnt=0;
    for (int i =0 ; i < k.size();++i){
        for(int j=0;j<k[i].size();j++){
            m[i].x += k[i][j].x;
            m[i].y += k[i][j].y;
        }
        m[i].x = m[i].x/k[i].size() ;
        m[i].y = m[i].y/k[i].size() ;
    }
}
static int check1() 
{
    for (int i = 0 ; i<p ; ++i){
        for (int j =0 ;  j< n; ++j){
            if(tempk[i][j].x != k[i][j].x && tempk[i][j].y != k[i][j].y){
                return 0;
            }
            return 1;
        }
    }
}

static void Kmean(int n ,int p){
    for(int i=0;i<p;++i){
        m[i].x=d[i].x;
        m[i].y=d[i].y; 
        }
        int temp=0;
        int flag=0;
        do
            {
            for(int i=0;i<p;++i){
                for(int j=0;j<n;++j)
                {
                k[i][j][0]=-1;
                k[i][j][1]=-1;
                }
            }
            for(int i=0;i<n;++i) 
                {
                temp = cal_diff(d[i]);
                System.out.println(temp+" "+ count1 +" "+ count2+ " "+ count3);
                if(temp==0){
                    k[temp].get(d[i]);
                }
                else
                    if(temp==1){
                        k[temp].get(d[i]);
                    }
                    else
                        if(temp==2){
                            k[temp].get(d[i]);
                        }
                }
            cal_mean(); 
            flag=check1(); 
            if(flag!=1){
                for(int i=0;i<p;++i){
                    for(int j=0;j<n;++j){
                        tempk[i].get(k[i][j]);
                    }
                }
            }
        }
        while(flag==0);
}

// static void greedy(int t){
//     int cnt = 0;
//     for (int i =0 ; i < n; i++){
//         if(k[t][i] == -1){
//             break;
//         }
//         else{
//             cnt ++;
//         }
//     }
//     int fur = findNode(t,cnt);
//     double[] node = new double[2];
//     node = k[t][fur];
//     for (int i =0 ; i< cnt ; i++){
//         if(distance(k[t][i], node) < r){
//             remove(i); // note
//         }
//     }

// }

public static void main(String args[]){
    n = 8;
    p =2;
    d=new double[n][2];
    for(int i=0;i<4;++i){
        d[i].x = i;
        d[i].y = i;
    }
        for(int  i =0 ; i < 4 ; i ++){
        d[7-i].x = 1000-i;
        d[7-i].y = 1000-i;
    }
    k=new LinkedList<>();
    tempk = new LinkedList<>();
    m = new LinkedList<>();
    diff=new double[p];

    Kmean(n,p);

System.out.println("\n\n\nThe Final Clusters By Kmeans are as follows: ");
for(int i=0;i<p;++i)
{
System.out.print("K"+(i+1)+"{ ");
for(int j=0;k[i][j].x!=-1 && k[i][j].y!=-1&& j<n-1;++j)
System.out.print(k[i][j].x+" "+ k[i][j].y + "  "+ m[i].x + "   " + m[i].y +"\n" );
System.out.println("}");
}
}
}