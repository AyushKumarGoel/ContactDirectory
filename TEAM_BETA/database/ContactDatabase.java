package database;

import entity.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactDatabase {
    private static List<Contact> contacts = new ArrayList<>();

    public static List<Contact> getContacts() {
        return contacts;
    }
}
