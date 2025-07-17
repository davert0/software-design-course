package com.github.davert0.storage;

// Storage.java
public interface Storage {
    void save(String data);
    String retrieve(int id);
}

