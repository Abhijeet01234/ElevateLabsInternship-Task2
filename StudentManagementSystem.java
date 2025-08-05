import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private int id;
    private String name;
    private int age;
    private String grade;
    private double marks;

    //Generating our Parameterised Constructor of Student Class
    public Student(int id, String name, int age, String grade, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.marks = marks;
    }

    //Generating their Getters and setters
    public int getId() {
        return id;
    }
    
    //No need of setter method to set id manually, it will automatically assigned when a new student is created.

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    //generating a toString Method for output
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + 
               ", Grade: " + grade + ", Marks: " + marks;
    }
}

public class StudentManagementSystem { //It is our main Class
    private ArrayList<Student> students;
    private int nextId;
    private Scanner sc;

    //default constructor for our purposes
    public StudentManagementSystem() {
        students = new ArrayList<>();
        nextId = 1;
        sc = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    sc.close(); //closing the object of Scanner class
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("----------------------------------");
        System.out.println("\nStudent Record Management System");
        System.out.println("----------------------------------");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Exit");
    }

    private void addStudent() {
        System.out.println("\nAdding New Student");
        System.out.println("----------------------------------");
        String name = getStringInput("Enter student name: ");
        int age = getIntInput("Enter student age: ");
        String grade = getStringInput("Enter student grade: ");
        double marks = getDoubleInput("Enter student marks: ");

        Student student = new Student(nextId++, name, age, grade, marks); //Id will auto incremented each time when adding the student 
        students.add(student);
        System.out.println("Student added successfully!");
    }

    private void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available in the System.");
            return;
        }

        System.out.println("\nList of All Students");
        System.out.println("----------------------------------");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void updateStudent() {
        if (students.isEmpty()) {
            System.out.println("No students to update.");
            return;
        }

        int id = getIntInput("Enter student ID to update: ");
        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        System.out.println("Current student details: " + student);
        System.out.println("\nEnter new details (or you can leave blank to keep current value):");

        String name = getStringInput("Name [" + student.getName() + "]: ", true);
        if (!name.isEmpty()) student.setName(name);

        String ageInput = getStringInput("Age [" + student.getAge() + "]: ", true);
        if (!ageInput.isEmpty()) student.setAge(Integer.parseInt(ageInput));

        String grade = getStringInput("Grade [" + student.getGrade() + "]: ", true);
        if (!grade.isEmpty()) student.setGrade(grade);

        String marksInput = getStringInput("Marks [" + student.getMarks() + "]: ", true);
        if (!marksInput.isEmpty()) student.setMarks(Double.parseDouble(marksInput));

        System.out.println("Student updated successfully!");
    }

    private void deleteStudent() {
        if (students.isEmpty()) {
            System.out.println("No students available to delete.");
            return;
        }

        int id = getIntInput("Enter student ID to delete: ");
        Student student = findStudentById(id);

        if (student == null) {
            System.out.println("Student with ID " + id + " not found.");
            return;
        }

        students.remove(student);
        System.out.println("Student deleted successfully!");
    }

    private Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    // Helper methods for input
    private String getStringInput(String prompt) {
        return getStringInput(prompt, false);
    }

    private String getStringInput(String prompt, boolean allowBlank) {
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (!allowBlank && input.isEmpty()) {
                System.out.println("This field cannot be empty. Please try again.");
            }
        } while (!allowBlank && input.isEmpty());
        return input;
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        system.run();
    }
}