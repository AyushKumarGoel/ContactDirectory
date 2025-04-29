package controller;

import database.LibraryDatabase;

public class LibrarianController {

    // Add a book to the library
    public void addBook(int id, String title, String author) {
        LibraryDatabase.books.put(id, title);
        System.out.println("Book added successfully: " + title);
    }

    // Remove a book from the library
    public void removeBook(int id) {
        LibraryDatabase.books.remove(id);
        System.out.println("Book removed successfully.");
    }

    // View all books in the library
    public void viewAllBooks() {
        System.out.println("\n===== All Books in Library =====");
        LibraryDatabase.books.forEach((id, title) -> {
            System.out.println("ID: " + id + " | Title: " + title);
        });
    }

    // Manage users (e.g., block/unblock users)
    public void manageUsers() {
        System.out.println("\n===== Manage Users =====");
        LibraryDatabase.users.forEach((card, user) -> {
            System.out.println("User: " + user.getName() + " | Library Card: " + user.getLibraryCard());
        });
    }

    // View all transactions (borrow/return history)
    public void viewTransactions() {
        System.out.println("\n===== All Transactions =====");
        LibraryDatabase.transactions.forEach((bookId, status) -> {
            System.out.println("Book ID: " + bookId + " | Status: " + status);
        });
    }
}
