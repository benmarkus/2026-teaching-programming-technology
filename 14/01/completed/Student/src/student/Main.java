package student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static boolean isStudentEligibleForStipendium(Student stud) {
        return stud.getGPA() > 4.0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int numberOfStudents = scanner.nextInt();

        List<Student> students = new ArrayList<>();

        for (int i = numberOfStudents; i > 0; --i) {
            System.out.print("Enter the name of the student: ");
            String nameOfStudent = scanner.next();
            System.out.print("Enter the nationality of the student: ");
            String nationalityOfStudent = scanner.next();
            System.out.print("Enter the GPA of the student: ");
            double gpaOfStudent = scanner.nextDouble();

            Student student = new Student(nameOfStudent, nationalityOfStudent, gpaOfStudent);
            students.add(student);
        }

        scanner.close();

        Student bestStudent1 = students.getFirst();
        for (Student stud : students) {
            if (stud.getGPA() > bestStudent1.getGPA()) {
                bestStudent1 = stud;
            }
        }

        System.out.print("Best student: ");
        System.out.println(bestStudent1.getName());

        Student worstStudent1 = students.getFirst();
        for (Student stud : students) {
            if (stud.getGPA() < worstStudent1.getGPA()) {
                worstStudent1 = stud;
            }
        }
        System.out.print("Worst student: ");
        System.out.println(worstStudent1.getName());


        Optional<Student> bestStudent = students.stream()
                .max(Comparator.comparingDouble(Student::getGPA));

        if (bestStudent.isPresent()) {
            System.out.print("Best student: ");
            System.out.println(bestStudent.get().getName());
        }

        Optional<Student> worstStudent = students.stream()
                .min(Comparator.comparingDouble(Student::getGPA));
        if (worstStudent.isPresent()) {
            System.out.print("Worst student: ");
            System.out.println(worstStudent.get().getName());
        }

        List<Student> studentsEligibleForStipendium = students.stream()
                .filter(Main::isStudentEligibleForStipendium)
                .toList();

        System.out.print("Students eligible for stipendium: ");
        System.out.println(studentsEligibleForStipendium.size());
        for (Student stud : studentsEligibleForStipendium) {
            System.out.println(stud.getName());
        }
    }
}
