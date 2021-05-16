import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdIn;

public class Island{
    public static void main(String[] args) {
        int R =  StdIn.readInt();
        int C = StdIn.readInt();
        StdIn.readLine();

        Graph g = new Graph(R*C);
        int[][] grid = new int[R][C];

        for (int i = 0; i < R; i++){
            String[] line = StdIn.readLine().split("");
            for (int j = 0; j < line.length; j++){
                grid[i][j] = Integer.parseInt(line[j]);
            }
        }

        for(int i = 0; i < R; i++){
            for (int j = 0; j < C; j++){
                
            }
        }

    }
}