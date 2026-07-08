package com.tutorial.contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactValidator {
    private List<ContactRule> contactRules = new ArrayList<>();

    public void addRule(ContactRule contactRule) {
        this.contactRules.add(contactRule);
    }

    public void addRule(List<ContactRule> contactRule) {
        this.contactRules.addAll(contactRule);
    }

    public List<String> validate(Contact contact) {
        return this.contactRules.stream().map(rule -> rule.check(contact)).filter(Objects::nonNull).toList();
    }

    ;
}
