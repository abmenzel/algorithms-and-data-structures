
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Party implements Comparable<Party>{

    public int partyNumber = 0;
    private int initialVotes = 0;
    public double quotient = 0;
    public int seats = 0;

    public Party(int initialVotes, int partyNumber){
        this.initialVotes = initialVotes;
        this.quotient = initialVotes;
        this.partyNumber = partyNumber;
    }

    @Override
    public int compareTo(Party other){
        if (other.quotient == this.quotient)
            return 0;
        else if (other.quotient < this.quotient)
            return 1;
        else
            return -1;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int m = StdIn.readInt();
        
        MaxPQ votes = new MaxPQ(n);
        int[] partySeats = new int[n];

        for(int i = 0; i < n; i++){
            votes.insert(new Party(StdIn.readInt(), i));
        }

        for(int a = 0; a < m; a++){
            Party winnerOfSeat = (Party) votes.delMax();

            winnerOfSeat.seats++;
            partySeats[winnerOfSeat.partyNumber]++;

            winnerOfSeat.quotient = (double) winnerOfSeat.initialVotes / (winnerOfSeat.seats + 1);
            votes.insert(winnerOfSeat);
        }

        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < n; j++){
            sb.append(partySeats[j]).append("\n");
        }
        StdOut.println(sb);
    }
}