# Java 8 in Practice – Contact Book

This project is a small demonstration of the features introduced in **Java 8**. Instead of building a production-ready contact book, the goal is to showcase how Java 8 enables a more declarative and functional programming style.

The project demonstrates concepts such as:

* Lambda Expressions
* Functional Interfaces
* Stream API
* Method References
* Predicate
* Optional
* Generic Repository
* Comparator
* Declarative Programming

## Project Structure

```
App
 ├── Repository<T, ID>
 ├── ContactBook
 ├── ContactValidator
 ├── ContactRule (Functional Interface)
 ├── FilterContacts
 ├── Contact
 └── ContactSeeder
```

## Features

### Generic Repository

A simple generic repository implementation demonstrating the use of Generics together with `Optional`.

```java
Repository<Contact, String>
```

---

### Functional Validation

Validation rules are implemented using a custom Functional Interface.

```java
@FunctionalInterface
public interface ContactRule {
    String check(Contact contact);
}
```

Rules can be added dynamically using lambda expressions.

```java
validator.addRule(contact ->
    contact.getEmail().contains("@")
        ? null
        : "Invalid email");
```

---

### Stream API

Filtering

```java
contacts.stream()
        .filter(...)
        .toList();
```

Sorting

```java
contacts.stream()
        .sorted()
        .toList();
```

Grouping

```java
Collectors.groupingBy(...)
```

Mapping

```java
.map(...)
```

---

### Predicate

The project demonstrates how behavior can be passed into methods using `Predicate<Contact>`.

```java
FilterContacts.filterContacts(
    contacts,
    contact -> contact.getName().startsWith("A")
);
```

---

### Optional

The repository returns `Optional<T>` instead of `null`.

```java
repository.findById(id)
```

This encourages explicit handling of missing values.

---

## Why this project?

I recently started learning Java after several years of Python development.

One thing that immediately stood out was how Java 8 fundamentally changed the way Java code is written. This project was created to explore those concepts through code instead of only reading documentation.

The objective is not to build a complete application, but to demonstrate how Java 8 introduced a more declarative programming model while preserving Java's static typing and backward compatibility.

