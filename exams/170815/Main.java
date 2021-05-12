public class Main{
    public static void main(String[] args) {
        int N = 100;
        double NlogN = N*(Math.log10(N) / Math.log10(2.0));
        int e = 0;
        for (int i = 0; i < N/2; i = i+1){
            for (int j = 1; j < N/2; j = 2*j){
                e = e +2;
            }
        }
        System.out.println("stars: " + e);
        System.out.println("n log n: " + NlogN);
    }
}