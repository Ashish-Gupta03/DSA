class EditDist
{
	public static void main(String[] args) //throws Exception
	{
		//BufferedReader br=new BufferedReader(new FileReader("input.txt"));
		String str1="ABC";
		String str2="CBA";
		System.out.println(editD(str1,str2,str1.length(),str2.length()));
	}
	static int editD(String str1,String str2,int m,int n)
	{
		int[][] dp=new int[m+1][n+1];
		int i,j;
		System.out.println("m is "+m+" n is "+n);
		for(i=0;i<=m;i++)
		{
			for(j=0;j<=n;j++)
			{
				if(i==0)
					dp[i][j]=j*7;
				else if(j==0) dp[i][j]=i*7;
				else if(str1.charAt(i-1)==str2.charAt(j-1))
					dp[i][j]=dp[i-1][j-1];
				else dp[i][j]= min(dp[i-1][j-1]+10,  //Replace
					dp[i-1][j]+7, //Delete
					dp[i][j-1]+5); //Insert		
			}
		}
		return dp[m][n];
	}
	static int min(int x,int y,int z)
    {
        if (x<y && x<z) return x;
        if (y<x && y<z) return y;
        else return z;
    }
}