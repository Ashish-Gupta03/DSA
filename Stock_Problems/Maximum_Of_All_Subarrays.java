import java.util.*;
import java.lang.*;
import java.io.*;
class Main
{
   public static void main (String[] args) throws java.lang.Exception
   {
      maxOfAllSubArrays();
   }
   static void maxOfAllSubArrays()throws IOException
   {
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      int n=Integer.parseInt(br.readLine());
      String sCurrentLine;
      String[] strs;    
         int[] arr=new int[n];   
         sCurrentLine=br.readLine();
         strs=sCurrentLine.trim().split("\\s+");            
         int i=0,j=1;
         for(i=0;i<n;i++)
         {
            arr[i]=Integer.parseInt(strs[i]);
         } 
         //int arr[]={-2,-3,-4,-5,-6,-7};
         //int n=arr.length;
      int k =Integer.parseInt(br.readLine());//3;
      Deque dq=new LinkedList();

      for(i=0;i<k;i++)
      {
         while(!dq.isEmpty() && arr[i]>=arr[(int)dq.peekLast()])
            dq.pollLast();
         dq.add(i);
      }
      for(;i<n;i++)
      {
         System.out.print(arr[(int)dq.peekFirst()]+" ");

         while(!dq.isEmpty() && (int)dq.peekFirst()<=(i-k))
            dq.pollFirst();
         while(!dq.isEmpty() && arr[i]>=arr[(int)dq.peekLast()])
            dq.pollLast();
         dq.add(i);
      }
      System.out.print(arr[(int)dq.peekFirst()]);
   }
}