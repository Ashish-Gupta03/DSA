public class Prog2
{
	public static void main(String args[])
	{
		int[] arr={6,-2,5,4,-3,1,1,-2,8,9,-8};//{-2,-3,4,-1,-1,1,-1,-3,3,8};//{-10,-8,-5,-4,-15,20,50,60,19,-278,18,150,120,-100,110,150};		
      exactlyL(arr);
      atLeastL(arr);
      atMostL(arr);
   }
   static void exactlyL(int[] arr)
   {
      int sum=0,i=0;
   	int j=0,max=Integer.MIN_VALUE,l=4,k=0,m=0;
   		for(j=0;j<arr.length;j++)
   		{            
   			sum+=arr[j];
            
            if(j==(i+l-1))
            {
               if(sum>max)
            {
               max=sum;
               k=j;
               m=i;
            }
               sum=sum-arr[i];               
               i=i+1;                           
            }
            if(sum<0)
            {
               if(l<2)
               sum=0;
               //i=j+1;
            }
   			
   			
            System.out.println("i is "+i+" j is "+j+" sum "+sum);
   		}
         System.out.println("Maximum subarray is from "+m+" to "+k+" with sum "+max);
	}
   static void atLeastL(int[] arr)
   {
      int sum=0,i=0,prevSum=0;
      int j=0,max=Integer.MIN_VALUE,l=3,k=0,m=0;
      for(j=0;j<l;j++)
         prevSum+=arr[j];

      sum=prevSum;
         for(j=l;j<arr.length;j++)
         {       
            sum+=arr[j];  
            prevSum=prevSum+arr[j]-arr[j-l];
            System.out.println("curr sum "+sum);
            if(prevSum>sum)
            {
               sum=prevSum;
               k=j;
               m=j-l;
            }
            else
            {

               k=j;
            }            
            //System.out.println("i is "+i+" j is "+j+" sum "+sum);
         }
         System.out.println("Maximum subarray is from "+m+" to "+k+" with sum "+sum);
   }
   static void atMostL(int[] arr)
   {
      //int[] arr={1,2,4,5,6,2,2,3,4,5,6,9};//{5,-3,2,-1,6,8,-7};
      int sum=0,i=0,min=1;
      int j=0,max=Integer.MIN_VALUE,l=4,k=0,m=0;
         for(j=0;j<arr.length;j++)
         {   
            if(j>=(i+l-1))
            {
               sum=0;
               if(l>1)
                  j--;
               i=j+1;            
            }         
            sum+=arr[j];
            if(sum>max)
            {
               max=sum;
               k=j;
               m=i-1;
            }
            if(sum<0)
            {
               sum=0;              
            }           
            System.out.println("i is "+i+" j is "+j+" sum "+sum);
         }
         System.out.println("Maximum subarray sum from "+m+" to "+k+" with sum "+max);
   }
}