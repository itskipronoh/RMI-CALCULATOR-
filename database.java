import java.sql.*;

class StudentDatabase {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/student"; // Replace 'mydatabase' with your database name
    private static final String DB_USER = "james"; // Replace 'username' with your database username
    private static final String DB_PASSWORD = "admin"; // Replace 'password' with your database password

    // Establish a connection to the database
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "james", "admin");
    }

    // Display metadata of the database connection
    private static void printMetadata(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        System.out.println("Database Product Name: " + metaData.getDatabaseProductName());
        System.out.println("Database Product Version: " + metaData.getDatabaseProductVersion());
        System.out.println("Driver Name: " + metaData.getDriverName());
        System.out.println("Driver Version: " + metaData.getDriverVersion());
    }

    // Insert student information into the database
    private static void insertStudent(Connection connection, int id, String name, int age) throws SQLException {
        String sql = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, age);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Student inserted successfully.");
            } else {
                System.out.println("Failed to insert student.");
            }
        }
    }

    // Update student information in the database
    private static void updateStudent(Connection connection, int id, String name, int age) throws SQLException {
        String sql = "UPDATE students SET name = ?, age = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setInt(3, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Student not found or update failed.");
            }
        }
    }

    // Delete student information from the database
    private static void deleteStudent(Connection connection, int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found or delete failed.");
            }
        }
    }

    // Display all student information from the database
    private static void displayStudents(Connection connection) throws SQLException {
        String sql = "SELECT * FROM students";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
            }
        }
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            // Print metadata of the database connection
            printMetadata(connection);

            // Insert student information
            insertStudent(connection, 1, "John Doe", 22);
            insertStudent(connection, 2, "Jane Smith", 21);

            // Display all students
            System.out.println("\nStudents in the database:");
            displayStudents(connection);

            // Update student information
            updateStudent(connection, 1, "John Johnson", 23);

            // Display all students after update
            System.out.println("\nStudents in the database after update:");
            displayStudents(connection);

            // Delete student
            deleteStudent(connection, 2);

            // Display all students after delete
            System.out.println("\nStudents in the database after delete:");
            displayStudents(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
