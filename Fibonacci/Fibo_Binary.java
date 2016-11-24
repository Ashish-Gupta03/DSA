import java.io.*;
import java.lang.*;
import java.util.*;
class Prog2
{	
	public static void main(String args[])
	{
		BufferedReader br = null;
		try{
			String sCurrentLine;
			
			int n,i=0,num;
			long startTime = System.currentTimeMillis();
			String content = new Scanner(new File("fsb.txt")).useDelimiter("\\Z").next();
		br=new BufferedReader(new InputStreamReader(System.in));
		int testNum=99999;
		long[][] x={{1,1},{1,0}};
		int[] arr=new int[testNum];
		long res;
		
		Random rand=new Random();
		StringBuilder sb=new StringBuilder();
		sb.append(1);
		// for(i=0;i<testNum;i++)
		// {
		// 	num=(int)(Math.random()*1000000)%2;			
		// 	sb.append(num);
		// }
		//System.out.println("num is "+content);
		StringBuilder sb1=new StringBuilder(content);
		//String binaryNum=sb.toString();
		// sb.setLength(0);
		//  for(i=binaryNum.length()-1;i>=0;i--)
		//  {
		//  	binaryNum.charAt(i)
		//  	x[0][0]=1;x[0][1]=1;x[1][0]=1;x[1][1]=0;
		
		//  }


			res=pow(x,sb1.toString(),99);	
		//for(i=testNum-1;i>=0;i--)
			System.out.println(res);
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("time taken in milli "+totalTime);
		
		}
		catch (Exception e){}
	}

	static long pow(long[][] x,String s,int m)
	{
		long[][] y={{1,0},{0,1}};
		int n,i=0,n_end;
		String str;
		int len=s.length();
		n=s.charAt(0)-'0';
		n_end=s.charAt(len-1)-'0';
		while(i<len)
		{
			if(n_end%2==1)
				y=mm(y,x,m);

			 x=mm(x,x,m);
			i++;
			if(len-i-1>=0)
			n_end=s.charAt(len-i-1)-'0';
		}
		return y[1][0];
	}

	static long[][] mm(long[][] y,long[][] x,int m)
	{
 		int yrows = y.length;
      	int ycols = y[0].length;
      	int xrows = x.length;
      	int xcols = x[0].length;
      	long[][] result ={{0,0},{0,0}};

        for (int i=0; i< yrows; i++){
            for (int j=0; j< xcols; j++){
               for (int k=0; k< ycols; k++){               	
                  result[i][j] =( (result[i][j]) + ((y[i][k]) * (x[k][j])) )%m;                                   
            }            
         }
      }
      return result;          		
  	}	
}
