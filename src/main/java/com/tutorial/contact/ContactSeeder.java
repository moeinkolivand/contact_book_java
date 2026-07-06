package com.tutorial.contact;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ContactSeeder {

    private static final String[] FIRST_NAMES = {
            "Alice", "Bob", "Charlie", "Diana", "Eve", "Frank", "Grace",
            "Hank", "Ivy", "Jack", "Kate", "Leo", "Mia", "Noah", "Olivia",
            "Paul", "Quinn", "Rose", "Sam", "Tina"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia",
            "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez",
            "Gonzalez", "Wilson", "Anderson", "Thomas", "Taylor", "Moore",
            "Jackson", "Martin"
    };

    private static final String[] EMAIL_DOMAINS = {
            "example.com", "mail.com", "test.org", "contact.net", "demo.io"
    };

    public static List<Contact> generateContacts(int count) {
        List<Contact> contacts = new ArrayList<>();
        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < count; i++) {
            String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
            String name = firstName + " " + lastName;

            String email = (firstName.toLowerCase() + "." + lastName.toLowerCase()
                    + "@" + EMAIL_DOMAINS[random.nextInt(EMAIL_DOMAINS.length)]);

            String phone = generatePhoneNumber(random);

            contacts.add(new Contact(name, email, phone));
        }
        return contacts;
    }

    private static String generatePhoneNumber(ThreadLocalRandom random) {
        // US-style: (XXX) XXX-XXXX
        int area = 100 + random.nextInt(800);      // 100-899
        int prefix = 100 + random.nextInt(900);    // 100-999
        int line = 1000 + random.nextInt(9000);    // 1000-9999
        return String.format("(%03d) %03d-%04d", area, prefix, line);
    }

    // Quick demo
    public static void main(String[] args) {
        List<Contact> contacts = generateContacts(10);
        contacts.forEach(System.out::println);
    }
}