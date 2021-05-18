import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdIn;

public class Tile {
    public int vertex;
    public int value;

    public Tile(int vertex, int value){
        this.vertex = vertex;
        this.value = value;
    }

    public static boolean legalMove(Tile v, Tile w){
        if(v != null && w != null && v.value != -1 && w.value != -1){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        String[] first = StdIn.readLine().split(" ");
        int R = Integer.parseInt(first[0]);
        int C = Integer.parseInt(first[1]);
        int day = 0;
        int maxDepth = 0;
        Tile[] prevRow = new Tile[C];
        Tile prevTile = null;
        Map<Integer, Tile> tiles = new HashMap<>();
        
        Graph g = new Graph(R * C);

        for (int row = 0; row < R; row++) {
            String[] line = StdIn.readLine().split("");
            for(int col = 0; col < C; col++){

                int tileValue = Integer.parseInt(line[col]);
                Tile v = new Tile((row * C) + col, tileValue);
                tiles.put((row * C) + col, v);
                
                // Adjacent tiles
                Tile w1 = prevTile;
                Tile w2 = null;
                Tile w3 = null;

                if(row > 0){
                    w2 = prevRow[col];
                    w3 = prevRow[col + 1];
                }

                maxDepth = tileValue > maxDepth ? tileValue : maxDepth;

                if(legalMove(v, w1)) g.addEdge(v.vertex, w1.vertex);
                if(legalMove(v, w2)) g.addEdge(v.vertex, w2.vertex);
                if(legalMove(v, w3)) g.addEdge(v.vertex, w3.vertex);

                prevRow[col] = v;
                prevTile = v;
            }
        }

        // Check connected components, must be a single component
        boolean[] marked = new boolean[g.V()];
        int[] id = new int[g.V()];
        int count;
        for(int v = 0; v < g.V(); v++){
            if(!marked[v]){
                marked[v] = true;
                id[v] = count;
                for (int w : G.adj(v)){
                    if (!marked[w] && tiles.get(w) != )
                }
                count++;
            }
        }
        public void dfs(Graph G, int v) {
            marked[v] = true;
            id[v] = count;
            size[count]++;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    dfs(G, w);
                }
            }
        }
    }
}
