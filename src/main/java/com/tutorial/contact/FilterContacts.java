package com.tutorial.contact;

import java.util.List;
import java.util.function.Predicate;

public class FilterContacts {
    public static List<Contact> filterContacts(List<Contact> contactList, Predicate<Contact> filters) {
      return contactList.stream().filter(filters).toList();
    }
}
