class SubsetSum
{
    static boolean subsetS(int set[],int sum)
    {
        int n=set.length;
        boolean[][] dp=new boolean[sum+1][n];

        for(int i=0;i<n;i++)
            dp[0][i]=true;
        for(int i=0;i<=sum;i++)
            dp[i][0]=false;
        
        for(int i=1;i<=sum;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i-set[j]>=0)
                    dp[i][j]=(j>=1?dp[i][j-1]:false) || dp[i-set[j]][j];
                else
                    dp[i][j]=(j>=1?dp[i][j-1]:false);
            }
        }
        return dp[sum][n-1];
    }

    public static void main(String[] args)
    {
        int set[]={3,34,4,12,5,2};        
        int sum=9;
        System.out.println("Subset Sum is "+subsetS(set,sum));
    }
}