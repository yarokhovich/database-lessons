package org.jjd.lessons.jdbc;

import java.sql.*;
import java.util.Arrays;
import java.util.HashSet;

public class Examples {
    // строка подключения
    private static final String CONNECTION_STR =
            "jdbc:postgresql://localhost:5432/lessons";
    // логин
    private static final String LOGIN = "ifmo";
    // пароль
    private static final String PASSWORD = "ifmo";

    private static void createTable(){
        // строка sql запроса
        String createString = "CREATE TABLE IF NOT EXISTS course (" +
                "id SERIAL PRIMARY KEY," +
                "title VARCHAR(255) NOT NULL," +
                "duration SMALLINT," +
                "price NUMERIC(9,2)" +
                ")";

        try {   // регистрация драйвера, загрузка класса
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Драйвер не найден");
        }
        // import java.sql.*
        try (Connection connection =
                     DriverManager.getConnection(CONNECTION_STR, LOGIN, PASSWORD)) {
            try (Statement statement = connection.createStatement()){
                System.out.println(statement.executeUpdate(createString));
            }
        } catch (SQLException e) {
            System.out.println("Не удалось выполнить запрос " + e.getMessage());
        }
    }

    private static void insertIntoCourse(Course course) {
        String insertSql = "INSERT INTO course (title, duration, price)" +
                "VALUES(?, ?, ?)";

        try {   // регистрация драйвера, загрузка класса
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Драйвер не найден");
        }

        try (Connection connection =
                     DriverManager.getConnection(CONNECTION_STR, LOGIN, PASSWORD)){
            try (PreparedStatement statement = connection.prepareStatement(insertSql)){
                statement.setString(1, course.getTitle());
                statement.setInt(2, course.getDuration());
                statement.setDouble(3, course.getPrice());
                System.out.println(statement.executeUpdate());
            }
        } catch (SQLException e) {
            System.out.println("Не удалось выполнить запрос " + e.getMessage());
        }
    }

    private static HashSet<Course> selectAll(){
        String selectAll = "SELECT * FROM course";
        HashSet<Course> courses = new HashSet<>();
        try {   // регистрация драйвера, загрузка класса
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Драйвер не найден");
        }

        try (Connection connection =
                     DriverManager.getConnection(CONNECTION_STR, LOGIN, PASSWORD)){
            try (Statement statement = connection.createStatement()){
                try (ResultSet result = statement.executeQuery(selectAll)){
                    while (result.next()){
                        int id = result.getInt("id"); // номер или название столбца
                        String title = result.getString("title");
                        int duration = result.getInt("duration");
                        double price = result.getDouble("price");
                        Course course = new Course(title, duration, price);
                        course.setId(id);
                        courses.add(course);
                    }
                }
            }
        } catch (SQLException e){
            System.out.println("Не удалось выполнить запрос " + e.getMessage());
        }
        return courses;
    }

    private static HashSet<Course> getByPrice(double price) {
         // стоимость больше price
        // "SELECT * FROM course WHERE price > ?";
        HashSet<Course> courses = new HashSet<>();
        return courses;
    }

    private static void bufferInsert(HashSet<Course> courses){
        String insertSql = "INSERT INTO course (title, duration, price)" +
                "VALUES(?, ?, ?)";

        try {   // регистрация драйвера, загрузка класса
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Драйвер не найден");
        }

        try (Connection connection =
                     DriverManager.getConnection(CONNECTION_STR, LOGIN, PASSWORD)){
            try (PreparedStatement statement = connection.prepareStatement(insertSql)){
                for (Course course : courses) {
                    statement.setString(1, course.getTitle());
                    statement.setInt(2, course.getDuration());
                    statement.setDouble(3, course.getPrice());
                    statement.addBatch();
                }
                // [1, 1, 1]
                System.out.println(Arrays.toString(statement.executeBatch()));
            }
        } catch (SQLException e) {
            System.out.println("Не удалось выполнить запрос " + e.getMessage());
        }
    }



    public static void main(String[] args) {
        createTable();
        /*insertIntoCourse(new Course("Java", 3, 40000)); // 1
        insertIntoCourse(new Course("Python", 2, 30000)); // 1
        insertIntoCourse(new Course("C++", 3, 40000)); // 1*/

        System.out.println(selectAll());

        // SELECT title, price FROM course;

        HashSet<Course> courses = new HashSet<>();
        courses.add(new Course("Java", 4, 50000));
        courses.add(new Course("JavaScript", 2, 20000));
        courses.add(new Course("Node JS", 3, 30000));

        bufferInsert(courses);
    }
}
