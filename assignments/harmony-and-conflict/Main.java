import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.Bipartite;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Main{
    public static void main(String[] args) {
        int vertices = StdIn.readInt();
        int edges = StdIn.readInt();

        // Set up graph
        Graph graph = new Graph(vertices);
        List<int[]> harmonyEdges = new ArrayList<>();

        // State
        boolean harmonyPersists = true;

        for(int i = 0; i < edges; i++){
            int vertexA = StdIn.readInt();
            int vertexB = StdIn.readInt();
            boolean conflicting = StdIn.readInt() == 1 ? true : false;

            // Add edge to graph or harmony list
            if(conflicting)
                graph.addEdge(vertexA, vertexB);
            else{
                harmonyEdges.add(new int[] {vertexA,vertexB});
            }
        }

        Bipartite bi = new Bipartite(graph);

        // Check if for any harmony edge, there might be two with differing colors
        if(bi.isBipartite()){
            for(int[] he : harmonyEdges)
            if(bi.color(he[0]) != bi.color(he[1]))
                harmonyPersists = false;
        }
        else harmonyPersists = false;

        // If graph is biPartite and all harmony eges are satisfied, print 1
        if(bi.isBipartite() && harmonyPersists) StdOut.print(1);
        else StdOut.print(0);
    }
}