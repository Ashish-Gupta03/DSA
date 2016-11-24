class Prog3
{
	public static void main(String args[])
	{

		int[] arr={-2,-3,4,-1,-1,1,1,1,1,-1,-3,3,8};//{1, 11, 100, 1, 0, 200, 3, 2, 1, 250};//{1,4,4};//{4,-3,2,-1,6,8,-7};
     System.out.println(minSubArrayLen(11,arr));          
	}
   public static int minSubArrayLen(int s, int[] nums) {
        if(nums.length==0)
        return 0;
        int sum=nums[0],i=0;
        int j=0,min=Integer.MAX_VALUE;
        int len=nums.length;
        for(j = 1; j <= len; j++)
        {
            while(sum >= s && i <= j - 1)
            {
                System.out.println("min curr "+min+"  i is"+i+" (j-1) is "+(j-1));
                if(min > (j - i))
                    min = j - i;
                sum = sum - nums[i];
                i++;
                System.out.println("sum is "+sum);
                while(nums[i]<=0 && i <= j - 1)
                {
                System.out.println("min curr "+min+"  i is"+i+" (j-1) is "+(j-1));
                if(min > (j - i))
                    min = j - i+1;
                sum = sum - nums[i];
                i++;
                System.out.println("sum is "+sum);
                }
            }
            
            if(sum<0)
            {
                sum=0;
                i=j;
            }
            if(j < len)
                sum += nums[j];
        }
        if(min==Integer.MAX_VALUE)
          return 0;
        else
          return min;  
    }
}