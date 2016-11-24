import java.util.*;
import java.io.*;
import static java.lang.System.out;
 class Solution
{
	public static void main(String args[])throws Exception
	{
		 String content = new Scanner(new File("IntegerArray.txt")).useDelimiter("\\Z").next();
		 StringTokenizer st = new StringTokenizer(content);
		 String[] strs=content.trim().split("\\r?\\n");
		 int len=st.countTokens();
		 int[] arr=new int[len];
		 int i;
		 for(i=0;i<len;i++)
		 {
		 	//System.out.println(strs[i]);
		 	arr[i]=Integer.parseInt(strs[i]);
		 }
		//int[] arr={92,-1,6,-8,9,11,99,10,59,56,68,86,85,90,69,-4,-5,3,8};
		int k=10000;
		//int rank=findRank(arr,0,arr.length-1,4);
		buildMaxHeap(arr,k);
		//out.println(rank);
	}

	static void buildMaxHeap(int[] arr,int k)
	{
		int i,len=arr.length;
		int[] res=new int[2*k];
		int temp=k;
		int size=2*k-1;
		System.arraycopy(arr,0,res,0,size+1);		
		int pos=findRank(res,0,size,k+1);
		
		int index=pos;
		
		i=size+1;
		while(i<len)
		{	
			if(i<=(len-1) && (index)<=size)
			{
				res[index++]=arr[i++];
			}

			if(index==(size+1) || i==len-1)
			{
				k=temp;
				index=findRank(res,0,res.length-1,k+1);				
			}
		
		}
		 int element=findExactRank(res,0,index-1,index-k+1);
		  out.println("Smallest array is ");
		  for(i=0;i<k;i++)
		  	out.print(res[i]+" ");		
	}

	static int findRank(int[] arr,int i,int j,int r)
	{
		int p=rPivot(arr,i,j);
		int k=partition(arr,i,j,p);
		int n=j-k+1;
		if(n<=r)
		{
			int m;
			return j-n+2;
		}
		else
			return findRank(arr,k+1,j,r);		
	}
	static int findExactRank(int[] arr,int i,int j,int r)
	{
		int p=rPivot(arr,i,j);
		int k=partition(arr,i,j,p);
		int n=j-k+1;
		if(r==n)
			return arr[p];
		else if(r<n)
			return findExactRank(arr,k+1,j,r);
		else return findExactRank(arr,i,k-1,r-n);
	}
	static int rPivot(int arr[], int i, int j)
	{
		Random randomObj = new Random();
    	int pivot;
    	if(i<j)
    	pivot = randomObj.nextInt((j - i)) + i;
    	else
    		pivot =i;
    	return pivot;
	}
	static int partition(int[] arr,int start,int end,int p)
	{
		int x=arr[p];
		int i=start;
		int j=end;
		while(i<j)
		{
			while(i<arr.length && arr[i]<=x)
				i++;
			
			while(j>=0 && arr[j]>x)
				j--;
			if(i>=arr.length)i--;
			if(j<0)j=0;
			if(i<j)
				swap(arr,i,j);			
		}
		int temp=-1,k;
		for(k=start;k<=end;k++)
		{
			if(arr[k]==x)
			{
				temp=k;
				break;
			}		
		}
		if(temp!=-1)
		swap(arr,temp,j);
		return j;
	}

	static void swap(int[] arr,int i,int j)
	{
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
}