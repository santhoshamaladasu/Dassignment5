import java.sql.*;

public class StoreDepartment {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/departments";
    public static final String USER = "username"; // Replace with your username
    public static final String PASS = "password"; // Replace with your password

    public static void main(String[] args) {

        // Department object
        Department department = new Department(10, "Engineering");

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 1. Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Connect to the database
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // 3. Prepare the SQL statement with placeholders
            String sql = "INSERT INTO department (id, name) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(sql);

            // 4. Set values for the prepared statement
            preparedStatement.setInt(1, department.getId());
            preparedStatement.setString(2, department.getName());

            // 5. Execute the update query
            preparedStatement.executeUpdate();

            System.out.println("Department inserted successfully!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6. Close resources (in reverse order)
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and setters (optional)
}
