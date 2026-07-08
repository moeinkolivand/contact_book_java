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

/*
Before Java 8 Implementation (Imperative Style)
public class ContactBook {
    private final Repository<Contact, String> repository;

    public ContactBook(Repository<Contact, String> repository) {
        this.repository = repository;
    }

    public List<Contact> findByNameContaining(String keyword) {
        List<Contact> result = new ArrayList<Contact>();
        for (Contact contact : this.repository.findAll()) {
            if (contact.getName().contains(keyword)) {
                result.add(contact);
            }
        }
        return result;
    }

    public List<Contact> sortedByName() {
        // Previously relied on Contact implementing Comparable and stream().sorted()
        List<Contact> result = new ArrayList<Contact>(this.repository.findAll());
        Collections.sort(result);
        return result;
    }

    public Map<Character, List<Contact>> groupByFirstLetter() {
        Map<Character, List<Contact>> grouped = new HashMap<Character, List<Contact>>();
        for (Contact contact : this.repository.findAll()) {
            if (!contact.getName().isEmpty()) {
                Character firstLetter = contact.getName().charAt(0);
                List<Contact> bucket = grouped.get(firstLetter);
                if (bucket == null) {
                    bucket = new ArrayList<Contact>();
                    grouped.put(firstLetter, bucket);
                }
                bucket.add(contact);
            }
        }
        return grouped;
    }

    public List<Contact> sortedByEmail() {
        List<Contact> filtered = new ArrayList<Contact>();
        for (Contact contact : this.repository.findAll()) {
            if (!contact.getName().isEmpty()) {
                filtered.add(contact);
            }
        }

        Collections.sort(filtered, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return c1.getEmail().compareTo(c2.getEmail());
            }
        });

        return filtered;
    }

}
*/
