import java.io.*;
import java.lang.*;
import java.util.*;
class Prog5
{	
	public static void main(String args[])
	{
		BufferedReader br = null;
		try{
			String sCurrentLine;
			
			int n,i=0,num,j=0;
			long startTime = System.currentTimeMillis();
		br=new BufferedReader(new InputStreamReader(System.in));
		String content = new Scanner(new File("fs.txt")).useDelimiter("\\Z").next();
		int testNum=1000;
		long[][] x={{1,1},{1,0}};
		long[] F=new long[1000];
		int res;
		F[0]=0;
		F[1]=1;
		Random rand=new Random();
		StringBuilder sb=new StringBuilder();
		// for(i=0;i<testNum;i++)
		// {
		// 	num=(int)(Math.random()*1000000)%10;
		// 	sb.append(num);
		// }
		
		for(i=2;i<testNum;i++)
		{
			F[i]=(F[i-1]+F[i-2])%100;
		//	System.out.println("F[i] is "+F[i]);
			if(F[i-1]==0 && F[i]==1)
				break;
		}
		System.out.println("Value of i "+(i-1));

			res=compute(content,(i-1));	
			System.out.println(F[res-1]);
		
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("time taken in milli "+totalTime);
		
		}
		catch (Exception e){}
	}

	static int compute(String s,int m)	
	{
		int rem;
		rem=DivideBym(s,m);
		return rem;
	}

		static int DivideBym(String s,int m)
		{
			int rem=0,i=0,num,val;
			int len=s.length();
			StringBuilder sb=new StringBuilder();
			sb.append(s.charAt(i));
			System.out.println("length is "+len);
			num=s.charAt(i)-'0';			
			while(num<m && i<len)
				{
					i++;
					sb.append(s.charAt(i));
					num=rem*10+Integer.parseInt(sb.toString());				
				}
			while(i<len)
			{
				rem=num%m;			
				num=rem*10+s.charAt(i)-'0';
				System.out.println("i is "+i+" num is "+num);
				i++;
			}	
			return num%m;
	}
}
