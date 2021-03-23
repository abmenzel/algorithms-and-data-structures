import edu.princeton.cs.algs4.*;

public class Main{
    public static void main(String[] args) {
        boolean balanced = true;
        int open = 0;
        Stack<String> symbol = new Stack<>();
        while(!StdIn.isEmpty()){
            char readChar = StdIn.readChar();
            switch(readChar){
                case ']':
                    if(symbol.isEmpty() || !symbol.peek().equals("[")){
                        balanced = false;
                        break;
                    }else{
                        symbol.pop();
                        open--;
                    }
                    break;
                case ')':
                    if(symbol.isEmpty() || !symbol.peek().equals("(")){
                        balanced = false;
                        break;
                    }else{
                        symbol.pop();
                        open--;
                    }
                    break;
                default:
                    symbol.push(String.valueOf(readChar));
                    open++;
            }
        }
        if(open > 0) balanced = false;
        System.out.println(balanced ? 1 : 0);
    }
}