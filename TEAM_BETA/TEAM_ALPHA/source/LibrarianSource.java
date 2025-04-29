package source;

import database.LibraryDatabase;

public class LibrarianSource {
    // Authenticate librarian by username and password
    public boolean authenticateLibrarian(String username, String password) {
        return LibraryDatabase.userCredentials.getOrDefault(username, "").equals(password);
    }
}
