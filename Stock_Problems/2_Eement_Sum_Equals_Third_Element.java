import java.util.Arrays;
class Prog2
{
	public static void main(String args[])
	{
		int[] arr={56,100,-1000,10000000,99,180,1,-3};
		int x=10000001;
		Arrays.sort(arr);
		int flag=0;
		for(int k=0;k<arr.length;k++)
		{
			int l=0,r=arr.length-1;
			while(l<r)
			{			
				if(arr[l]+arr[r]==arr[k])
				{
					flag=1;
					System.out.println("arr[l] "+arr[l]+" arr[r] "+arr[r]+" = "+arr[k]);
					break;
				}
				else if(arr[l]+arr[r]<arr[k])
					l++;
				else r--;
			}
		}
		if(flag==0)
			System.out.println("Required sum not found");
	}
}