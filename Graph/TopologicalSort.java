import java.io.*;
import java.util.*;
/*
6 6
5 2
5 0
4 0
4 1
2 3
3 1
//
5 5
1 0
0 2
2 1
0 3
3 4
*/
class Graph
{
	static int V; 
    static List<Integer>[] adj;
    static List<Integer>[] adjR;  
	static boolean[] visited;
	static Graph g;

	public Graph(int v)
	{
		this.V=v;
		adj=new List[v];
		adjR=new List[v];
		for(int i=0;i<v;i++)
		{
			adj[i]=new ArrayList<Integer>();
			adjR[i]=new ArrayList<Integer>();
		}
	}
	public void addEdge(int u,int v)
	{
		adj[u].add(v);
		adjR[v].add(u);
	}

	void DFS_VISIT(int u,Stack<Integer> s)
	{		
		visited[u]=true;
		//System.out.println("u is "+u);
		for(Integer i:adj[u])
		{
			if(!visited[i])
				DFS_VISIT(i,s);
		}
		s.push(u);
	}

	void DFS_UTIL(int u)
	{		
		visited[u]=true;
		System.out.print(u+" ");
		//System.out.println("u is "+u);
		for(Integer i:adjR[u])
		{
			if(!visited[i])
				DFS_UTIL(i);
		}
	}
    
    void printSCC(Stack<Integer> s)
    {
    	visited=new boolean[V];

    	System.out.println(Arrays.toString(adjR));
    	System.out.println(Arrays.toString(s.toArray()));
    	while(!s.isEmpty())
    	{
    		Integer i=s.pop();
    		if(!visited[i])
    		{
    			DFS_UTIL(i);
    			System.out.println();
    		}	
    	}
    }

	public static void main(String args[])throws Exception
	{
		BufferedReader br=new BufferedReader(new FileReader("input.txt"));
		int i;
			String[] strs;
			strs=br.readLine().trim().split("\\s+");
			int n=Integer.parseInt(strs[0]);
			int e=Integer.parseInt(strs[1]);			
            
			g=new Graph(n);
			visited=new boolean[n];
			int j,k;
			for(j=0;j<e;j++)
			{
				strs=br.readLine().trim().split("\\s+");
				g.addEdge(Integer.parseInt(strs[0]),Integer.parseInt(strs[1]));	
			}
			Stack<Integer> s=new Stack<>();
			for(i=0;i<n;i++)
			{
				if(!visited[i])
					g.DFS_VISIT(i,s);
			}
			/*SCC print*/
			g.printSCC(s);
			/*SCC print*/

			/*Topological Sort print start*/
			//while(!s.isEmpty())
			//	System.out.print(s.pop()+" ");
			/*Topological Sort print end*/
	}	
}