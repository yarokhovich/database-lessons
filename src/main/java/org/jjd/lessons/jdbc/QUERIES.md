### Таблицы создаются при помощи CREATE TABLE:

    CREATE TABLE [IF NOT EXISTS] имя_таблицы (
        имя_столбца1 тип_данных(размер) доп._информация,
        имя_столбца2 тип_данных(размер) доп._информация,
        имя_столбца3 тип_данных(размер) доп._информация
        и т.д.
    );

    CREATE TABLE IF NOT EXISTS course (
        id SERIAL PRIMARY KEY,
        title VARCHAR(255),
        duration smallint,
        price NUMERIC(9,2)
    );

    CREATE TABLE teacher (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255),
        salary NUMERIC(9,2),
        course_id INTEGER NOT NULL
    );

Будут созданы таблицы:

    Column    | Type                   | Modifiers
    ----------+------------------------+-----------
    id        | integer                | not null
    title     | character varying(255) |
    duration  | smallint               |
    price     | numeric                |

    Column     | Type                   | Modifiers
    -----------+------------------------+-----------
    id         | integer                | not null
    name       | character varying(255) |
    salary     | numeric                |
    course_id  | integer                | 

    NOT NULL означает, что значение не может быть null

### Таблицы наполняются данными при помощи INSERT INTO:

    INSERT INTO course VALUES (1, 'Python', 150, 20000);
    INSERT INTO course VALUES (2, 'PHP', 174, 25000);
    INSERT INTO course VALUES (3, 'Ruby', 135, 17000);
    INSERT INTO course VALUES (4, 'Java', 145, 30000);
   
    INSERT INTO teacher VALUES (1, 'Алексей Караченцев', 26000, 1);
    INSERT INTO teacher VALUES (2, 'Артур Петроян', 28000, 2);
    INSERT INTO teacher VALUES (3, 'Леонид Попов', 20000, 3);
    INSERT INTO teacher VALUES (4, 'Елена Петрова', 26000, 4);
    INSERT INTO teacher (id, name, salary) VALUES (5, 'Оксана Гусева', 10000);
    INSERT INTO teacher (id, name, salary) VALUES (6, 'Михаил Протасов', 10000);

### Из таблицы можно выбрать данные при помощи SELECT:

    SELECT * FROM course;

    id  | title  | duration | price
    ----+--------+----------+------------
    1   | Python | 150      | 20 000.00 
    2   | PHP    | 174      | 25 000.00
    3   | Ruby   | 135      | 17 000.00 
    4   | Java   | 145      | 30 000.00 


    SELECT * FROM teacher;

    id  | name               | salary        | course_id
    ----+--------------------+---------------+-----------
    1   | Алексе Караченцев  | 26 000.00     | 1
    2   | Артур Петроян      | 28 000.00     | 2
    3   | Леонид Попов       | 20 000.00     | 3
    4   | Елена Петрова      | 26 000.00     | 4

    course_id в таблице teacher соттветсвует id из таблицы course, 
    что позволяет узнать какой курс ведет преподаватель.

Можно выбирать не все столбцы, а только некоторые:

    SELECT title FROM course;

    title   | price
    --------+--------
    Python  | 20 000.00 
    PHP     | 25 000.00
    Ruby    | 17 000.00 
    Java    | 30 000.00 

Можно отфильтровать нужные строки с помощью WHERE:

    SELECT * FROM course WHERE title='Ruby';

    id  | title | duration | price
    ----+-------+----------+--------------
    3   | Ruby  | 135      | 17 000.00 

Можем сортировать при помощи ORDER BY:

    SELECT * FROM course ORDER BY duration;

    id  | title  | duration | price
    ----+--------+----------+--------
    3   | Ruby   | 135 | 17 000.00 
    4   | Java   | 145 | 30 000.00 
    1   | Python | 150 | 20 000.00 
    2   | PHP    | 174 | 25 000.00 


    SELECT * FROM course ORDER BY duration DESC; 

    в обратном порядке
    id  | title  | duration | price
    ----+--------+----------+------------
    2   | PHP    | 174      | 25 000.00 
    1   | Python | 150      | 20 000.00 
    4   | Java   | 145      | 30 000.00 
    3   | Ruby   | 135      | 17 000.00 

Агрегирующие функции:

    SELECT count(*) as course_count from course;
    
    количество строк 
    course_count
    -------
    4


    SELECT sum(price) as price_sum FROM course;

    сумма по столбцу price
    price_sum
    -----------
    92 000.00 


    SELECT avg(price) as price_avg FROM course;

    среднее значение по столбцу price
    price_avg
    -------
    23000

    Оператор AS позволяет назначать другие имена столбцам.

### LIMIT позволяет ограничить количество результирующих строк:

    SELECT * FROM course ORDER BY price LIMIT 2;

    id  | title  | duration | price
    ----+--------+----------+--------
    3   | Ruby   | 135 | 17 000.00 
    1   | Python | 150 | 20 000.00 

### Удаление строк осуществляется с помощью DELETE:

    DELETE * FROM имя_таблицы WHERE условие удаления;

    DELETE * FROM course WHERE title='PHP';

    Если сделать DELETE без фильтра, то он удалит все значения из таблицы.

### Для удаления таблицы используется DROP TABLE: `DROP TABLE course;`

### Обновление строк: 
   
      UPDATE имя_таблицы SET имя_столбца = 'новое значение' WHERE условие обновления;

      UPDATE course SET duration = 200 WHERE id = 3;

### Связи между таблицами:

    CREATE TABLE [IF NOT EXISTS] имя_таблицы (
        имя_столбца1 тип_данных(размер) доп._информация,
        имя_столбца2 тип_данных(размер) доп._информация,
        имя_столбца3 тип_данных(размер) доп._информация,
        FOREIGN KEY (имя_столбца)
        REFERENCES главная_таблица (имя_столбца_главной_таблицы)
        [ON DELETE {CASCADE|RESTRICT}] [ON UPDATE {CASCADE|RESTRICT}]
    );

    CREATE TABLE IF NOT EXISTS course (
        id SERIAL PRIMARY KEY,
        title VARCHAR(255),
        duration smallint,
        price NUMERIC(9,2)
    );

    CREATE TABLE teacher (
        id SERIAL PRIMARY KEY,
        name VARCHAR(255),
        salary NUMERIC(9,2),
        course_id INTEGER,
         constraint course_teacher
        FOREIGN KEY (course_id) REFERENCES course (id)
    );

### JOIN позволяет объединять данные из разных таблиц:

*inner join

В части SELECT перечислили поля, которые нам нужны, FROM — из какой таблицы брать данные, JOIN — какую таблицу
присоединить, ON по какому условию присоединить.

    SELECT имя_таблицы1.имя_столбца, имя_таблицы2.имя_столбца
    FROM имя_таблицы1
    JOIN имя_таблицы2 ON условия объединения;


    SELECT course.title, teacher.name
    FROM course
    JOIN teacher ON course.id = teacher.course_id;

    title  | name
    -------+--------------------
    Python | Алексей Караченцев    
    PHP    | Артур Петроян
    Ruby   | Леонид Попов
    Java   | Елена Петрова

    Возьмет все записи из course и для каждой найдет соответствующую запись из teacher.

### JOIN можно заменить на WHERE:

Но JOIN позволяет облегчить читаемость, т.к. в сложных запросах WHERE может быть сильно перегружен условиями.

    SELECT course.title, teacher.name
    FROM course, teacher
    WHERE course.id = teacher.course_id;

### LEFT и RIGHT JOIN

*outer join

1. LEFT JOIN: выборка будет содержать все строки из первой или левой таблицы и только те строки из другой таблицы, где
   объединяемые поля равны (выполняется условие соединения)
2. RIGHT JOIN: выборка будет содержать все строки из второй или правой таблицы и только те строки из другой таблицы, где
   объединяемые поля равны (выполняется условие соединения)


      SELECT course.title, teacher.name
      FROM course
      RIGHT JOIN teacher ON course.id = teacher.course_id;

       title  | name
       -------+--------------------
       Python | Алексей Караченцев    
       PHP    | Артур Петроян 
       Ruby   | Леонид Попов 
       Java   | Елена Петрова 
       null   | Оксана Гусева
       null   | Михаил Протасов


   

