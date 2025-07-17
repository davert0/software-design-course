package com.github.davert0.storage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DBStorage implements Storage {

    private Connection connection;

    public DBStorage(Connection connection) {
        this.connection = connection;
        initializeTable();
    }

    private void initializeTable() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS storage (
                id INT AUTO_INCREMENT PRIMARY KEY,
                data VARCHAR(255) NOT NULL
            )
            """;
        try (PreparedStatement ps = connection.prepareStatement(createTableSQL)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create storage table", e);
        }
    }

    @Override
    public void save(String data) {
        String sql = "INSERT INTO storage(data) VALUES (?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, data);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e); // или своя обработка ошибок
        }
    }

    @Override
    public String retrieve(int id) {
        String sql = "SELECT data FROM storage WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("data");
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
