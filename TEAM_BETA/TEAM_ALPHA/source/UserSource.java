package source;

import database.LibraryDatabase;
import entity.User;
import java.util.UUID;

public class UserSource {

    // Register a user with unique library card and store user details
    public void registerUser(int id, String name, String password) {
        String card = generateLibraryCard();
        
        // Check if user already exists with the same card
        if (LibraryDatabase.users.containsKey(card)) {
            System.out.println("User with this card already exists. Please try again.");
            return;
        }
        
        // Create new user and add to the system
        User newUser = new User(id, name, card, password);
        LibraryDatabase.users.put(card, newUser);
        LibraryDatabase.userCredentials.put(card, password);
        
        System.out.println("User " + name + " with ID " + id + " registered successfully. Library Card: " + card);
    }

    // Authenticate a user by card number and password
    public boolean authenticateUser(String card, String password) {
        String storedPassword = LibraryDatabase.userCredentials.get(card);
        
        if (storedPassword == null) {
            System.out.println("User with this card not found.");
            return false;
        }
        
        if (storedPassword.equals(password)) {
            System.out.println("Authentication successful.");
            return true;
        } else {
            System.out.println("Incorrect password.");
            return false;
        }
    }

    // Generate a unique library card (8 characters long)
    public String generateLibraryCard() {
        return UUID.randomUUID().toString().substring(0, 8); // Unique 8-character string
    }

    // Method to get a user's current details by library card
    public User getUserDetails(String card) {
        User user = LibraryDatabase.users.get(card);
        
        if (user == null) {
            System.out.println("No user found with this library card.");
            return null;
        }
        
        return user;
    }

    // Method to update user's details (name, password, id)
    public boolean updateUserDetails(String card, String newName, String newPassword, int newId) {
        User user = LibraryDatabase.users.get(card);
        
        if (user == null) {
            System.out.println("No user found with this library card.");
            return false;
        }
        
        // Update the user details
        user.setName(newName);
        user.setPassword(newPassword);
        user.setId(newId);
        
        // Save the updated user details
        LibraryDatabase.users.put(card, user);
        LibraryDatabase.userCredentials.put(card, newPassword); // Update the password
        
        System.out.println("User details updated successfully.");
        return true;
    }
}
