import java.util.Arrays;
import java.util.*;
 class Stock
{
	public static void main(String args[])
	{
		int[] arr={-10,-8,-5,-50,-4,-15};
		//maximizeProfit(arr);
		spanProblem(arr);		
	}
	static void maximizeProfit(int[] arr)
	{
		int i=0,max=arr[0];
   		for(int j=0;j<arr.length;j++)
   		{
   			if(arr[j]-arr[i]>max)
   				max=arr[j]-arr[i];
   			if(arr[j]<arr[i])
   				i=j;
   		}
		// if(flag==0)
		System.out.println("Maximum profit is "+max);
	}
	static void spanProblem(int arr[])
	{
		Stack<Integer> st=new Stack<Integer>();
		st.push(0);
		int[] S=new int[arr.length];
		int len=arr.length;
		S[0]=1;
		int i=0;
		for(i=1;i<len;i++)
		{
			while(!st.isEmpty() && arr[st.peek()]<=arr[i])
				st.pop();
			S[i]=(st.isEmpty())?(i+1):(i-st.peek());
			st.push(i);
		}
		for(i=0;i<len;i++)
			System.out.print(S[i]+" ");
	}
}