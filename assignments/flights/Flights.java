import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Flights {
    int n; //num of flights 
    int m; //num of operations
    
    TreeMap<String,String> map = new TreeMap<String,String>(); //hashmap for flights 
    Scanner scn = new Scanner(System.in);

    public Flights(){
    }

    public void readin() {
        while (scn.hasNext()) {
            n = scn.nextInt();
            m = scn.nextInt();
    
            //read in flights 
            for (int i = 0; i < n; i++) {
                String time = scn.next().trim();
                String destination = scn.next().trim();
                map.put(time,destination);
            }
    
            //read in operations
            //handle operations 
            for (int i = 0; i < m; i++) {
                String operation = scn.next().trim();
                operationSwitch(operation);
            }
        }
        scn.close();
    }

    public void cancel() {
        String flightToCancel = scn.next().trim();
        map.remove(flightToCancel);        
    }

    public void delay() {
        String flightTimeKey = scn.next().trim(); //what flight are we talking about
        int delay = Integer.valueOf(scn.next().trim()); //integer value of delayment 
        String destination = map.get(flightTimeKey); //where was the flight going

        //remove from map 
        map.remove(flightTimeKey);

        //add to map 
        map.put(updateTime(flightTimeKey, delay), destination);

    }

    public String updateTime(String time, int delay) {
        
        //date formatter 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        //parse and plus and format 
        String updatedFlightTime = 
        LocalDateTime
        .parse("2000-01-01T" + time)
        .plusSeconds(delay)
        .format(formatter)
        .toString();

        return updatedFlightTime;
    }

    public void destination() {
        String flightTimeKey = scn.next().trim(); //what flight are we talking about

        if (map.containsKey(flightTimeKey)){
            System.out.println(map.get(flightTimeKey));
        } else {
            System.out.println("-");
        }
    }

    public void reroute() {
        String flightTimeKey = scn.next().trim(); //what flight are we talking about
        String reroute = scn.next().trim(); //where to? 

        map.remove(flightTimeKey);
        map.put(flightTimeKey, reroute);
    }

    public void count() {
        String fromKey = scn.next().trim(); //what min time
        String ToKey = scn.next().trim(); //what max time 
        
        NavigableMap<String,String> submap = map.subMap(fromKey, true, ToKey, true);
        System.out.println(submap.size());
    }

    public void next() {
        String key = scn.next().trim(); //from when are we looking?
        if (map.containsKey(key)){
            System.out.println(key);
        }else{
            System.out.println(map.higherKey(key));
        }
    }

    public void operationSwitch(String o) {
        switch (o) {
            case "destination":
                destination();
                break;
            case "cancel":
                cancel();
                break;
            case "delay":
                delay();
                break;
            case "reroute":
                reroute();
                break;
            case "next":
                next();
                break;
            case "count":
                count();
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        Flights f = new Flights();
        f.readin();
    }
}
