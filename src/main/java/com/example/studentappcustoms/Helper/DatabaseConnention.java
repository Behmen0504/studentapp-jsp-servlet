package com.example.studentappcustoms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnention {
    private static Connection connection;

    public static Connection connectToDatabase() throws Exception {
        if(connection==null){
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "memmedov516");
        }
        return connection;
    }
    public static boolean closeConnection(Connection c) {
        if (c!=null){
            try {
                c.close();
                return true;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            return false;
        }
    }
}
