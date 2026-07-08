package com.tutorial.contact;


import java.util.function.Predicate;

public class Contact implements Comparable<Contact> {
    private final String name;
    private final String email;
    private final String phone;

    public Contact(String name, String email, String phone) {
        if (phone == null || phone.isEmpty() || phone.isBlank()) {
            throw new IllegalArgumentException("PhoneNumber Cannot Be Blank Or Null");
        }
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Contact() {
        this("", "", "0000000000");
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Contact contact) {
        return this.name.compareTo(contact.getName());
    }

    @Override
    public String toString() {
        return this.name + " " + this.email;
    }
}
