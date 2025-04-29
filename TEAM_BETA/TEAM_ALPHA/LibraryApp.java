import controller.LibrarianController;
import controller.UserController;
import database.LibraryDatabase;
import entity.User;
import java.util.InputMismatchException;
import java.util.Scanner;
import source.LibrarianSource;
import source.UserSource;

public class LibraryApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserSource userSource = new UserSource();
    private static final LibrarianSource librarianSource = new LibrarianSource();
    private static final UserController userController = new UserController();
    private static final LibrarianController librarianController = new LibrarianController();

    public static void main(String[] args) {
        LibraryDatabase.userCredentials.put("admin", "admin123"); // Default admin
        boolean running = true;
        while (running) {
            try {
                System.out.println("\n===== Library Management System =====");
                System.out.println("1. Register User");
                System.out.println("2. Login as User");
                System.out.println("3. Login as Librarian");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> registerUser();
                    case 2 -> loginUser();
                    case 3 -> loginLibrarian();
                    case 4 -> {
                        System.out.println("Exiting... Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // clear the invalid input
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter User ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Set Password: ");
        String password = scanner.nextLine();

        userSource.registerUser(id, name, password);
        System.out.println("User registered successfully.");
    }

    private static void loginUser() {
        System.out.print("Enter Library Card: ");
        String card = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (!userSource.authenticateUser(card, password)) {
            System.out.println("Authentication failed.");
            return;
        }

        boolean loggedIn = true;
        while (loggedIn) {
            try {
                System.out.println("\n===== User Menu =====");
                System.out.println("1. View All Books");
                System.out.println("2. Sort and View Books"); // New option
                System.out.println("3. Borrow Book");
                System.out.println("4. Return Book");
                System.out.println("5. View Transaction History");
                System.out.println("6. View and Modify My Details");
                System.out.println("7. Exit");
                System.out.println("8. Logout");
                System.out.print("Enter choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> userController.viewBooks();
                    case 2 -> sortBooksFlow(); // New method
                    case 3 -> {
                        System.out.print("Enter Book ID to borrow: ");
                        int id = scanner.nextInt();
                        try {
                            userController.borrowBook(card, id);
                            System.out.println("Book borrowed successfully.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 4 -> {
                        System.out.print("Enter Book ID to return: ");
                        int id = scanner.nextInt();
                        try {
                            userController.returnBook(card, id);
                            System.out.println("Book returned successfully.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 5 -> userController.viewTransactions(card);
                    case 6 -> viewAndModifyDetails(card);
                    case 7 -> {
                        System.out.println("Exiting application. Goodbye!");
                        System.exit(0);
                    }
                    case 8 -> {
                        System.out.println("Logging out...");
                        loggedIn = false;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void sortBooksFlow() {
        System.out.println("Sort books by:");
        System.out.println("1. ID");
        System.out.println("2. Title");
        System.out.println("3. Author");
        System.out.print("Enter choice: ");
        int sortChoice = scanner.nextInt();
        scanner.nextLine();

        String sortCriterion;
        switch (sortChoice) {
            case 1 -> sortCriterion = "id";
            case 2 -> sortCriterion = "title";
            case 3 -> sortCriterion = "author";
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        userController.sortBooks(sortCriterion);
    }

    private static void viewAndModifyDetails(String card) {
        User user = userSource.getUserDetails(card);
        if (user != null) {
            System.out.println("\n===== My Details =====");
            System.out.println("Name: " + user.getName());
            System.out.println("ID: " + user.getId());
            System.out.println("Library Card: " + user.getLibraryCard());
            System.out.println("Password: " + user.getPassword());

            System.out.println("\nDo you want to modify your details?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter new Name: ");
                String newName = scanner.nextLine();
                System.out.print("Enter new User ID: ");
                int newId = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Enter new Password: ");
                String newPassword = scanner.nextLine();

                if (userSource.updateUserDetails(card, newName, newPassword, newId)) {
                    System.out.println("Details updated successfully.");
                } else {
                    System.out.println("Error updating details.");
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }

    private static void loginLibrarian() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (!librarianSource.authenticateLibrarian(username, password)) {
            System.out.println("Authentication failed.");
            return;
        }

        boolean loggedIn = true;
        while (loggedIn) {
            try {
                System.out.println("\n===== Librarian Menu =====");
                System.out.println("1. Add a Book");
                System.out.println("2. Remove a Book");
                System.out.println("3. View All Books");
                System.out.println("4. View All Users");
                System.out.println("5. View All Transactions");
                System.out.println("6. Logout");
                System.out.print("Enter choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> addBook();
                    case 2 -> removeBook();
                    case 3 -> librarianController.viewAllBooks();
                    case 4 -> librarianController.manageUsers();
                    case 5 -> librarianController.viewTransactions();
                    case 6 -> {
                        System.out.println("Logging out...");
                        loggedIn = false;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();

        librarianController.addBook(id, title, author);
    }

    private static void removeBook() {
        System.out.print("Enter Book ID to remove: ");
        int id = scanner.nextInt();
        librarianController.removeBook(id);
    }
}