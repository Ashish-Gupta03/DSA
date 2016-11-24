import java.util.*;
class CoinChange
{
	public static void main(String args[])
	{
		int[] coins={1,7,10};
		int v=14;
		System.out.println(findMinCoins2D(coins,v));
	}
	static int findMinCoins(int[] coins,int v)
	{
		int[] table=new int[v+1];
		Arrays.fill(table,Integer.MAX_VALUE);
		table[0]=0;
		int i,j;
		for(i=1;i<=v;i++)
		{
			for(j=0;j<coins.length;j++)
			{
				if(coins[j]<=i)
				{
					int res=table[i-coins[j]];
					if(res!=Integer.MAX_VALUE && res+1<table[i])
						table[i]=1+res;
				}				
			}
		}
		return table[v];
	}

	static int findMinCoins2D(int[] coins,int v)
	{
		int n=coins.length;
		int[][] table=new int[v+1][n];		
		for(int i=0;i<n;i++)
			table[0][i]=0;
		int i,j;
		for(i=1;i<=v;i++)
		{
			for(j=0;j<n;j++)
			{
				if(i-coins[j]>=0)				
					table[i][j]=Math.min((j>=1?table[i][j-1]:Integer.MAX_VALUE),table[i-coins[j]][j]+1);
				else 
					table[i][j]=(j>=1?table[i][j-1]:Integer.MAX_VALUE);
			}
		}
		return table[v][n-1];
	}
}