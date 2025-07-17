package com.github.davert0;

import com.github.davert0.storage.DBStorage;
import com.github.davert0.storage.Storage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbStorageDemo {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Storage storage = new DBStorage(connection);
        storage.save("Opaopaopapapa");
        System.out.println(storage.retrieve(1));
    }
}
