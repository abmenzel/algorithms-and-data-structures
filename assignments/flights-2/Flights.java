import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import edu.princeton.cs.algs4.StdIn;

public class Flights {

    private NavigableMap<String, String> flightTable;

    public Flights() {
        flightTable = new TreeMap<>();
    }

    public void addFlight(String timestamp, String location) {
        flightTable.put(timestamp, location);
    }

    public String getFlightLocation(String timestamp) {
        if (flightTable.containsKey(timestamp)) {
            return flightTable.get(timestamp);
        } else {
            return "-";
        }
    }

    public void removeFlight(String timestamp) {
        if (flightTable.containsKey(timestamp)) {
            flightTable.remove(timestamp);
        } else {
            return;
        }
    }

    public void reRoute(String timestamp, String newLocation) {
        if (flightTable.containsKey(timestamp)) {
            flightTable.put(timestamp, newLocation);
        } else {
            return;
        }
    }

    public void delayFlight(String timestamp, int delayInSeconds) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        if (flightTable.containsKey(timestamp)) {
            String location = flightTable.get(timestamp);
            String updatedTimestamp = LocalDateTime.parse("2000-01-01T" + timestamp).plusSeconds(delayInSeconds)
                    .format(formatter).toString();
            removeFlight(timestamp);
            flightTable.put(updatedTimestamp, location);
        } else {
            return;
        }
    }

    public String nextFlight(String timestamp) {
        Entry<String, String> ceilEntry = flightTable.ceilingEntry(timestamp);
        if(ceilEntry != null){
            return ceilEntry.getKey() + " " + ceilEntry.getValue(); 
        }else{
            return "";
        }
    }

    public int flightCount(String timestamp1, String timestamp2) {
        String loKey = flightTable.ceilingKey(timestamp1);
        String hiKey = flightTable.floorKey(timestamp2);
        if (loKey != null && hiKey != null) {
            int lo = flightTable.headMap(loKey).size();
            int hi = flightTable.headMap(hiKey).size();
            return (hi - lo) + 1;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        Flights myFlights123 = new Flights();

        int n = StdIn.readInt();
        int m = StdIn.readInt();
        String[] operations = new String[m];

        StdIn.readLine();

        for (int i = 0; i < n; i++) {
            String[] line = StdIn.readLine().split(" ");
            String time = line[0];
            String location = line[1];
            myFlights123.addFlight(time, location);
        }

        for (int i = 0; i < m; i++) {
            operations[i] = StdIn.readLine();
        }

        for (String op : operations) {
            String opParts[] = op.split(" ");

            switch (opParts[0]) {
            case "destination": {
                String timestamp = opParts[1];
                System.out.println(myFlights123.getFlightLocation(timestamp));
            }
                break;
            case "cancel": {
                String timestamp = opParts[1];
                myFlights123.removeFlight(timestamp);
            }
                break;
            case "delay": {
                String timestamp = opParts[1];
                int delay = Integer.parseInt(opParts[2]);
                myFlights123.delayFlight(timestamp, delay);
            }
                break;
            case "reroute": {
                String timestamp = opParts[1];
                String newLocation = opParts[2];
                myFlights123.reRoute(timestamp, newLocation);
            }
                break;
            case "next": {
                String timestamp = opParts[1];
                System.out.println(myFlights123.nextFlight(timestamp));
            }
                break;
            case "count": {
                String timestamp1 = opParts[1];
                String timestamp2 = opParts[2];
                System.out.println(myFlights123.flightCount(timestamp1, timestamp2));
            }
                break;
            }
        }
    }
}