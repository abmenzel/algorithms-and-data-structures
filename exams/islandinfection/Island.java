import edu.princeton.cs.algs4.BreadthFirstPaths;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdIn;

public class Island{
    
    public static boolean legalMove(int v, int w){
        if(v == 0 || w == 0){
            return false;
        }else{
            return true;
        }
    }
    public static void main(String[] args) {
        int R =  StdIn.readInt();
        int C = StdIn.readInt();
        StdIn.readLine();

        Graph g = new Graph(R*C);
        BreadthFirstPaths bfs;
        int[] prevRow = new int[C];
        int prevCol = 0;
        int human = 0;
        int virus = 0;

        for (int row = 0; row < R; row++){
            String[] line = StdIn.readLine().split("");
            for (int col = 0; col < line.length; col++){
             
                // 0: number 1: value
                int[] v = {(row * C) + col, Integer.parseInt(line[col])};
                int[] w1 = {((row-1) * C) + col, prevRow[col]}; // vertex above current
                int[] w2 = {(row * C) + col-1, prevCol}; // vertex left of current

                // Add edge link to above vertex if we are not on first row
                if(row > 0 && legalMove(v[1], w1[1])){
                    g.addEdge(v[0], w1[0]);
                }

                // Add edge link to left vertex if we are not on first index
                if(col > 0 && legalMove(v[1], w2[1])){
                    g.addEdge(v[0], w2[0]);
                }

                if(v[1] == 3) human = v[0];
                if(v[1] == 2) virus = v[0];

                prevRow[col] = v[1];
                prevCol = v[1];
            }
        }

        bfs = new BreadthFirstPaths(g, virus);
        System.out.println(bfs.hasPathTo(human) ? 1 : 0);

    }
}