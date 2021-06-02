package org.jjd.lessons.dao;

import org.jjd.lessons.pool.PoolDataSource;

import java.sql.*;
import java.util.List;

public class BookDao implements Dao<Book, Integer>{

    public void createTable(){ // Book.class, Course.class
        String createSql = "CREATE TABLE IF NOT EXISTS tb_books (" +
                "id SERIAL PRIMARY KEY," +
                "title VARCHAR(200) NOT NULL," +
                "page_count INT NOT NULL" +
                ")";
        // Connection connection = PoolDataSource.getConnection();
        try (Statement statement = PoolDataSource.getConnection().createStatement()){
            statement.executeUpdate(createSql);
        } catch (SQLException e) {
            System.out.println("Запрос не был выполнен " + e.getMessage());
        }
    }

    @Override
    public void add(Book entity) {
        String insertSql = "INSERT INTO tb_books(title, page_count) VALUES(?, ?)";
        try (PreparedStatement statement =
                     PoolDataSource.getConnection().prepareStatement(insertSql)){
            statement.setString(1, entity.getTitle());
            statement.setInt(2, entity.getPageCount());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()){  // entity.setId();
                if (resultSet.next()) {
                   entity.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Запрос не был выполнен " + e.getMessage());
        }
    }

    @Override
    public Book getByPK(Integer integer) { // по id
        String sql = "SELECT * FROM tb_books WHERE id = ?";

        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override //  save(Book entity): add или update в зависимости от
    // наличия или отсутствия первичного ключа
    public void update(Book entity) {
        String update = "UPDATE tb_books SET title = ?, page_count = ?" +
                "WHERE id = ?";
        try (PreparedStatement statement =
                     PoolDataSource.getConnection().prepareStatement(update)){
            statement.setString(1, entity.getTitle());
            statement.setInt(2, entity.getPageCount());
            statement.setInt(3, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Запрос не был выполнен " + e.getMessage());
        }
    }

    @Override
    public void deleteByPK(Integer integer) { }


}
