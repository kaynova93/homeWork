package org.ibs.testDB;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseTestSQL {
    static Connection connection;

    @BeforeAll
    static Connection initDB() throws SQLException {
        connection = DriverManager
                .getConnection("jdbc:h2:tcp://localhost:9092/mem:testdb",
                        "user", "pass");
        return connection;
    }

    @AfterAll
    static void close() throws SQLException {
        connection.close();
    }
}
