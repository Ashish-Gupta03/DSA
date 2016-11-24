import java.io.*;
import java.lang.*;
import java.util.*;
class Prog1
{	
	public static void main(String args[])
	{
		BufferedReader br = null;
		try{
			String sCurrentLine;
			
			int n,i=0,num;
			long startTime = System.currentTimeMillis();
		br=new BufferedReader(new InputStreamReader(System.in));
		String content = new Scanner(new File("fs.txt")).useDelimiter("\\Z").next();
		//int testNum=1000000;
		long[][] x={{1,1},{1,0}};
		//int[] arr=new int[testNum];
		long res;
		Random rand=new Random();
		StringBuilder sb=new StringBuilder();
		// for(i=0;i<testNum;i++)
		// {
		// 	num=(int)(Math.random()*1000000)%10;
		// 	sb.append(num);
		// }
		// //System.out.println("num is "+sb);
			res=pow(x,content,99);	
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
		while(n>0)
		{
			if(n_end%2==1)
				y=mm(y,x,m);
			
			x=mm(x,x,m);
			str=DivideBy2(s);
			s=new String(str);
			len=s.length();
			if(len>0)
			{			
				n=s.charAt(0)-'0';
				n_end=s.charAt(len-1)-'0';			
			}
			else
			{
				n=0;
			}
		}
		return y[1][0];
	}

	static String DivideBy2(String s)
	{
		int carry=0,i=0,num,val;
		int len=s.length();
		StringBuilder sb=new StringBuilder();
		while(i<len)
		{
			num=carry*10+s.charAt(i)-'0';
			if(num%2==1)
			{
				val=num/2;
				carry=1;
				sb.append(val);
			}
			else
			{
				val=num/2;
				carry=0;
				sb.append(val);
			}
			i++;
		}
		if(sb.charAt(0)=='0')
		sb.deleteCharAt(0);
		return sb.toString();
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
