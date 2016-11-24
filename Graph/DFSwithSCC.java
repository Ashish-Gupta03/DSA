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
	int time;	
    static List<Integer>[] adj;  
    static List<Integer>[] adjR;  
	static boolean[] visited;
	static Graph g;
	static int[] start,end;
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

	void DFS_VISIT(int u,int[] start,int[] end,LinkedList<Integer> lst,List<Integer>[] adj)
	{		
		visited[u]=true;
		time+=1;
		start[u]=time;
		for(Integer i:adj[u])
		{
			if(!visited[i])
				DFS_VISIT(i,start,end,lst,adj);
		}
		time+=1;
		end[u]=time;
		lst.add(0,u);
	}	
    
    void printSCC(LinkedList<Integer> lst)
    {    	
    	visited=new boolean[V];
    	
    	for(int i=0;i<lst.size();i++)
    	{
    		if(!visited[lst.get(i)])
    		{
    			LinkedList<Integer> lstN=new LinkedList<>();
    			DFS_VISIT(lst.get(i),start,end,lstN,adjR);
    			System.out.println(Arrays.toString(lstN.toArray()));
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
			start=new int[n];
			end=new int[n];
			LinkedList<Integer> lst=new LinkedList<>();
			for(i=0;i<n;i++)
			{
				if(!visited[i])
					g.DFS_VISIT(i,start,end,lst,adj);
			}
			
			//Arrays.sort(end);
			for(i=0;i<lst.size();i++)
				System.out.print(lst.get(i)+" ");
			System.out.println();
			/*SCC print*/
			g.printSCC(lst);
			/*SCC print*/
	}	
}