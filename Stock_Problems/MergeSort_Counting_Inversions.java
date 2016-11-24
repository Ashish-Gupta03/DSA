import java.lang.*;
import java.io.*;
class MergeSort {
    public static void main(String args[]) throws FileNotFoundException
    {
    	int i=0,j=0;
    	MergeSort ms=new MergeSort();
    	// int[] arr=new int[8];
    	// Scanner sc=new Scanner(new File("input.txt"));
    	// for(i=0;i<8;i++){    	
    	// arr[i]=sc.nextInt();
    	// }
        int[] arr={-10,-8,-5,-12,-4,-15,20,50,60,19,18,78,99,100,110,150};
        // for( int ele:arr)
        //     System.out.print(ele+" ");
        // System.out.println();
    	int val=ms.msort(arr,0,arr.length-1);
        System.out.println("value is "+val);
        System.out.println("Sorted output is ");
    	for(j=0;j<arr.length;j++)
    	System.out.print(arr[j]+" ");
    }
    
    int msort(int a[],int p,int r)
    {
        int inv_count=0;
    	if(p<r)
    	{
    		int q=(p+r)/2;
    	inv_count=	msort(a,p,q);
    	inv_count+=	msort(a,q+1,r);
         inv_count+=  merge(a,p,q,r);
    		}      
            return inv_count;      
    }
    	
    int merge(int arr[],int l,int k,int r)
    {
        int i=l,j=k+1;
        int a=0;
        int c=0;
        int[] b=new int[r-l+1];
        while(i<=k && j<=r)
        {
            if(arr[i]<arr[j])
                b[a++]=arr[i++];
            else
            {
                b[a++]=arr[j++];
                c+=(k-i+1);
            }
        }
        while(i<=k)
            b[a++]=arr[i++];
        while(j<=r)
            b[a++]=arr[j++];
        System.arraycopy(b,0,arr,l,b.length);
        return c;
	}
    
}