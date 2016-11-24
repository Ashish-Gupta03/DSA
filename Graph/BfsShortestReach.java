import java.io.*;
import java.util.*;
class Graph
{
	static int V; 
    static List<Integer>[] adj;  
	static int[] distance;
	public Graph(int v)
	{
		this.V=v;
		adj=new List[v+1];
		distance=new int[v+1];
		Arrays.fill(distance,-1);
		for(int i=1;i<=v;i++)
		{
			adj[i]=new ArrayList<Integer>();
		}
	}
	public void addEdge(int u,int v)
	{
		adj[u].add(v);
		adj[v].add(u);
	}

	void BFS(int u)
	{
		boolean[] visited=new boolean[V+1];
		Queue<Integer> q=new LinkedList<>();
		q.add(u);
		visited[u]=true;
		distance[u]=0;
		while(!q.isEmpty())
		{
			Integer in=q.poll();			
			for(int i:adj[in])	
			{
				if(!visited[i])
				{				
					visited[i]=true;	
					q.add(i);
					distance[i]=distance[in]+6;
				}
			}
		}
	}

	public static void main(String args[])throws Exception
	{
		BufferedReader br=new BufferedReader(new FileReader("input.txt"));
		int numOfGraphs=Integer.parseInt(br.readLine());
		int i;
		while(numOfGraphs-->0)
		{
			String[] strs;
			strs=br.readLine().split(" ");
			int n=Integer.parseInt(strs[0]);
			int e=Integer.parseInt(strs[1]);
			
			Graph g=new Graph(n);
			int j,k;
			for(j=0;j<e;j++)
			{
				strs=br.readLine().trim().split("\\s+");
				g.addEdge(Integer.parseInt(strs[0]),Integer.parseInt(strs[1]));	
			}
			int src=Integer.parseInt(br.readLine());
			g.BFS(src);
			//printG(n);
			for(i=1;i<=n;i++)
			{
				if(i!=src)
					System.out.print(distance[i]+" ");
			}
			System.out.println();

		}
	}	
}