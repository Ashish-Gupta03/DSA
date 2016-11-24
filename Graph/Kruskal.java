import java.util.*;
import java.lang.*;
import java.io.*;
 
/*
4 6
1 2 5
1 3 3
4 1 6
2 4 7
3 2 4
3 4 5
1
*/

class Graph
{
    class Edge implements Comparable<Edge>
    {
        int src, dest, weight;

        public int compareTo(Edge compareEdge)
        {
            return this.weight-compareEdge.weight;
        }
    };
 
    class subset
    {
        int parent, rank;
    };
 
    int V, E;    
    Edge edge[];
 
    Graph(int v, int e)
    {
        V = v;
        E = e;
        edge = new Edge[E];
        for (int i=0; i<e; ++i)
            edge[i] = new Edge();
    }
 
    int find(subset subsets[], int i)
    {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
 
        return subsets[i].parent;
    }
 
    void Union(subset subsets[], int x, int y)
    {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank)
            subsets[xroot].parent = yroot;
        else if (subsets[xroot].rank > subsets[yroot].rank)
            subsets[yroot].parent = xroot;

        else
        {
            subsets[yroot].parent = xroot;
            subsets[xroot].rank++;
        }
    }
 
 	void addEdge(int i,int src,int dest,int wt)
 	{
 		edge[i].src=src;
 		edge[i].dest=dest;
 		edge[i].weight=wt;
 	}
    
    void KruskalMST()
    {
        Edge result[] = new Edge[V];
        int e = 0;
        int i = 0;
        for (i=0; i<V; ++i)
            result[i] = new Edge();
        Arrays.sort(edge);
         
        subset subsets[] = new subset[V];
        for(i=0; i<V; ++i)
            subsets[i]=new subset();
        
        /*Make Set Start*/ 
        for (int v = 0; v < V; ++v)
        {
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }
        /*Make Set End*/ 
 
        i = 0;
 
        while (e < V - 1)
        {
            Edge next_edge = new Edge();
            next_edge = edge[i++];
 
            int x = find(subsets, next_edge.src);
            int y = find(subsets, next_edge.dest);
 
            if (x != y)
            {
                result[e++] = next_edge;
                Union(subsets, x, y);
            }
        }
      
      	int sum=0;
        for (i = 0; i < e; ++i)
        {
        	sum+=result[i].weight;
        }
        System.out.println(sum);
    }
 
    public static void main (String[] args)throws Exception
    {
        BufferedReader br=new BufferedReader(new FileReader("input.txt"));
        String[] strs;
		strs=br.readLine().trim().split("\\s+");
		int n=Integer.parseInt(strs[0]);
		int e=Integer.parseInt(strs[1]);
			
     	Graph g=new Graph(n,e);
		int i,j,k;
		for(j=0;j<e;j++)
		{
			strs=br.readLine().trim().split("\\s+");
			g.addEdge(j,Integer.parseInt(strs[0])-1,Integer.parseInt(strs[1])-1,Integer.parseInt(strs[2]));	
		}
        int src=Integer.parseInt(br.readLine());
        g.KruskalMST();
    }
}