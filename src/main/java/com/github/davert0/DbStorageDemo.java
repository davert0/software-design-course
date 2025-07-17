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
        
        storage.save("Первая строка данных");
        storage.save("Вторая строка данных");
        storage.save("Третья строка данных");
        storage.save("Четвертая строка данных");
        storage.save("Пятая строка данных");
        

        for (int i = 1; i <= 5; i++) {
            String retrieved = storage.retrieve(i);
            System.out.println("ID " + i + ": " + retrieved);
        }
        

        String nonExistent = storage.retrieve(999);
        System.out.println("ID 999: " + (nonExistent == null ? "не найдено" : nonExistent));
    }
}
