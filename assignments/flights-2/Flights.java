import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.StdIn;

public class Flights {

    private Map<SimpleDateFormat, String> flightTable; 

    public Flights(){
        flightTable = new HashMap<>();
    }

    public void addFlight(String timestamp, String location){
        flightTable.put(new SimpleDateFormat(timestamp), location);
    }
    public String getFlightLocation(String timestamp){
        SimpleDateFormat requestedTime = new SimpleDateFormat(timestamp);
        if(flightTable.containsKey(requestedTime)){
            return flightTable.get(requestedTime);
        }else{
            return "-";
        }
    }
    public void removeFlight(String timestamp){
        SimpleDateFormat requestedTime = new SimpleDateFormat(timestamp);
        if(flightTable.containsKey(requestedTime)){
            flightTable.remove(requestedTime);
        }else{
            return;
        }
    }
    public void reRoute(String timestamp, String newLocation){
        SimpleDateFormat time = new SimpleDateFormat(timestamp);
        if(flightTable.containsKey(time)){
            flightTable.put(time, newLocation);
        }else{
            return;
        }
    }
    public void delayFlight(String timestamp, int delayInSeconds){
        SimpleDateFormat oldTime = new SimpleDateFormat(timestamp);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); 
        if(flightTable.containsKey(oldTime)){
            String location = flightTable.get(oldTime);
            String updatedTimestamp = LocalDateTime.parse("2000-01-01T" + timestamp).plusSeconds(delayInSeconds).format(formatter).toString();
            removeFlight(timestamp);
            flightTable.put(new SimpleDateFormat(updatedTimestamp), location);
        }else{
            return;
        }
    }
    public static void main(String[] args) {
        Flights myFlights123 = new Flights();

        int n = StdIn.readInt();
        int m = StdIn.readInt();

        StdIn.readLine();
        
        for(int i = 0; i < n; i++){
            String[] line = StdIn.readLine().split(" ");
            String time = line[0];
            String location = line[1];
            myFlights123.addFlight(time, location);
        }

        myFlights123.delayFlight("09:00:00", 10);

    }
}