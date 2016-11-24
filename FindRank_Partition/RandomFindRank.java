import java.util.*;
import static java.lang.System.out;
public class RandomFindRank
{
	public static void main(String args[])
	{
		int[] arr={12,3, 5, 7,4 ,19,26 };
		int rank=findRank(arr,0,arr.length-1,6);
		for(int i=0;i<arr.length;i++)
			out.print(arr[i]+" ");
		out.println(rank);
	}
	static int findRank(int[] arr,int i,int j,int r)
	{
		int p=rPivot(arr,i,j);
		int k=partition(arr,i,j,p);
		int n=j-k+1;
		if(r==n)
			return arr[p];
		else if(r<n)
			return findRank(arr,k+1,j,r);
		else return findRank(arr,i,k-1,r-n);
	}
	static int rPivot(int arr[], int i, int j)
	{
		Random randomObj = new Random();
    	int pivot;
    	out.println(" i is "+i+" j is "+j);
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