import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

// Many Parts of this algorithm is inspired by Robert Sedgewick and Kevin Wayne (2020, p.228).
public class NiceSet {

    private int[] sets;
/*     private int[] depth; */

    public NiceSet(int n){
        sets = new int[n];
/*         depth = new int[n]; */
        for(var j = 0; j < n; j++){
            sets[j] = j;
        }
    }
    public void union(int sRoot, int tRoot){
/*         if(depth[tRoot] > depth[sRoot]) sets[sRoot] = tRoot;
        else if(depth[tRoot] < depth[sRoot]) sets[tRoot] = sRoot;
        else{
            sets[sRoot] = tRoot;
            depth[tRoot] += 1;
        } */
        sets[sRoot] = tRoot;
    }
    public int find(int p){
        while(p != sets[p]) p = sets[p]; // Max træeets højde
        return p;
    }
    
    public void move(int pRoot, int p, int qRoot){
        sets[p] = qRoot;
        if(pRoot == -1){ // if root is -1 we need to find a new root
            int newRoot = -1;
            for(var damn = 0; damn < sets.length; damn++){
                if (sets[damn] == p){
                    sets[damn] = (newRoot != -1) ? newRoot : damn;
                    
                    if (newRoot == -1) newRoot = damn;
                }
            }
        }else{
            for(var damn = 0; damn < sets.length; damn++){
                if (sets[damn] == p) sets[damn] = pRoot;
            }
        }
    }

    public static void main(String[] args) {
        
        int n = StdIn.readInt();
        int m = StdIn.readInt();
        NiceSet NS = new NiceSet(n);

        // Check operations
        for(var i = 0; i < m; i++){
            int command = StdIn.readInt();
            int s = StdIn.readInt();
            int t = StdIn.readInt();
            int S = NS.find(s);
            int T = NS.find(t);
            switch(command){
                case 0: // query
                    StdOut.println(S == T ? 1 : 0);
                    break;
                case 1: // union
                    if(S != T){
                        NS.union(S, T);
                    }
                    break;
                case 2: // move
                    if(S != T && S == s) NS.move(-1, s, T); // Check if the number we want to move is pointing to itself s == S, if so, consider the root -1
                    else if(S != T) NS.move(S, s, T);
                    break;
            }
        }

    }
}