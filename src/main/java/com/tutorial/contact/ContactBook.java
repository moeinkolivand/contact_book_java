package com.tutorial.contact;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContactBook {
    private final Repository<Contact, String> repository;

    public ContactBook(Repository<Contact, String> repository) {
        this.repository = repository;
    }

    public List<Contact> findByNameContaining(String keyword) {
        return this.repository.findAll().stream().filter(contact -> contact.getName().contains(keyword)).toList();
    }

    public List<Contact> sortedByName() {
        return this.repository.findAll().stream().sorted().toList();
    }

    public Map<Character, List<Contact>> groupByFirstLetter() {
        return this.repository.findAll().stream().filter(contact -> !contact.getName().isEmpty()).collect(Collectors.groupingBy(contact -> contact.getName().charAt(0)));
    }

    public List<Contact> sortedByEmail() {
        return this.repository.findAll().stream().filter(contact -> !contact.getName().isEmpty()).sorted(Comparator.comparing(Contact::getEmail)).toList();
    }

}
