package org.example.Amazon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * This is a very naive database connection class.
 * In real life, you should make use of a decent database API,
 * such as Spring Data or Hibernate.
 */
public class Database {
    private static Connection connection;

    public Database() {
        if (connection != null) return;
        withSql(() -> {
            connection = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb.db", "SA", "");
            // Create the shoppingcart table if it doesn't exist
            try (var preparedStatement = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS shoppingcart (" +
                            "type VARCHAR(100), " +
                            "name VARCHAR(100), " +
                            "quantity INT, " +
                            "priceperunit DOUBLE)")) {
                preparedStatement.execute();
                connection.commit();
            }
            return null;
        });
    }



    public Connection getConnection() {
        return connection;
    }



    public void resetDatabase() {
        withSql(() -> {
            // Clear all records from the shoppingcart table
            try (var preparedStatement = connection.prepareStatement("DELETE FROM shoppingcart")) {
                preparedStatement.execute();
                connection.commit();
            }
            return null;
        });
    }



    public interface SqlSupplier<T> {
        T doSql() throws SQLException;
    }



    public <T> T withSql(SqlSupplier<T> sqlSupplier) {
        try {
            return sqlSupplier.doSql();
        } catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }



    public void close() {
        withSql(() -> {
            if (connection != null) {
                connection.close();
            }
            return null;
        });
        connection = null;
    }
}
