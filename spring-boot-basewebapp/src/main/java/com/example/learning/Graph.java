package com.example.learning;

public class Graph {
	int numOfVertices;//V-> no. of vertices 
	int numOfEdge; //E->no.of edges
	Edge edge[]; // /collection of all edges

	class Edge {
		int src;
		int dest;
	};

	// Creates a graph with V vertices and E edges
	Graph(int v, int e) {
		this.numOfVertices = v;
		this.numOfEdge = e;
		edge = new Edge[numOfEdge];
		for (int i = 0; i < e; ++i) {
			edge[i] = new Edge();
		}
	}

	// A utility function to find the subset of an element i
	int find(int parent[], int index) {
		if (parent[index] == -1) {
			return index;
		}
		return find(parent, parent[index]);
	}

	// A utility function to do union of two subsets
	void Union(int parent[], int x, int y) {
		int xset = find(parent, x);
		int yset = find(parent, y);
		parent[xset] = yset;
	}

	// The main function to check whether a given graph
	// contains cycle or not
	int isCycle( Graph graph) 
    { 
        // Allocate memory for creating V subsets 
        int parent[] = new int[graph.numOfVertices]; 
  
        // Initialize all subsets as single element sets
        //by default all is -1
        for (int i = 0; i < graph.numOfVertices; ++i) {
            parent[i] = -1; 
        }
        // Iterate through all edges of graph, find subset of both 
        // vertices of every edge, if both subsets are same, then 
        // there is cycle in graph. 
        for (int i = 0; i < graph.numOfEdge; ++i) 
        { 
            int x = graph.find(parent, graph.edge[i].src); 
            int y = graph.find(parent, graph.edge[i].dest); 
  
            if (x == y) {
                return 1; 
            }
            graph.Union(parent, x, y); 
        } 
        return 0; 
    }
	
	// Driver Method 
    public static void main (String[] args) 
    { 
        /* Let us create following graph 
         0 
        |  \ 
        |    \ 
        1-----2 */
        int V = 3, E = 3; 
        Graph graph = new Graph(V, E); 
  
        // add edge 0-1 
        graph.edge[0].src = 0; 
        graph.edge[0].dest = 1; 
  
        // add edge 1-2 
        graph.edge[1].src = 1; 
        graph.edge[1].dest = 2; 
  
        // add edge 0-2 
        graph.edge[2].src = 0; 
        graph.edge[2].dest = 2; 
  
        if (graph.isCycle(graph) == 1) {
            System.out.println( "graph contains cycle" ); 
        } else {
            System.out.println( "graph doesn't contain cycle" ); 
        }
    } 
}