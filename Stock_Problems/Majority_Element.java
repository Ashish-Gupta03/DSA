import java.util.Arrays;
import java.util.Collections;
public class Prog7
{
	public static void main(String args[])
	{
		int[] arr={1,1,1,1,1,1,-1,-1,-1,-1,-1,-1,-1,6,4,5,7,8,10,12};
      int[] maj=new int[2];
      int c1,c2;
      c1=0;c2=0;

   	int i=0,max=arr[0];
   		for(int j=0;j<arr.length;j++)
   		{
   			if(c1>0 && maj[0]==arr[j])
               c1++;
            else if(c2>0 && maj[1]==arr[j])
               c2++;
            else if(c1==0)
            {
               c1=1;
               maj[0]=arr[j];
            }
            else if(c2==0)
            {
               c2=1;
               maj[1]=arr[j];
            }
            else
            {
               c1--;
               c2--;
            }
   		}
         int count1,count2;
         count1=0;count2=0;

         for(int j=0;j<arr.length;j++)
         {
            if(arr[j]==maj[0])
               count1++;
            else if(arr[j]==maj[1])
               count2++;
         }
         if(count1>=(arr.length/3))
            System.out.println("maj1 is "+maj[0]);
         if(count2>=(arr.length/3))
            System.out.println("majority is "+maj[1]);
	}
}