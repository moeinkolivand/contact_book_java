package com.tutorial.contact;

@FunctionalInterface
public interface ContactRule {
    String check(Contact contact);
}
