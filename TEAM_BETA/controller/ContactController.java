package controller;

import service.ContactService;
import entity.Contact;
import java.util.List;
import java.util.Scanner;

public class ContactController {
    private ContactService service;
    private Scanner scanner;

    public ContactController() {
        this.service = new ContactService();
        this.scanner = new Scanner(System.in);
        preloadContacts();  // Preload initial contacts
    }

    private void preloadContacts() {
        service.addContact("Ayush", "7428860615");
        service.addContact("Yashasvi", "9289435155");
        service.addContact("Yashika", "1234567890");
        service.addContact("Animesh", "4545254656");
        service.addContact("Aryan", "255214785214");
    }

    public void run() {
        int choice;
        do {
            System.out.println("\n--- Contact Application Menu ---");
            System.out.println("1. Add Contact");
            System.out.println("2. Search by Name");
            System.out.println("3. Search by Initial");
            System.out.println("4. Search by Phone Number");
            System.out.println("5. List All Contacts");
            System.out.println("6. Delete Contact by Phone Number");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    searchByName();
                    break;
                case 3:
                    searchByInitial();
                    break;
                case 4:
                    searchByPhoneNumber();
                    break;
                case 5:
                    listAllContacts();
                    break;
                case 6:
                    deleteContact();
                    break;
                case 0:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 0);
    }

    private void addContact() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        service.addContact(name, phone);
        System.out.println("Contact added successfully!");
    }

    private void searchByName() {
        System.out.print("Enter Name to Search: ");
        String name = scanner.nextLine();
        List<Contact> results = service.searchByName(name);
        displayResults(results);
    }

    private void searchByInitial() {
        System.out.print("Enter Initial to Search: ");
        char initial = scanner.nextLine().charAt(0);
        List<Contact> results = service.searchByInitial(initial);
        displayResults(results);
    }

    private void searchByPhoneNumber() {
        System.out.print("Enter Phone Number to Search: ");
        String phone = scanner.nextLine();
        List<Contact> results = service.searchByPhoneNumber(phone);
        displayResults(results);
    }

    private void listAllContacts() {
        List<Contact> contacts = service.getAllContacts();
        displayResults(contacts);
    }

    private void deleteContact() {
        System.out.print("Enter Phone Number of the Contact to Delete: ");
        String phone = scanner.nextLine();
        boolean deleted = service.deleteContact(phone);
        if (deleted) {
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found!");
        }
    }

    private void displayResults(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }
}
