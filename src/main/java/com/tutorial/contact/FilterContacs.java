package com.tutorial.contact;

import java.util.List;
import java.util.function.Predicate;

public class FilterContacs {
    public static List<Contact> filterContacs(List<Contact> contactList, Predicate<Contact> filters) {
      return contactList.stream().filter(filters).toList();
    }
}
