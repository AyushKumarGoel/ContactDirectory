package database;

import entity.User;
import java.util.HashMap;
import java.util.Map;

public class LibraryDatabase {
    // Storing users by their library card
    public static Map<String, User> users = new HashMap<>();
    
    // Storing user credentials (password)
    public static Map<String, String> userCredentials = new HashMap<>();
    
    // Storing books (id -> book name)
    public static Map<Integer, String> books = new HashMap<>();
    
    // Storing transactions (bookId -> status: borrowed/returned)
    public static Map<Integer, String> transactions = new HashMap<>();
    
    // Initialize some default books
    static {
        books.put(1, "Introduction to Java");
        books.put(2, "Data Structures and Algorithms");
        books.put(3, "Machine Learning Basics");
    }
}
