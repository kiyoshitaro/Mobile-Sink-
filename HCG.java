import java.util.*;
import java.io.*;
import java.lang.*;

class Point{
    double x ;
    double y ;
    double r = 2.1;
    int pos ;
    public Point(){}
        
    public Point(double x, double y){
      this.x = x;
      this.y = y;
    }
    public Point(double x, double y, int pos){
        this.x = x;
        this.y = y;
        this.pos = pos;
    }
}
class HCG{
    LinkedList<Point> d;
    LinkedList<Point> res;
    LinkedList<Point> k[] ; // mang cac group- p phan tu
    LinkedList<Point> tempk[];
    Point m[]; // mang cac diem mean- p phan tu
    double diff[];
    int n,p;
    HCG(int n, int p){
        this.n = n;
        this.p = p < n ? p : n;
        k = new LinkedList[p];
        tempk = new LinkedList[p];
        m = new Point[p];
        diff = new double[n];
        d = new LinkedList<>();
        res = new LinkedList<>();
        for (int i=0; i<p;i++){
            k[i] = new LinkedList<>();
            tempk[i] = new LinkedList<>();
            m[i] = new Point();
        }
    }
    double distance(Point p1, Point p2){
        return Math.sqrt((p1.x-p2.x)*(p1.x - p2.x)+(p1.y - p2.y)*(p1.y -p2.y)) ;   
    }
    int cal_diff(Point p1) 
    {
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
    void cal_mean() 
    {
        for(int i=0;i<p;++i){
            m[i].x = 0;
            m[i].y = 0;
        }
        for (int i =0 ; i < p;i++){
            for(int j=0;j<k[i].size();++j){
                m[i].x += k[i].get(j).x;
                m[i].y += k[i].get(j).y;
            }
            m[i].x = m[i].x/k[i].size() ;
            m[i].y = m[i].y/k[i].size() ;
            // System.out.println(m[i].x + "         " + i) ;
        }
    }
    int check1() {
        for(int i=0;i<p;++i){
            for(int j=0;j<k[i].size();j++){
                if(tempk[i].get(j).x != k[i].get(j).x && tempk[i].get(j).y != k[i].get(j).y)
                {return 0;}
            }
        }
        return 1;
    }

    void Kmean(){
        p = p < d.size() ? p : d.size();
        System.out.println(
            ":::::::::::" + d.size()
            );
        for(int i=0;i<p;++i){
            m[i].x=d.get(i).x;
            m[i].y=d.get(i).y; 
            // m[i].x=i;
            // m[i].y=i; 
            }
            // int temp=0;
        int flag=0;
        Point tmp = new Point(0,0);
        for(int i=0;i<p;++i){
            for(int j=0;j<n;++j){
                tempk[i].add(tmp);
            }
        }
        do
            {
            for (int i =0 ; i< p ;++i){
                k[i].clear();
            } 
            // Xoa mang truoc khi lap lai
            for(int i=0;i<d.size();++i) {
                k[cal_diff(d.get(i))].add(d.get(i));
            } 
                // add cac phan tu vao nhom co mean gan nhat
            // for (int i =0 ; i< p ; i ++){
            //     for (int j =0 ; j< k[i].size();j++){
            //         System.out.println(k[i].get(j).x + " --------- "+ k[i].get(j).y + "  ------  " + i);
            //     }
            // }
            // test
            cal_mean(); 
            flag=check1(); 
            // System.out.println("flag = "+ flag);
            if(flag!=1){
                for(int i=0;i<p;++i){
                    tempk[i] = k[i];
                }
            } // dung bien tempk de kiem tra xem 2 phan loai lien tiep co trung nhau khong 
            // de dung thuat toan
        }
        while(flag==0);
        System.out.println("\n\n\nThe Final Clusters By Kmeans are as follows: ");
        for(int i=0;i<p;++i){
            System.out.print("K"+(i+1)+"{ ");
            for(int j=0; j<this.k[i].size();++j)
                System.out.print(this.k[i].get(j).x+" "+ this.k[i].get(j).pos + "  "+ this.m[i].x + "   " + this.m[i].y +"\n" );
                System.out.println("}");
        }
    }

    Point findCenter(Point m,  Point d){
        if(m.x == d.x && m.y== d.y){
            Point center = new Point(m.x + m.r , m.y );
            return center;
        }
        Point vector = new Point((m.x-d.x)/distance(m, d) , (m.y-d.y)/distance(m, d));
        Point center = new Point(d.x+vector.x*vector.r , d.y + vector.y*vector.r);
        return center;
    }
    // m - mean , d - diem xa mean nhat
    void decreaseIndex(int pos){
        System.out.println(pos + "==:==");

        for (int i =pos ; i< d.size(); ++i){
            d.get(i).pos --;
            System.out.println(d.get(i).pos + "======");
        }
    }
// giam cac index sau khi da remove mot phan tu trong d 
    void greedy(){
        while(d.size() != 0 ){
            System.out.println(d.size() + "---in");
            this.Kmean();
            for (int i =0 ;i< p && k[i].size() != 0;++i){
                System.out.println(i+ "''''''''''''" + k[i].size());
                double tmp = 0;
                int val = 0;
                for(int j=0;j<k[i].size();++j){
                    if(tmp < distance(k[i].get(j),m[i])){
                        tmp = distance(k[i].get(j),m[i]);
                        val = j;
                    }
                }
                // val la index diem xa nhat voi tam
                System.out.println(val+"````````````" + k[i].get(val).pos+ "s" + d.size());
                Point center = findCenter(m[i],k[i].get(val));
                d.remove(k[i].get(val).pos);
                this.decreaseIndex(k[i].get(val).pos);
                res.add(center);
                for (int j=0 ; j< k[i].size() ; ++j){
                    System.out.println(k[i].get(j).x + "----"+ k[i].get(j).y );
                    if(distance(k[i].get(j),center) - center.r < -0.000001){
                        System.out.println(
                            // k[i].get(j).pos + 
                              "+++++++++" + i
                            + "+++++++++" + j
                            + "+++++++++" + d.size() 
                            // + "---------" + center.r 
                            + "+++++++++" + center.x
                            + "+++++++++" + center.y
                            + "+++++++++" + (distance(k[i].get(j),center) - center.r)
                            + "+++++++++" + k[i].get(j).pos
                            );
                        d.remove(k[i].get(j).pos);
                        this.decreaseIndex(k[i].get(j).pos);
                    }
                    else {
                        System.out.println(
                            // k[i].get(j).pos + 
                              "+++++++++" + i
                            + "+++++++++" + j
                            + "+++++++++" + d.size() 
                            // + "---------" + center.r 
                            + "+++++++++" + center.x
                            + "+++++++++" + center.y
                            + "+++++++++" + (distance(k[i].get(j),center) - center.r)
                            + "+++++++++" + k[i].get(j).pos
                            );
                    }
                }
                System.out.println(d.size() + "=''=");
                // remove nhung diem trong vong tron
            }
            System.out.println(d.size() + "--out");
        }
    }

    public static void main(String args[]){
        int P = 2;
        int N = 8;
        HCG hcg =  new HCG(N,P) ;
        for (int i=0; i< 4 ;++i){
            Point c = new Point(i,i,i);
            hcg.d.add(c);
        }
        for (int i=0; i< 4 ;++i){
            Point c = new Point(1000-i,1000-i,7-i);
            hcg.d.add(c);
        }
        // hcg.Kmean();
        hcg.greedy();
   

        for (int i =0 ; i< hcg.res.size(); ++i){
            System.out.println(hcg.res.get(i).x + "--------" + hcg.res.get(i).y+"-------" + hcg.res.size());
        }
    }
}