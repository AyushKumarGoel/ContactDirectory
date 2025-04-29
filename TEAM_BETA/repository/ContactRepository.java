package repository;

import entity.Contact;
import database.ContactDatabase;
import java.util.List;

public class ContactRepository {

    public void addContact(Contact contact) {
        ContactDatabase.getContacts().add(contact);
    }

    public List<Contact> getAllContacts() {
        return ContactDatabase.getContacts();
    }

    public boolean deleteContact(String phoneNumber) {
        List<Contact> contacts = ContactDatabase.getContacts();
        return contacts.removeIf(contact -> contact.getPhoneNumber().equals(phoneNumber));
    }
}
