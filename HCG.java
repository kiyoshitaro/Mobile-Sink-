import java.util.*;
import java.io.*;
import java.lang.*;

class Point{
    double x ;
    double y ;
    double r = 13;
    int pos ;
    public Point(){}
        
    public Point(double x, double y){
      this.x = x;
      this.y = y;
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
        System.out.println("\n Điểm gần nhất với (" + p1.x +" , " + p1.y +" ) là : (" + m[val].x + " , "+ m[val].y + " ) ==> thuộc nhóm " + val );
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
        System.out.println("\nCác điểm mean mới là : ");
        for (int i =0 ; i< p ; ++i){
            System.out.println("\n (" + m[i].x +" , " + m[i].y + " )");
        }

    }
    int check() {
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
            "Mảng d còn " + d.size() + " phần tử khi bắt đầu cluster"
            );
        for(int i=0;i<p;++i){
            m[i].x=d.get(i).x;
            m[i].y=d.get(i).y; 
            }
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
            cal_mean(); 
            flag=check(); 
            // System.out.println("flag = "+ flag);
            if(flag!=1){
                for(int i=0;i<p;++i){
                    tempk[i] = k[i];
                }
            } 
            // dung bien tempk de kiem tra xem 2 phan loai lien tiep co trung nhau khong 
            // de dung thuat toan
        }
        while(flag==0);
        System.out.println("\n\n\n Clustering bằng Kmeans ta được : ");
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
// giam cac index sau khi da remove mot phan tu trong d 
    void greedy(){
        while(d.size() != 0 ){
            System.out.println("\t=> Lặp");            
            System.out.println("\n\tMảng vào vòng lặp  có : " + d.size() + " phần tử ");
            this.Kmean();
            for (int i =0 ;i< p && k[i].size() != 0;++i){
                System.out.println("\t\tNhóm " +i+ " có " + k[i].size() + " phần tử");
                double tmp = 0;
                int val = 0;
                for(int j=0;j<k[i].size();++j){
                    if(tmp < distance(k[i].get(j),m[i])){
                        tmp = distance(k[i].get(j),m[i]);
                        val = j;
                    }
                System.out.println("\n\t\tĐiểm " + "(" +k[i].get(val).x + "," + k[i].get(val).y + ") xa tâm nhất");
                }
                // val la index diem xa nhat voi tam
                Point center = findCenter(m[i],k[i].get(val));
                // d.remove(k[i].get(val).pos);
                // this.decreaseIndex(k[i].get(val).pos);
                // d.remove(val);
                res.add(center);
                System.out.println(
                    // k[i].get(j).pos + 
                      "\n\t\tXét nhóm : " + i
                      + "\n\t\tTìm được tâm là : "
                      + " (" + center.x + " , " + center.y + ")"
                      );
                for (int j =0 ; j< d.size(); ++j){
                    System.out.print(
                        "\n\t\t\tMảng gốc còn : " + d.size() 
                        + "\n\t\t\tXét phần tử  thứ " + j + " của mảng : "
                        + "\n\t\t\tCó tọa độ (" + d.get(j).x + " , " + d.get(j).y+ ")"
                        // + "\nTọa độ tâm: (" + center.x + " , " + center.y + ")"
                        + "\n\t\t\tKhoảng điểm đó đến tâm = " + (distance(d.get(j),center))
                    );
                    if(distance(d.get(j),center) - center.r < 0.0000001){
                                 System.out.print(
                            " <= "+ center.r +  " => Loại \n\n"
                            );
                        d.remove(j);
                        j --;
                    }
                    else{
                        System.out.print(" > " + center.r +" => Không loại \n\n");
                    }
                }
        
                System.out.println("\n\t\tKích thước mảng d sau khi xét cluster " + i + " là " + d.size());
            }
            System.out.println("\n\tMảng ra vòng lặp  còn : " + d.size() + " phần tử ");
        }
        System.out.println("\t => Ngừng lặp");            

    }

    public static void main(String args[]){
        int P = 3;
        int N = 10;
        HCG hcg =  new HCG(N,P) ;
       
        hcg.d.add(new Point(1,1));
        hcg.d.add(new Point(3,3));
        hcg.d.add(new Point(10,10));
        hcg.d.add(new Point(233,235));
        hcg.d.add(new Point(236,234));
        hcg.d.add(new Point(237,233));
        hcg.d.add(new Point(999,999));
        hcg.d.add(new Point(997,997));
        hcg.d.add(new Point(990,997));
        hcg.d.add(new Point(989,990));
        // hcg.Kmean();
        hcg.greedy();
   

        System.out.println(
            "\n Có " + hcg.res.size() + " node cần tìm là : ");
        for (int i =0 ; i< hcg.res.size(); ++i){
            System.out.println(
                "( " +  hcg.res.get(i).x +" , "+ hcg.res.get(i).y + " )"
            );
        }
    }
}