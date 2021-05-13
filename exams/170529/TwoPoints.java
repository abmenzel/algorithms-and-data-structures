import java.util.*;

import edu.princeton.cs.algs4.*;

public class TwoPoints {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int S = in.nextInt();
        int streets = in.nextInt();
        int M = in.nextInt();
        int EL = in.nextInt();
        Map<String, Integer> jens = new HashMap<>();
        Map<String, Integer> monsterMap = new HashMap<>();
        in.nextLine();

        // 1. Build graph
        EdgeWeightedDigraph g = new EdgeWeightedDigraph((S + M)*2, 0);

        for (int i = 0; i < streets*2; i = i + 2) {
            String[] linePart = in.nextLine().split(", ");
            String location1 = linePart[0];
            String location2 = linePart[1];
            Double distance = Double.parseDouble(linePart[2]);
            if(!jens.containsKey(location1)) jens.put(location1, i);
            if(!jens.containsKey(location2)) jens.put(location2, i+1);

            if (i > 0) {
                g.addEdge(new DirectedEdge(jens.get(location1), jens.get(location2), distance));
            }
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