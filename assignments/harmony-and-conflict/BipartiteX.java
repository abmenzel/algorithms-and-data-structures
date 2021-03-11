import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.Bipartite;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * The BipartiteX class is borrowed from Sedgewick and Wayne.
 * This has been modified to consider harmony edges.
 */

/**
 *  The {@code BipartiteX} class represents a data type for 
 *  determining whether an undirected graph is <em>bipartite</em> or whether
 *  it has an <em>odd-length cycle</em>.
 *  A graph is bipartite if and only if it has no odd-length cycle.
 *  The <em>isBipartite</em> operation determines whether the graph is
 *  bipartite. If so, the <em>color</em> operation determines a
 *  bipartition; if not, the <em>oddCycle</em> operation determines a
 *  cycle with an odd number of edges.
 *  <p>
 *  This implementation uses <em>breadth-first search</em> and is nonrecursive.
 *  The constructor takes &Theta;(<em>V</em> + <em>E</em>) time in
 *  in the worst case, where <em>V</em> is the number of vertices
 *  and <em>E</em> is the number of edges.
 *  Each instance method takes &Theta;(1) time.
 *  It uses &Theta;(<em>V</em>) extra space (not including the graph).
 *  See {@link Bipartite} for a recursive version that uses depth-first search.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a>   
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class BipartiteX {
    private static final boolean WHITE = false;

    private boolean isBipartite;   // is the graph bipartite?
    private boolean[] color;       // color[v] gives vertices on one side of bipartition
    private boolean[] marked;      // marked[v] = true iff v has been visited in DFS
    private int[] edgeTo;          // edgeTo[v] = last edge on path to v
    private Map<Integer,Set<Integer>> harmonyEdges;

    public BipartiteX(Graph G, Map<Integer,Set<Integer>> harmonyEdges) {
        isBipartite = true;
        color  = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.harmonyEdges = harmonyEdges;
        
        for (int v = 0; v < G.V() && isBipartite; v++) {
            if (!marked[v]) {
                bfs(G, v);
            }
        }
    }

    private boolean isHarmony(int w, int v){
        if (harmonyEdges.containsKey(w) && harmonyEdges.get(w).contains(v)) {
            return true;
        }
        else{
            return false;
        }
    }

    private void bfs(Graph G, int s) { 
        Queue<Integer> q = new Queue<Integer>();
        color[s] = WHITE;
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    if(isHarmony(w,v)){
                        color[w] = color[v];
                    }else{
                        color[w] = !color[v];
                    }
                    q.enqueue(w);
                }
                else if (!isHarmony(w,v) && color[w] == color[v]) {
                    // tjek at w og v ikke er harmony edge
                    isBipartite = false;
                    return;
                }
            }
        }
    }

    public boolean isBipartite() {
        return isBipartite;
    }
    
     public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstLine = reader.readLine();
        int vertices = Integer.parseInt(firstLine.split(" ")[0]);
        int edges = Integer.parseInt(firstLine.split(" ")[1]);
        Map<Integer,Set<Integer>> harmonyEdges = new HashMap<>();

        // Set up graph
        Graph graph = new Graph(vertices);

        for(int i = 0; i < edges; i++){
            String line = reader.readLine();
            int vertexA = Integer.parseInt(line.split(" ")[0]);
            int vertexB = Integer.parseInt(line.split(" ")[1]);
            boolean conflicting = Integer.parseInt(line.split(" ")[2]) == 1 ? true : false;
            
            // Add edge to graph or harmony list
            graph.addEdge(vertexA, vertexB);

            if(!conflicting){
                if(harmonyEdges.containsKey(vertexA)){
                    harmonyEdges.get(vertexA).add(vertexB);
                }else{
                    harmonyEdges.put(vertexA,new HashSet<>(Arrays.asList(vertexB)));
                }
                if(harmonyEdges.containsKey(vertexB)){
                    harmonyEdges.get(vertexB).add(vertexA); 
                }else{
                    harmonyEdges.put(vertexB,new HashSet<>(Arrays.asList(vertexA)));
                }
            }
        }

        BipartiteX bi = new BipartiteX(graph, harmonyEdges);

        // If graph is biPartite and all harmony eges are satisfied, print 1
        if(bi.isBipartite()) StdOut.print(1);
        else StdOut.print(0);
    }
}