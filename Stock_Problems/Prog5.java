import java.util.Arrays;
import java.util.Collections;
public class Prog5
{
	public static void main(String args[])
	{
		int[] arr={-10,-8,-5,-12,-4,-15,20,50,60,19,18,78,99,100,110,150};
		//Integer[] numbers = { 56,100,-1000,10000000,99,180,1,-3 };

   		int i=0,max=Integer.MIN_VALUE,l=4;
   		for(int j=l+1;j<arr.length;j++)
   		{
   			if(arr[j]-arr[i]>max)
   				max=arr[j]-arr[i];
   			if(arr[j-l]<arr[i])
   				i=j-l;
            //System.out.println("max is "+max+" i is "+i);
   		}
		// if(flag==0)
		System.out.println("Maximum profit is "+max);
	}
}