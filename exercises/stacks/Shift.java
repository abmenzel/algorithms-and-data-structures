import java.util.Iterator;

import edu.princeton.cs.algs4.Queue;

public class Shift{
    
    public static boolean isCircular(String string1, String string2){
        boolean result = false;
        String[] string1Array = string1.split("");
        String[] string2Array = string2.split("");
        Queue<String> q1 = new Queue<>();
        Queue<String> q2 = new Queue<>();

        for(String ch : string1Array){
            q1.enqueue(ch);
        }
        for(String ch : string2Array){
            q2.enqueue(ch);
        }

        for(var i = 0; i < q2.size(); i++){
            if(q2.peek().equals(q1.peek())){
                Iterator<String> it1 = q1.iterator();
                Iterator<String> it2 = q2.iterator();
                
                while(it2.hasNext()){
                    if(!it1.next().equals(it2.next())){
                        result = false;
                        break;
                    }else{
                        result = true;
                    }
                }
            }else{
                q2.enqueue(q2.dequeue());
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(isCircular("TGACGAC", "ACTGACG"));
    }
}