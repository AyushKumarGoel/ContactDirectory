import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
@Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber;
    }
}

class ContactManager {
    private List<Contact> contacts = new ArrayList<>();

    public void addContact(String name, String phoneNumber) {
        contacts.add(new Contact(name, phoneNumber));
        System.out.println("Contact added successfully!");
    }

    public void searchByName(String name) {
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                System.out.println(contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contact found with name: " + name);
        }
    }

    public void searchByInitial(char initial) {
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().toLowerCase().charAt(0) == Character.toLowerCase(initial)) {
                System.out.println(contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contact found with initial: " + initial);
        }
    }

    public void searchByPhoneNumber(String phoneNumber) {
        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                System.out.println(contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contact found with phone number: " + phoneNumber);
        }
    }

    public void listContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts available.");
        } else {
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        }
    }
}

public class ContactApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactManager manager = new ContactManager();
        int choice;

        do {
            System.out.println("\n--- Contact Application Menu ---");
            System.out.println("1. Add Contact");
            System.out.println("2. Search by Name");
            System.out.println("3. Search by Initial");
            System.out.println("4. Search by Phone Number");
            System.out.println("5. List All Contacts");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.nextLine();
                    manager.addContact(name, phone);
                    break;
                case 2:
                    System.out.print("Enter Name to Search: ");
                    String searchName = scanner.nextLine();
                    manager.searchByName(searchName);
                    break;
                case 3:
                    System.out.print("Enter Initial to Search: ");
                    char initial = scanner.nextLine().charAt(0);
                    manager.searchByInitial(initial);
                    break;
                case 4:
                    System.out.print("Enter Phone Number to Search: ");
                    String searchPhone = scanner.nextLine();
                    manager.searchByPhoneNumber(searchPhone);
                    break;
                case 5:
                    manager.listContacts();
                    break;
                case 0:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
