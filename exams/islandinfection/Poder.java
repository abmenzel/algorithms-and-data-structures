package islandinfection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Poder{

    // Possible moves
    int[] dX = {-1,0,1,0};
    int[] dY = {0,1,0,-1};

    public Poder(){}

    private boolean valid(boolean vis[][], int matrix[][], int x, int y){
        if(x < 0 || y < 0 || x >= vis.length || y >= vis[0].length){
            return false;
        }else if(vis[x][y]){
            return false;
        }else if(matrix[x][y] == 0){ // Position is water
            return false;
        }else{
            return true;
        }
    }

    public int podetjek(boolean vis[][], int matrix[][], int human[], int virus[]){
        Queue<int[]> q = new LinkedList<>();
        q.add(human);
        vis[human[0]][human[1]] = true;

        while(!q.isEmpty()){
            int[] pos = q.peek();
            int x = pos[0];
            int y = pos[1];

            if(virus[0] == x && virus[1] == y){
                return 1;
            }

            q.remove();

            for(int i = 0; i < 4; i++){
                int adjx = x + dX[i];
                int adjy = y + dY[i];

                if(valid(vis, matrix, adjx, adjy)){
                    vis[adjx][adjy] = true;
                    int[] newPos = {adjx, adjy};
                    q.add(newPos);
                }
            }
        }

        return 0;
    } 

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        int R = in.nextInt();
        int C = in.nextInt();
        int[][] matrix = new int[C][R];
        boolean[][] vis = new boolean[C][R];
        int[] human = {0,0};
        int[] virus = {0,0};
        Poder philip = new Poder();

        in.nextLine();

        for (int i = 0; i < R;i++){
            String[] line = in.nextLine().split("");

            for (int j = 0; j < C; j++){
                int type = Integer.parseInt(line[j]);
                matrix[j][i] = type;
                if(type == 3){
                    human[0] = j;
                    human[1] = i;
                }
                else if(type == 2){
                    virus[0] = j;
                    virus[1] = i;
                }
            }
        }

        System.out.println(philip.podetjek(vis, matrix, human, virus));

    }
}