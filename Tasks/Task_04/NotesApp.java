package ElevateLabs.Tasks.Task_04;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Notes Manager ---");
            System.out.println("1. View all notes");
            System.out.println("2. Add a new note");
            System.out.println("3. Delete all notes");
            System.out.println("4. Exit");
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
                    viewNotes();
                    break;
                case 2:
                    System.out.print("Enter your note: ");
                    String note = scanner.nextLine();
                    addNote(note);
                    break;
                case 3:
                    deleteNotes();
                    break;
                case 4:
                    System.out.println("Exiting application.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewNotes() {
        File notesFile = new File(FILE_NAME);
        if (!notesFile.exists() || notesFile.length() == 0) {
            System.out.println("\nNo notes found.");
            return;
        }

        System.out.println("\n--- Your Notes ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String currentLine;
            int noteNumber = 1;
            while ((currentLine = reader.readLine()) != null) {
                System.out.println(noteNumber + ". " + currentLine);
                noteNumber++;
            }
        } catch (IOException e) {
            System.out.println("Error reading notes: " + e.getMessage());
        }
        System.out.println("--------------------");
    }

    private static void addNote(String note) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(note);
            writer.newLine();
            System.out.println("Note added successfully.");
        } catch (IOException e) {
            System.out.println("Error writing note: " + e.getMessage());
        }
    }

    private static void deleteNotes() {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            // Opening in non-append mode and writing nothing truncates the file.
            System.out.println("All notes have been deleted.");
        } catch (IOException e) {
            System.out.println("Error deleting notes: " + e.getMessage());
        }
    }
}
