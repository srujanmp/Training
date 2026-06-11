// java -cp .:mysql-connector-j-9.0.0.jar Main

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// copy pasted
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/nitte2026";
        String user = "Srujan";
        String password = "Srujan@12345";

        // Try-with-resources handles opening and automatic closing of connections and statements
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            
            System.out.println("Connected to the database!");

            // 1. Create a table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(50) NOT NULL, "
                    + "course VARCHAR(50)"
                    + ")";
            statement.execute(createTableSQL);
            System.out.println("Table 'students' verified/created.");

            // 2. Insert data into the table
            String insertSQL = "INSERT INTO students (name, course) VALUES ('Srujan', 'Information Science')";
            int rowsAffected = statement.executeUpdate(insertSQL);
            System.out.println("Inserted " + rowsAffected + " row(s) successfully.");

            // 3. Read data from the table (Query)
            String selectSQL = "SELECT * FROM students";
            try (ResultSet resultSet = statement.executeQuery(selectSQL)) {
                System.out.println("\n--- Student Records ---");
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String course = resultSet.getString("course");
                    System.out.println("ID: " + id + " | Name: " + name + " | Course: " + course);
                }
                System.out.println("-----------------------\n");
            }

        } catch (SQLException e) {
            System.err.println("Database error occurred:");
            e.printStackTrace();
        }
    }
}