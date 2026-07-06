package com.tutorial;
import com.tutorial.contact.Contact;
import com.tutorial.contact.ContactBook;
import com.tutorial.contact.ContactSeeder;
import com.tutorial.contact.Repository;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class App
{
    public static void main( String[] args )
    {
        List<Contact> contactList = ContactSeeder.generateContacts(10);
        contactList.forEach(System.out::println);
        Repository<Contact, String> contactRepository = new Repository<>();
        IntStream.range(0, contactList.size()).forEach(i -> contactRepository.save(String.valueOf(i), contactList.get(i)));
        ContactBook contactBook = new ContactBook(contactRepository);
        List<Contact> nameContaining = contactBook.findByNameContaining("A");
        nameContaining.forEach(System.out::println);
        List<Contact> sortedByName = contactBook.sortedByName();
        sortedByName.forEach(System.out::println);
        Map<Character, List<Contact>> characterListMap = contactBook.groupByFirstLetter();
        characterListMap.forEach((letter, contacts) -> {
            System.out.println(letter + ":");
            contacts.forEach(c -> System.out.println("  " + c));
        });
        contactRepository.findById("1").ifPresentOrElse(contact -> System.out.println("contact.getName() = " + contact.getName()), () -> System.out.println("Id Does Not Exists"));
    }
}
