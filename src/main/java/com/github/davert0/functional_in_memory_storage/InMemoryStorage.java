package com.github.davert0.functional_in_memory_storage;

import java.util.HashMap;
import java.util.Map;

public class InMemoryStorage {
    private final Map<Integer, String> storage;
    private final int counter;

    public InMemoryStorage() {
        this.storage = new HashMap<>();
        this.counter = 0;
    }

    private InMemoryStorage(Map<Integer, String> storage, int counter) {
        this.storage = new HashMap<>(storage);
        this.counter = counter;
    }

    public InMemoryStorage save(String data) {
        Map<Integer, String> newStorage = new HashMap<>(storage);
        newStorage.put(counter, data);
        return new InMemoryStorage(newStorage, counter + 1);
        }


    public String retrieve(int id) {
        return storage.get(id);
    }
}