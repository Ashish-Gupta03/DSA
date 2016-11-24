class Prog1
{
	public static void main(String args[])
	{
		int[] arr={-10,-8,-5,-12,-4,-15,20,50,60,19,18,78,99,-100,110,150};
      int sum=0,i=0;
   	int j=0,max=0;
   		for(j=0;j<arr.length;j++)
   		{
   			sum+=arr[j];
   			if(sum>max)
   				max=sum;
   			if(sum<0)
   			{
   				sum=0;
   				i=j+1;
   			}
   		}
         System.out.println("Maximum subarray is from "+i+" to "+(j-1)+" with sum "+max);
	}
}