
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Student { // class structure for each student
    private double cgpa;
    private String name;
    private int token;

    public Student(String fname, double cgpa, int token) {
        this.name = fname;
        this.cgpa = cgpa;
        this.token = token;
    }

    public int getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

}

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // store first line ie number of events

        PriorityQueue<Student> students = new PriorityQueue<>(new Comparator<Student>() {
            @Override
            public int compare(Student studentThis, Student studentThat) {
                // case 1: First check for cgpa
                if (studentThis.getCgpa() == studentThat.getCgpa()) {
                    //case 2: Check for name
                    if (studentThis.getName().compareTo(studentThat.getName()) == 0) // case 3 : Check for token
                        return studentThis.getToken() - studentThat.getToken();

                    return studentThis.getName().compareTo(studentThat.getName());
                } else {
                    // since cgpa is in desc order of priority we reverse the return
                    if (studentThis.getCgpa() > studentThat.getCgpa())
                        return -1;

                    return 1;


                }
            }
        });

        for (int i = 0; i < n; i++) {

            String next = sc.next();

            // next can be either ENTER or SERVED
            if (next.equals("ENTER")) {
                String name = sc.next();
                double cgpa = sc.nextDouble();
                int token = sc.nextInt();
                students.add(new Student(name, cgpa, token));

            } else if (next.equals("SERVED"))
                students.poll();
//            System.out.println("Removed" + students.poll().getName());

        }

        // Check if students is null if not print all remaining till it is not empty
        if (students.isEmpty())
            System.out.println("EMPTY");
        else
            while (true) {
                try {
                    System.out.println(students.poll().getName()); // if this is null then catch will be executed and it will break out of the loop
                } catch (Exception e) {
//                   e.printStackTrace();
                    break;
                }
            }
    }
}
