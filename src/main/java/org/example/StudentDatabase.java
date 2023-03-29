package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDatabase {
    // Create a connection to the MySQL Server
    private static final String URL = "jdbc:mysql://localhost:3306/imsdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // Execute the SELECT query to retrieve all students from the table
            String sql = "SELECT * FROM students";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Map the result set to Student objects
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                int age = resultSet.getInt("age");
                String major = resultSet.getString("major");

                Student student = new Student(id, name, email, age, major);
                students.add(student);
            }

            // Display the students on the console
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
