package service;

import entity.Contact;
import java.util.List;
import java.util.stream.Collectors;
import repository.ContactRepository;

public class ContactService {
    private ContactRepository repository;

    public ContactService() {
        this.repository = new ContactRepository();
    }

    public void addContact(String name, String phoneNumber) {
        Contact contact = new Contact(name, phoneNumber);
        repository.addContact(contact);
    }

    public List<Contact> searchByName(String name) {
        return repository.getAllContacts().stream()
                .filter(contact -> contact.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Contact> searchByInitial(char initial) {
        return repository.getAllContacts().stream()
                .filter(contact -> contact.getName().toLowerCase().charAt(0) == Character.toLowerCase(initial))
                .collect(Collectors.toList());
    }

    public List<Contact> searchByPhoneNumber(String phoneNumber) {
        return repository.getAllContacts().stream()
                .filter(contact -> contact.getPhoneNumber().equals(phoneNumber))
                .collect(Collectors.toList());
    }

    public List<Contact> getAllContacts() {
        return repository.getAllContacts();
    }

    public boolean deleteContact(String phoneNumber) {
        return repository.deleteContact(phoneNumber);
    }
    public List<Contact> searchByPhoneSubstring(String partial) {
        return repository.getAllContacts().stream()
                .filter(contact -> contact.getPhoneNumber().contains(partial))
                .collect(Collectors.toList());
    }
    
    public boolean editContact(String oldPhoneNumber, String newName, String newPhoneNumber) {
        List<Contact> contacts = repository.getAllContacts();
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(oldPhoneNumber)) {
                contacts.remove(contact);
                Contact updated = new Contact(newName.isEmpty() ? contact.getName() : newName,
                                              newPhoneNumber.isEmpty() ? contact.getPhoneNumber() : newPhoneNumber);
                contacts.add(updated);
                return true;
            }
        }
        return false;
    }
    
}
