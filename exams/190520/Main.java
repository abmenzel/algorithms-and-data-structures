public class Main{
    public static void main(String[] args) {
        int N = 1000;
        int c = 0;
        for (int i = 0; i < N; i = i+1)
            for (int j = i; j > 0; j = j-2){
                c++;
            }

        System.out.println(c);
    }
}