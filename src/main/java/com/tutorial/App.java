package com.tutorial;

import com.tutorial.contact.*;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

public class App {
    public static void main(String[] args) {
        List<Contact> contactList = ContactSeeder.generateContacts(10);

        // ---- ContactValidator ----
        ContactValidator contactValidator = new ContactValidator();

        ContactRule hasValidName = contact ->
                contact.getName() == null || contact.getName().isBlank()
                        ? "Contact " + contact.getPhone() + " has no name"
                        : null;

        ContactRule hasValidEmail = contact ->
                contact.getEmail() != null && contact.getEmail().contains("@")
                        ? null
                        : "Email is not valid for contact " + contact.getPhone();

        ContactRule hasValidPhoneNumber = contact ->
                contact.getPhone() != null && contact.getPhone().chars().allMatch(Character::isDigit)
                        ? null
                        : "Phone number is not valid for contact " + contact.getPhone();

        contactValidator.addRule(List.of(hasValidName, hasValidEmail, hasValidPhoneNumber));

        Contact invalidContact = new Contact("", "", "123abc");
        List<String> validate = contactValidator.validate(invalidContact);
        System.out.println("--- Validation Errors ---");
        validate.forEach(System.out::println);

        // ---- Predicates ----
        Predicate<Contact> predictHasValidEmail = contact ->
                contact.getEmail() != null && contact.getEmail().contains("@");

        Predicate<Contact> predicateHasPhoneNumber = contact ->
                contact.getPhone() != null && !contact.getPhone().isBlank();

        Predicate<Contact> nameStartsWithA = c ->
                c.getName() != null && c.getName().startsWith("A");

        // ---- Functions ----
        // 1. Unbound instance method reference
        Function<Contact, String> toDisplayName = Contact::getName;

        // 2. Custom function
        Function<Contact, String> toEmailDomain = contact -> {
            String email = contact.getEmail();
            int atIndex = email.indexOf('@');
            return atIndex >= 0 ? email.substring(atIndex + 1) : "";
        };

        // ---- Supplier ----
        // 3. Constructor reference
        Supplier<Contact> unknownContact = Contact::new;

        // ---- Consumer ----
        // 4. Bound instance method reference (example)
        Consumer<Contact> prettyPrintContact = System.out::println; // bound instance

        // ---- BiFunction ----
        BiFunction<Contact, Contact, Boolean> checkTwoContactHaveSameEmail =
                (contactOne, contactTwo) -> contactOne.getEmail().equals(contactTwo.getEmail());

        // ---- Supplier usage with Optional ----
        System.out.println("\n--- Supplier with Optional ---");
        Optional<Contact> optionalContact = Optional.ofNullable(null);
        Contact defaultContact = optionalContact.orElseGet(unknownContact);
        prettyPrintContact.accept(defaultContact);

        // ---- Filtering ----
        List<Contact> invalidContacts = ContactSeeder.generateInvalidContacts();

        Predicate<Contact> complexFilter = nameStartsWithA
                .and(predicateHasPhoneNumber)
                .or(predictHasValidEmail.negate());

        List<Contact> filteredContactList = FilterContacs.filterContacs(invalidContacts, complexFilter);

        System.out.println("\n--- Filtered Contacts ---");
        filteredContactList.forEach(prettyPrintContact);

        // ---- Function chaining ----
        System.out.println("\n--- Email Domains (Uppercase) ---");
        contactList.stream()
                .map(toEmailDomain.andThen(String::toUpperCase))
                .forEach(System.out::println);

        // ---- Consumer in forEach ----
        System.out.println("\n--- All Contacts ---");
        contactList.forEach(prettyPrintContact);

        // ---- BiFunction ----
        boolean twoContactIsSame = checkTwoContactHaveSameEmail.apply(
                contactList.get(0), contactList.get(1)
        );
        System.out.println("\n--- BiFunction Result ---");
        System.out.println("twoContactIsSame = " + twoContactIsSame);
    }
}