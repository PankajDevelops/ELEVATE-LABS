package ElevateLabs.Tasks.Task_03;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void setIssued(boolean issued) {
        isIssued = issued;
    }

    @Override
    public String toString() {
        return "ID: " + bookId + ", Title: '" + title + "', Author: '" + author + "', Status: "
                + (isIssued ? "Issued" : "Available");
    }
}

class User {
    private int userId;
    private String name;
    private ArrayList<Book> borrowedBooks;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: '" + name + "', Books Borrowed: " + borrowedBooks.size();
    }
}

public class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private int nextBookId = 1;
    private int nextUserId = 1;
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        addInitialData();

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
                    issueBook();
                    break;
                case 2:
                    returnBook();
                    break;
                case 3:
                    addNewBook();
                    break;
                case 4:
                    registerNewUser();
                    break;
                case 5:
                    showAllBooks();
                    break;
                case 6:
                    showAllUsers();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Thank you for using the Library System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }

    private void showMenu() {
        System.out.println("--- Library Management System ---");
        System.out.println("1. Issue a Book");
        System.out.println("2. Return a Book");
        System.out.println("3. Add a New Book");
        System.out.println("4. Register a New User");
        System.out.println("5. List All Books");
        System.out.println("6. List All Users");
        System.out.println("7. Exit");
        System.out.println("---------------------------------");
    }

    private void addInitialData() {
        books.add(new Book(nextBookId++, "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book(nextBookId++, "To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book(nextBookId++, "1984", "George Orwell"));
        users.add(new User(nextUserId++, "Alice"));
        users.add(new User(nextUserId++, "Bob"));
    }

    private User findUserById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    private void issueBook() {
        System.out.println("--- Issue a Book ---");
        System.out.print("Enter User ID: ");
        int userId = -1;
        try {
            userId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid User ID format.");
            return;
        }
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("Error: User not found.");
            return;
        }

        System.out.print("Enter Book ID to issue: ");
        int bookId = -1;
        try {
            bookId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Book ID format.");
            return;
        }
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Error: Book not found.");
            return;
        }

        if (book.isIssued()) {
            System.out.println("Error: This book is already issued.");
        } else {
            book.setIssued(true);
            user.borrowBook(book);
            System.out.println("Success: Book '" + book.getTitle() + "' issued to " + user.getName() + ".");
        }
    }

    private void returnBook() {
        System.out.println("--- Return a Book ---");
        System.out.print("Enter User ID: ");
        int userId = -1;
        try {
            userId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid User ID format.");
            return;
        }
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("Error: User not found.");
            return;
        }

        System.out.print("Enter Book ID to return: ");
        int bookId = -1;
        try {
            bookId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid Book ID format.");
            return;
        }
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Error: Book not found.");
            return;
        }

        if (!book.isIssued()) {
            System.out.println("Error: This book was not issued.");
        } else {
            book.setIssued(false);
            user.returnBook(book);
            System.out.println("Success: Book '" + book.getTitle() + "' returned by " + user.getName() + ".");
        }
    }

    private void addNewBook() {
        System.out.println("--- Add a New Book ---");
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();

        books.add(new Book(nextBookId++, title, author));
        System.out.println("Book added successfully!");
    }

    private void registerNewUser() {
        System.out.println("--- Register a New User ---");
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();

        users.add(new User(nextUserId++, name));
        System.out.println("User registered successfully!");
    }

    private void showAllBooks() {
        System.out.println("--- Complete Book List ---");
        if (books.isEmpty()) {
            System.out.println("There are no books in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }

    private void showAllUsers() {
        System.out.println("--- Registered User List ---");
        if (users.isEmpty()) {
            System.out.println("There are no registered users.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    public static void main(String[] args) {
        Library librarySystem = new Library();
        librarySystem.start();
    }
}
