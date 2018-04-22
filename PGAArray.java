import java.util.*;
class k_means
{
static int count1,count2,count3;
static double d[][];
static double k[][][];
static double tempk[][][];
static double m[][];
static double diff[];
static int n,p;
static double r =15;

static double distance(double x[], double y[]){
    return Math.sqrt((x[0]-y[0])*(x[0]-y[0])+(x[1]-y[1])*(x[1]-y[1]) ) ;   
}

static int cal_diff(double a[]) 
{
    int temp1=0;
    for(int i=0;i<p;++i)
    {
        diff[i]=distance(a,m[i]);
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
        m[i][0]=0; 
        m[i][1] = 0;
    }
    int cnt=0;
    for(int i=0;i<p;++i){
        cnt=0;
        for(int j=0;j<n-1;++j)
        {
            if(k[i][j][0]!=-1){
                m[i][0]+=k[i][j][0];
                ++cnt;
                }
        }
        m[i][0]=m[i][0]/cnt;
        }

    for(int i=0;i<p;++i){
        cnt=0;
        for(int j=0;j<n-1;++j)
        {
            if(k[i][j][1]!=-1){
                m[i][1]+=k[i][j][1];
                ++cnt;
                }
        }
        m[i][1]=m[i][1]/cnt;
        }
}

static int check1() 
{
    for(int i=0;i<p;++i)
        for(int j=0;j<n;++j)
            if(tempk[i][j][0]!=k[i][j][0]&& tempk[i][j][1]!=k[i][j][1])
            {
            return 0;
            }
        return 1;
}

static void Kmean(int n ,int p){
    for(int i=0;i<p;++i){
        m[i][0]=d[i][0];
        m[i][1]=d[i][1]; 
        }

        int temp=0;
        int flag=0;
        // for(int i=0;i<8;++i){
        //     System.out.println(d[i][0]+ "  "+ d[i][1]);
        // }    
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
                temp=cal_diff(d[i]);
                System.out.println(temp+" "+ count1 +" "+ count2+ " "+ count3);
                if(temp==0){
                    k[temp][count1][0]=d[i][0];
                    k[temp][count1++][1]=d[i][1];
                }
                else
                    if(temp==1){
                        k[temp][count2][0]=d[i][0];
                        k[temp][count2++][1]=d[i][1];
                    }
                    else
                        if(temp==2){
                            k[temp][count3][0]=d[i][0];
                            k[temp][count3++][1]=d[i][1];
                        }
                }
                // for (int i =0 ; i< p ; i ++){
                //     for (int j =0 ; j<n && k[i][j][0] != 1;j++){
                //         System.out.println(k[i][j][0] + " __________ "+ k[i][j][1] + "  ______  " + i);
                //     }
                // }
            cal_mean(); 
            flag=check1(); 
            if(flag!=1){
                for(int i=0;i<p;++i){
                    for(int j=0;j<n;++j){
                        tempk[i][j]=k[i][j];
                    }
                }
            }

            count1=0;
            count2=0;
            count3=0;
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
    // Scanner scr=new Scanner(System.in);
    // System.out.println("Enter the number of elements ");
    // n=scr.nextInt();
    n = 8;
    d=new double[n][2];
    // System.out.println("Enter "+n+" elements: ");
    for(int i=0;i<4;++i){
        d[i][0] = i;
        d[i][1] = i;
    }
    // for(int i=n-1;i>=n/2-1;--i){
        for(int  i =0 ; i < 4 ; i ++){
        d[7-i][0] = 1000-i;
        d[7-i][1] = 1000-i;
    }
    
    
    // System.out.println("Enter the number of clusters: ");
    // p=scr.nextInt();
    p =2;
    k=new double[p][n][2];
    tempk=new double[p][n][2];
    m=new double[p][2];
    diff=new double[p];

    Kmean(n,p);

System.out.println("\n\n\nThe Final Clusters By Kmeans are as follows: ");
for(int i=0;i<p;++i)
{
System.out.print("K"+(i+1)+"{ ");
for(int j=0;k[i][j][0]!=-1 && k[i][j][1]!=-1&& j<n-1;++j)
System.out.print(k[i][j][0]+" "+ k[i][j][1] + "  "+ m[i][0] + "   " + m[i][1] +"\n" );
System.out.println("}");
}
}
}