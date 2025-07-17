package com.github.davert0;

import com.github.davert0.storage.InMemoryStorage;
import com.github.davert0.storage.Storage;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Storage memoryStorage = new InMemoryStorage();
        memoryStorage.save("Data in memory");
        System.out.println("InMemoryStorage: " + memoryStorage.retrieve(0));

    }
}
