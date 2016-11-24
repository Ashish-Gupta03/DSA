class CountWaysToChange
{
    static int change(int S[],int m,int n)
    {
        int[][] dp=new int[n+1][m];

        for(int i=0;i<m;i++)
            dp[0][i]=1;
        
        for(int i=1;i<n+1;i++)
        {
            for(int j=0;j<m;j++)
                dp[i][j]=(j>=1?dp[i][j-1]:0)+(i-S[j]>=0?dp[i-S[j]][j]:0);
        }
        return dp[n][m-1];
    }

    public static void main(String[] args)
    {
        int arr[]={1,7,10};        
        System.out.println("No. of Denominations for given value is "+change(arr,arr.length,14));
    }
}