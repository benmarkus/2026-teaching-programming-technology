package student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static boolean isStudentEligibleForStipendium(Student stud) {
        return stud.getGPA() > 4.0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int numberOfStudent = scanner.nextInt();

        List<Student> students = new ArrayList<>();

        for (int i = numberOfStudent; i > 0; --i) {
            System.out.print("Enter the name of the student: ");
            String nameOfStudent = scanner.next();
            System.out.print("Enter the nationality of the student: ");
            String nationalityOfStudent = scanner.next();
            System.out.print("Enter the GPA of the student: ");
            double gpaOfStudent = scanner.nextDouble();

            Student student = new Student(nameOfStudent, nationalityOfStudent, gpaOfStudent);
            students.add(student);
        }

        /*
        long-running alkalmazásokban figyelni kell arra, hogy
        a scannereket lezárjuk, mivel ezek tartják az erőforrásokat
        amíg véget nem ér a program, ha nincsenek explicit módon lezárva
        */
        scanner.close();


        // Progalapos tétellel

        Student bestStudent1 = null;
        double bestGPA = 0.0;

        for (Student stud : students) {
            if (stud.getGPA() > bestGPA) {
                bestStudent1 = stud;
                bestGPA = stud.getGPA();
            }
        }

        if (bestStudent1 != null) {
            System.out.println(bestStudent1.getName());
        }


        Student worstStudent1 = null;
        double worstGPA = bestGPA;

        for (Student stud : students) {
            if (stud.getGPA() < worstGPA) {
                worstStudent1 = stud;
                worstGPA = stud.getGPA();
            }
        }

        if (worstStudent1 != null) {
            System.out.println(worstStudent1.getName());
        }

        List<Student> studentsEligibleForStipendium1 = new ArrayList<>();

        for (Student stud : students) {
            if (isStudentEligibleForStipendium(stud)) {
                studentsEligibleForStipendium1.add(stud);
            }
        }

        System.out.print("Students eligible for stipendium: ");
        System.out.println(studentsEligibleForStipendium1.size());
        for (Student stud : studentsEligibleForStipendium1) {
            System.out.println(stud.getName());
        }


        // KITEKINTÉS: Modern Java megoldásokkal, később ezekről részletesebben

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
