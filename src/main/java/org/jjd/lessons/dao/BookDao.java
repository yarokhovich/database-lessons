package org.jjd.lessons.dao;

import java.util.List;

public class BookDao implements Dao<Book, Integer>{

    @Override
    public void add(Book entity) { }

    @Override
    public Book getByPK(Integer integer) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public void deleteByPK(Integer integer) { }


}
