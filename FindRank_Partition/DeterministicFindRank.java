import java.util.*;
import static java.lang.System.out;
class DeterministicFindRank
{
	public static void main(String args[])
	{
		int[] arr={19,3,15,7,4,29,35,20,40,27,25,12,26,38,45 };		
		int rank=findRank(arr,0,arr.length-1,15);
		System.out.println(rank);
	}
	static int findRank(int[] arr,int i,int j,int r)
	{			 
			int p=goodPivot(arr,i,j);
			int k=partition(arr,i,j,p);
			int n=j-k+1;
			if(r==n){
				return p;
			}
			else if(r<n)
				return findRank(arr,k+1,j,r);
			else return findRank(arr,i,k-1,r-n);		
	}
	
	static int findMedian(int[] arr,int n)
	{
		Arrays.sort(arr);
		if(arr.length==1)
			return arr[0];
		else
		return arr[(arr.length-1)/2];
	}
	
	static int goodPivot(int[] arr,int start,int end)
	{
			int n=end-start+1;
			int k;
			int groups=n/5;
			if(n%5>0) groups++;
			int[] medOfMed=new int[groups];

			for(k=0;k<groups;k++)
			{
				int[] parts;
				if(n%5>0)
				{
					if(k==groups-1)
						parts=new int[n%5];
					else
						parts=new int[5];
				}
				else
				{
					parts=new int[5];
				}

				for(int l=0;l<parts.length;l++)
				{
					parts[l]=arr[l+5*k+start];
				}				
				medOfMed[k]=findMedian(parts,parts.length);
			}
			int medOfMeds=(k==1)?medOfMed[k-1]:goodPivot(medOfMed,0,medOfMed.length-1);
			return medOfMeds;
	}

	static int partition(int[] arr,int start,int end,int p)
	{
		int x=-1;
		int k;		
		int i=start;
		int j=end;
		int len=arr.length;
		while(i<j)
		{
			while(i<len && arr[i]<=p)
				i++;
			
			while(j>=0 && arr[j]>p)
				j--;

			if(i>=arr.length)i--;
			if(j<0)j=0;
			if(i<j)
				swap(arr,i,j);		
		}
		for(k=start;k<=end;k++)
		{
			if(arr[k]==p)
			{
				x=k;
				break;
			}			
		}
		if(x!=-1)
		swap(arr,x,j);
		return j;
	}

	static void swap(int[] arr,int i,int j)
	{
		int temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}
}