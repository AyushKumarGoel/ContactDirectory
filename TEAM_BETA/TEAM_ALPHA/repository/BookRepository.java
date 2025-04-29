package repository;

import entity.Book;
import java.util.*;

public class BookRepository {
    private static List<Book> books = new ArrayList<>();

    // Sample data
    static {
        books.add(new Book(2, "Java Programming", "James Gosling"));
        books.add(new Book(1, "Data Structures", "Robert Lafore"));
        books.add(new Book(3, "Algorithms", "Thomas Cormen"));
    }

    public void sortBooks(String criterion) {
        System.out.println("\n===== Sorted Books by " + criterion + " =====");

        List<Book> sortedBooks = new ArrayList<>(BookRepository.getAllBooks());

        switch (criterion.toLowerCase()) {
            case "id":
                sortedBooks.sort(Comparator.comparingInt(Book::getId));
                break;
            case "title":
                sortedBooks.sort(Comparator.comparing(Book::getTitle));
                break;
            case "author":
                sortedBooks.sort(Comparator.comparing(Book::getAuthor));
                break;
            default:
                System.out.println("Invalid sorting criterion. Please choose 'id', 'title', or 'author'.");
                return;
        }

        sortedBooks.forEach(System.out::println);
    }

    public static List<Book> getAllBooks() {
        return books;
    }

    public static Book getBookById(int id) {
        return books.stream().filter(book -> book.getId() == id).findFirst().orElse(null);
    }

    public static void addBook(Book book) {
        books.add(book);
    }

    public static boolean isBookExists(int id) {
        return books.stream().anyMatch(book -> book.getId() == id);
    }

    public static void removeBook(int id) {
        books.removeIf(book -> book.getId() == id);
    }
}
