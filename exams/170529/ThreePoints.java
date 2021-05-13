import java.util.*;

import edu.princeton.cs.algs4.*;

public class ThreePoints {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int S = in.nextInt();
        int M = in.nextInt();
        int EL = in.nextInt();
        String prevLocation = null;
        Map<String, Integer> jens = new HashMap<>();
        Map<String, Integer> monsterMap = new HashMap<>();
        in.nextLine();

        // 1. Build graph
        EdgeWeightedDigraph g = new EdgeWeightedDigraph(S + M, 0);

        for (int i = 0; i < S; i++) {
            String[] linePart = in.nextLine().split(", ");
            String location = linePart[0];
            Double distance = Double.parseDouble(linePart[1]);
            jens.put(location, i); 

            if (i > 0) {
                g.addEdge(new DirectedEdge(jens.get(prevLocation), jens.get(location), distance));
            }
            prevLocation = location;
        }

        for (int i = 0; i < M; i++){
            String[] linePart = in.nextLine().split(", ");
            String monster = linePart[0];
            String location = linePart[1];
            Double killtime = Double.parseDouble(linePart[2]);

            monsterMap.put(monster, S + i);

            g.addEdge(new DirectedEdge(jens.get(location), S + i, killtime));
        }

        //2. Find best monster
        Double currentBest = 0.0;
        String currentBestName = "";
        DijkstraSP dijkstra = new DijkstraSP(g, 0);

        for (Map.Entry<String, Integer> monster : monsterMap.entrySet()){
            String name = monster.getKey();
            Integer vertex = monster.getValue();
            Double length = dijkstra.distTo(vertex);

            if(length <= EL && length > currentBest){
                currentBest = length;
                currentBestName = name;
            }
        }
        
        System.out.println();
        System.out.println("Sheriff Grimes should kill " + currentBestName + " with a total running time of " + currentBest);

    }
}