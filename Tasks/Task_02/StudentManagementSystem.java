package ElevateLabs.Tasks.Task_02;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

class Student {
    private int id;
    private String name;
    private double grade;

    public Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Grade: " + grade;
    }
}

public class StudentManagementSystem {

    private ArrayList<Student> studentList = new ArrayList<>();
    private int nextStudentId = 1;
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean exit = false;
        while (!exit) {
            showMenu();
            System.out.print("Enter your choice: ");
            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

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
                    exit = true;
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    private void showMenu() {
        System.out.println("--- Student Record Management System ---");
        System.out.println("1. Add a new Student");
        System.out.println("2. View all Students");
        System.out.println("3. Update a Student's record");
        System.out.println("4. Delete a Student's record");
        System.out.println("5. Exit");
        System.out.println("----------------------------------------");
    }

    private void addStudent() {
        System.out.println("--- Add New Student ---");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        double grade = -1;
        while (grade < 0 || grade > 100) {
            System.out.print("Enter student grade (0-100): ");
            try {
                grade = Double.parseDouble(scanner.nextLine());
                if (grade < 0 || grade > 100) {
                    System.out.println("Invalid grade. Please enter a value between 0 and 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value for the grade.");
            }
        }

        Student newStudent = new Student(nextStudentId++, name, grade);
        studentList.add(newStudent);
        System.out.println("Student added successfully!");
    }

    private void viewAllStudents() {
        System.out.println("--- All Student Records ---");
        if (studentList.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            for (Student student : studentList) {
                System.out.println(student);
            }
        }
    }

    private Student findStudentById(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private void updateStudent() {
        System.out.println("--- Update Student Record ---");
        System.out.print("Enter the ID of the student to update: ");
        int idToUpdate = -1;
        try {
            idToUpdate = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
            return;
        }

        Student studentToUpdate = findStudentById(idToUpdate);

        if (studentToUpdate == null) {
            System.out.println("Student with ID " + idToUpdate + " not found.");
            return;
        }

        System.out.println("Current record: " + studentToUpdate);

        System.out.print("Enter new name (or press Enter to keep current): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) {
            studentToUpdate.setName(newName);
        }

        System.out.print("Enter new grade (or press Enter to keep current): ");
        String newGradeStr = scanner.nextLine();
        if (!newGradeStr.isEmpty()) {
            try {
                double newGrade = Double.parseDouble(newGradeStr);
                if (newGrade >= 0 && newGrade <= 100) {
                    studentToUpdate.setGrade(newGrade);
                } else {
                    System.out.println("Invalid grade. Keeping the old one.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format for grade. Keeping the old one.");
            }
        }

        System.out.println("Student record updated successfully!");
    }

    private void deleteStudent() {
        System.out.println("--- Delete Student Record ---");
        System.out.print("Enter the ID of the student to delete: ");
        int idToDelete = -1;
        try {
            idToDelete = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
            return;
        }

        Iterator<Student> iterator = studentList.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getId() == idToDelete) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Student with ID " + idToDelete + " deleted successfully.");
        } else {
            System.out.println("Student with ID " + idToDelete + " not found.");
        }
    }

    public static void main(String[] args) {
        StudentManagementSystem sms = new StudentManagementSystem();
        sms.start();
    }
}
