import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Main{
    public static void main(String[] args) {
        int lines = StdIn.readInt();
        String[] students = new String[lines];

        for (int i = 0; i < lines; i++) {
            String name = StdIn.readString();
            String[] grade = StdIn.readString().split("(?<=[A-Z])(?=[\\+\\-])");
            double numericGrade = 0.0;

            switch(grade[0]){
                case "A":
                    numericGrade = 1.0;
                    break;
                case "B":
                    numericGrade = 2.0;
                    break;
                case "C":
                    numericGrade = 3.0;
                    break;
                case "D":
                    numericGrade = 4.0;
                    break;
                case "E":
                    numericGrade = 5.0;
                    break;
                case "FX":
                    numericGrade = 6.0;
                    break;
                case "F":
                    numericGrade = 7.0;
                    break;
            }

            if(grade.length > 1){
                boolean plusSign = (grade[1].charAt(0) == '+' ? true : false);
                double count = grade[1].length();

                if(plusSign) numericGrade -= (count/1000);
                else numericGrade += (count/1000);
            }

            students[i] = numericGrade + " " + name;
        }
        if(lines > 1) Merge.sort(students);

        for(String student : students){
            StdOut.println(student.split(" ")[1]);
        }
    }
}