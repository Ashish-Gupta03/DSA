class Knapsack01
{
    /*Duplicates not allowed*/
    static int knapsackWithoutDup(int W,int[] wt,int[] val)
    {
        int n=val.length;
        int[][] dp=new int[n+1][W+1];
        for(int i=0;i<=n;i++)
        {
            for(int j=0;j<=W;j++)
            {
                if(i==0||j==0)
                    dp[i][j]=0;
                else if(j-wt[i-1]>=0)
                    dp[i][j]=Math.max(val[i-1]+dp[i-1][j-wt[i-1]], dp[i-1][j]);
                else
                    dp[i][j]=dp[i-1][j];
            }
        }
        return dp[n][W];
    }

    /*Duplicates allowed*/
    static int knapsackWithDup(int W,int[] wt,int[] val)
    {
        int n=wt.length;
        int[][] dp=new int[W+1][n];

        for(int i=0;i<n;i++)
            dp[0][i]=0;
        for(int i=0;i<=W;i++)
            dp[i][0]=0;

        for(int i=1;i<=W;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i-wt[j]>=0)
                    dp[i][j]=Math.max(val[j]+dp[i-wt[j]][j], (j>=1?dp[i][j-1]:0));
                else
                    dp[i][j]=(j>=1?dp[i][j-1]:0);
            }
        }        
        return dp[W][n-1];
    }

    public static void main(String[] args)
    {
        int val[]={60,100,120};
        int wt[]={10,20,30};
        int W=50;
        System.out.println("Maximum value is "+knapsackWithDup(W,wt,val));
    }
}