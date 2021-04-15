import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

import edu.princeton.cs.algs4.StdOut;

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
        return ceilEntry.getKey() + " " + ceilEntry.getValue();
    }

    public int flightCount(String timestamp1, String timestamp2) {
        return flightTable.subMap(timestamp1, true, timestamp2, true).size();
    }

    public static void main(String[] args) throws IOException {
        Flights myFlights123 = new Flights();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = reader.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        for (int i = 0; i < n; i++) {
            String[] line = reader.readLine().split(" ");
            String time = line[0];
            String location = line[1];
            myFlights123.addFlight(time, location);
        }

        for (int i = 0; i < m; i++) {
            String line = reader.readLine();
            String opParts[] = line.split(" ");

            switch (opParts[0]) {
            case "destination": {
                String timestamp = opParts[1];
                StdOut.println(myFlights123.getFlightLocation(timestamp));
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
                StdOut.println(myFlights123.nextFlight(timestamp));
            }
                break;
            case "count": {
                String timestamp1 = opParts[1];
                String timestamp2 = opParts[2];
                StdOut.println(myFlights123.flightCount(timestamp1, timestamp2));
            }
                break;
            }
        }

        reader.close();
    }
}