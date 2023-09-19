import java.util.*;
/*
 * A Directed graph is strongly  connected if the every vertex are reachable from every other vertex
 * In other words, there exists a path from vertex u to vertex v for every pair of vertices (u,v) in the graph.
 * The isStronglyConnected() method checks every vertex one at a time by running the DFS traversal starting from each vertex, 
 * both in the original graph and in its transpose. It marks each visited vertex as "reachable" and checks if all 
 * vertices are reachable. If any vertex is not reachable in any of the traversals, the graph is not strongly connected. 
 * If all vertices are reachable, the graph is strongly connected.
 * */
class StronglyConnectedGraph {
    private int V;
    private List<Integer>[] adj;
    
    StronglyConnectedGraph(int v) {
        V = v; //num of vertices
        adj = new ArrayList[V]; //edges
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<Integer>(); //create the adjacency list for a DIRECTED graph
        }
    }
    
    void addEdge(int u, int v) {
        adj[u].add(v); //DIRECTED graph: only add edges to the vertex that is being pointed towards
    }
    
    void DFSHelper(int v, boolean[] visited) {
        visited[v] = true; 
        for (int i : adj[v]) { //perform DFS search on every vertex to see if all the other nodes are reachable
            if (!visited[i]) {
                DFSHelper(i, visited);
            }
        }
    }
    
    StronglyConnectedGraph getTranspose() {
        StronglyConnectedGraph g = new StronglyConnectedGraph(V); //reverse the graph
        for (int i = 0; i < V; i++) {
            for (int j : adj[i]) {
                g.addEdge(j, i);
            }
        }
        return g;
    }
    
    boolean isStronglyConnected() {
        boolean[] visited = new boolean[V];
        StronglyConnectedGraph g = getTranspose();
        
        // Run DFS traversal from every vertex in the graph
        for (int i = 0; i < V; i++) {
            Arrays.fill(visited, false);
            DFSHelper(i, visited);
            for (boolean b : visited) {
                if (!b) {
                    // If any vertex is not reachable, graph is not strongly connected
                    return false;
                }
            }
        }
        
        // Run DFS traversal from every vertex in the transpose graph
        for (int i = 0; i < V; i++) {
            Arrays.fill(visited, false);
            g.DFSHelper(i, visited);
            for (boolean b : visited) {
                if (!b) {
                    // If any vertex is not reachable, graph is not strongly connected
                    return false;
                }
            }
        }
        
        // If all DFS traversals were successful, graph is strongly connected
        return true;
    }
    
    
    public static void main(String[] args) {
        StronglyConnectedGraph g = new StronglyConnectedGraph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 0);
        System.out.println(g.isStronglyConnected()); // true

        StronglyConnectedGraph g2 = new StronglyConnectedGraph(6);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(2, 4);
        g2.addEdge(3, 1);
        g2.addEdge(3, 5);
        g2.addEdge(4, 5);
        g2.addEdge(5, 0);
        System.out.println(g2.isStronglyConnected()); // false
    }
}
