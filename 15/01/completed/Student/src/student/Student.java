package student;

public class Student {
    private String name;
    private String nationality;
    private double GPA;

    public Student(String name, String nationality, double GPA) {
        this.name = name;
        this.nationality = nationality;
        this.GPA = GPA;
    } 
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
     
    public double getGPA() {
        return this.GPA;
    }

    public void setGPA(double gpa) {
        this.GPA = gpa;
    }
}
