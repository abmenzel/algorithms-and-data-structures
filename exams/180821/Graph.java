import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Graph {
    Map<Integer, Set<Integer>> adjacentVertices;
    Map<Integer[], Integer> edgeWeight;

    public Graph(int[][] edges){
        adjacentVertices = new HashMap<>();
        edgeWeight = new HashMap<>();
        
        for(int[] edge : edges){
            Integer u = edge[0];
            Integer v = edge[1];
            Integer[] pair = {u,v};
            Integer[] reversePair = {v,u};
            Integer weight = edge[2];

            // Add adjacent
            if(adjacentVertices.containsKey(u)){
                adjacentVertices.get(u).add(v);
            }else{
                adjacentVertices.put(u, (new HashSet<>()));
                adjacentVertices.get(u).add(v);
            }

            // Add weight
            edgeWeight.put(pair,weight);
            edgeWeight.put(reversePair, weight); 
        }
        // Build adjacent list
        // Build build weightList
    }

    public Integer distance(Integer u, Integer v){
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        q.add(u);
        visited.add(u);
        
        while(!q.isEmpty()){
            Integer v1 = q.remove();

            // Check if v1 is v
            // Keep track of edge cost
            // Add adjacent vertices to q
        }
        
        return 0;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int E = in.nextInt();
        int[][] edges = new int[E][3];
        Graph g;
        in.nextLine();

        for(int i = 0; i < E; i++){
            String[] line = in.nextLine().split(" ");
            int u = Integer.parseInt(line[0]);
            int v = Integer.parseInt(line[1]);
            int w = Integer.parseInt(line[2]);

            int[] edge = {u,v,w};
            edges[i] = edge;
        }

        g = new Graph(edges);
    }    
}
