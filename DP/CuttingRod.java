import java.util.*;
class CuttingRod
{
    public static void main(String args[])
    {
        int arr[] = {1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int[][] rAs=cutRod(arr);
        System.out.println(rAs[0][arr.length]);
        System.out.println(Arrays.toString(rAs[1]));
        int n=8;        
        while(n>0)
        {
            System.out.print(rAs[1][n]+" ");
            n=n-rAs[1][n];
        }
    }
    static int[][] cutRod(int[] arr)
    {
        int[] r=new int[arr.length+1];        
        int[] s=new int[arr.length+1];        
        r[0]=0;
        int i,j,q;
        for(i=1;i<=arr.length;i++)
        {
            q=Integer.MIN_VALUE;
            for(j=0;j<i;j++)            
            {
                if(q<arr[j]+r[i-j-1])
                {
                    q=arr[j]+r[i-j-1];
                    s[i]=j+1;
                }
            }
            r[i]=q;
        }
        return new int[][]{r,s};
    }
}