public class Main extends k_meansArray{
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
    // k_meansArray kmean = new k_meansArray(n,p);


    k_meansArray.Kmean(n,p);

System.out.println("\n\n\nThe Final Clusters By Kmeans are as follows: ");
for(int i=0;i<p;++i)
{ja
System.out.print("K"+(i+1)+"{ ");
for(int j=0;k[i][j][0]!=-1 && k[i][j][1]!=-1&& j<n-1;++j)
System.out.print(k[i][j][0]+" "+ k[i][j][1] + "  "+ m[i][0] + "   " + m[i][1] +"\n" );
System.out.println("}");
}
}
}