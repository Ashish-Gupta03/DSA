import java.io.*;
import java.util.*;
class HeapSort
{
	public static void main(String args[]) throws Exception
	{
		 String content = new Scanner(new File("IntegerArray.txt")).useDelimiter("\\Z").next();
		 //StringTokenizer st = new StringTokenizer(content);
		 String[] strs=content.trim().split("\\r?\\n");
		 int len=strs.length;//st.countTokens();
		 int[] arr=new int[len];
		 int i;
		 for(i=0;i<len;i++)
		 {
		 	//System.out.println(strs[i]);
		 	arr[i]=Integer.parseInt(strs[i]);
		 }		 	
		// int[] arr=content.//{92,-1,6,-8,9,11,99,10,59,56,68,86,85,90,69,99};
		// int len=arr.length;
		 int k=10000;
		 buildMaxHeap(arr,k);
		 System.out.println("Smallest k elements are is ");
		 for( i=0;i<k;i++)
		 	System.out.print(arr[i]+" ");
	}
	static void buildMaxHeap(int[] arr,int k)
	{
		int i,len=arr.length;
		for(i=k/2-1;i>=0;i--)
			MaxHeapify(arr,i,k);

		for(i=k;i<len;i++)
		{
			if(arr[0]>arr[i])
			{
				arr[0]=arr[i];
				MaxHeapify(arr,0,k);
			}
		}

		for(i=k-1;i>=0;i--)
		{
			swap(arr,0,i);
			MaxHeapify(arr,0,i);
		}
	}
	static void MaxHeapify(int[] arr,int i,int heapSize)
	{
		int largest;
		int l=2*i+1;
		int r=2*i+2;
		if(l<heapSize && arr[l]>arr[i])		
			largest=l;		
		else
			largest=i;
		if(r<heapSize && arr[r]>arr[largest])
			largest=r;
		if(largest!=i)
		{
			swap(arr,i,largest);
			MaxHeapify(arr,largest,heapSize);
		}
	}
	static void swap(int[] arr,int i,int j)
	{
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
}