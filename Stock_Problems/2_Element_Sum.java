import java.util.Arrays;
class Prog1
{
	public static void main(String args[])
	{
		int[] arr={56,100,-1000,10000000,99,180,1,-3};
		int x=-1003;
		Arrays.sort(arr);
		int l=0,r=arr.length-1,flag=0;
		while(l<r)
		{
			if(arr[l]+arr[r]==x)
			{
				flag=1;
				System.out.println("arr[l] "+arr[l]+" arr[r] "+arr[r]+" = "+x);
				break;
			}
			else if(arr[l]+arr[r]<x)
				l++;
			else r--;
			System.out.println("l is "+l+" r is "+r);
		}
		if(flag==0)
			System.out.println("Required sum not found");
	}
}