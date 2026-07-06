package com.tutorial.contact;

public class Contact implements Comparable<Contact> {
    private final String name;
    private final String email;
    private final String phone;

    public Contact(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
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
