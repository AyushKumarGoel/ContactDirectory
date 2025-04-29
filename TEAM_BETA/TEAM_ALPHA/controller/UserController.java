package controller;

import database.LibraryDatabase;
import entity.Book;
import java.util.*;
import repository.BookRepository;

public class UserController {

    // View all books available in the library
    public void viewBooks() {
        System.out.println("\n===== Available Books =====");
        LibraryDatabase.books.forEach((id, title) -> {
            System.out.println("ID: " + id + " | Title: " + title);
        });
    }

    // Sort and view books based on the given criterion
    public void sortBooks(String criterion) {
    List<Book> sortedBooks = new ArrayList<>(BookRepository.getAllBooks());

    switch (criterion.toLowerCase()) {
        case "id" -> sortedBooks.sort(Comparator.comparingInt(Book::getId));
        case "title" -> sortedBooks.sort(Comparator.comparing(Book::getTitle));
        case "author" -> sortedBooks.sort(Comparator.comparing(Book::getAuthor));
        default -> {
            System.out.println("Invalid sorting criterion.");
            return;
        }
    }

    System.out.println("\n===== Sorted Books by " + criterion + " =====");
    sortedBooks.forEach(book ->
        System.out.println("ID: " + book.getId() +
                           " | Title: " + book.getTitle() +
                           " | Author: " + book.getAuthor())
    );
}

    // Borrow a book by book ID
    public void borrowBook(String card, int bookId) throws Exception {
        if (!LibraryDatabase.books.containsKey(bookId)) {
            throw new Exception("Book not found.");
        }
        LibraryDatabase.transactions.put(bookId, "borrowed");
        System.out.println("You borrowed the book: " + LibraryDatabase.books.get(bookId));
    }

    // Return a book by book ID
    public void returnBook(String card, int bookId) throws Exception {
        if (!LibraryDatabase.transactions.containsKey(bookId) || !"borrowed".equals(LibraryDatabase.transactions.get(bookId))) {
            throw new Exception("This book is not borrowed.");
        }
        LibraryDatabase.transactions.put(bookId, "returned");
        System.out.println("You returned the book: " + LibraryDatabase.books.get(bookId));
    }

    // View user's transaction history
    public void viewTransactions(String card) {
        System.out.println("\n===== Your Transactions =====");
        LibraryDatabase.transactions.forEach((bookId, status) -> {
            System.out.println("Book ID: " + bookId + " | Status: " + status);
        });
    }
}
