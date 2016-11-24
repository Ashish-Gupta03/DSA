import java.util.Arrays;
import java.util.Collections;
class Prog3
{
	public static void main(String args[])
	{
		Integer[] arr={56,100,-1000,10000000,99,180,1,-3};
		//Integer[] numbers = { 56,100,-1000,10000000,99,180,1,-3 };

   		int min = (int) Collections.min(Arrays.asList(arr));
    	int max = (int) Collections.max(Arrays.asList(arr));
    	int maxIndex=Arrays.asList(arr).indexOf(max);
    	int minIndex=Arrays.asList(arr).indexOf(min);
    	System.out.println("max index "+maxIndex+" min index "+minIndex+" max-min is "+(max-min));
		// int flag=0;
		// for(int k=0;k<arr.length;k++)
		// {
		// 	int l=0,r=arr.length-1;
		// 	while(l<r)
		// 	{			
		// 		if(arr[l]+arr[r]==arr[k])
		// 		{
		// 			flag=1;
		// 			System.out.println("arr[l] "+arr[l]+" arr[r] "+arr[r]+" = "+arr[k]);
		// 			break;
		// 		}
		// 		else if(arr[l]+arr[r]<arr[k])
		// 			l++;
		// 		else r--;
		// 		//System.out.println("l is "+l+" r is "+r);
		// 	}
		// 	if(flag==1)
		// 		break;
		// }
		// if(flag==0)
		// 	System.out.println("Required sum not found");
	}
}