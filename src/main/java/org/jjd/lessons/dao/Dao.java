package org.jjd.lessons.dao;

import java.util.List;

public interface Dao<T, PK> {
    // где, T - тип данных класса сущности
    //      PK - тип данных первичного ключа
    // добавление в таблицу
    void add(T entity);
    // получение по первичному ключу
    T getByPK(PK pk);
    // получение всех записей
    List<T> getAll();
    // обновление данных
    void update(T entity);
    // удаление по первичному ключу
    void deleteByPK(PK pk);
}
