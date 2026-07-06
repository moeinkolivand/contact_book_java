package com.tutorial.contact;

import java.util.*;

public class Repository<T, ID> {
    private Map<ID, T> store = new HashMap<>();

    public void save(ID id, T item) {
        store.put(id, item);
    }

    public Optional<T> findById(ID id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<T> findAll() {
        return new ArrayList<>(store.values());
    }

    public void deleteById(ID id) {
        store.remove(id);
    }

}
