class MatrixChain
{
    static int MatrixChainOrder(int p[],int n)
    {
        int j,k,q;
        int[][] m=new int[n][n];
        for(int i=1;i<n;i++)
           m[i][i]=0;
        for(int l=2;l<n;l++)
        {
            for(int i=1;i<n-l+1;i++)
            {
                j=i+l-1;
                m[i][j]=Integer.MAX_VALUE;
                for (k=i; k<=j-1; k++)
                {
                    q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if (q < m[i][j])
                        m[i][j] = q;
                }
            }
        }
        return m[1][n-1];
    }

    public static void main(String[] args)
    {
        int p[]={30,35,15,5,10,20,25};
        int n=p.length;
        System.out.println("Minimum number of multiplications is "+MatrixChainOrder(p,n));
    }
}