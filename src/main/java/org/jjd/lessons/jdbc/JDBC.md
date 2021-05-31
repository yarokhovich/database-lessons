## JDBC (Java DataBase Connectivity)

- платформенно независимый промышленный стандарт взаимодействия Java-приложений с различными СУБД, реализованный в виде
  пакета java.sql, входящего в состав Java SE.

Для соединения с сервером используется JDBC Driver Manager. Для отправки запросов и получения результатов используется
JDBC API.

Для установления соединения нужен JDBC драйвер для конкретной БД.

1. Загрузка драйвера: `Class.forName("org.postgresql.Driver");`
2.Подключение: `Connection con = DriverManager.getConnection("org.jjd.lessons.jdbc:postgresql://ip:port/имя_бд", "имя_пользователя", "пароль");`

Класс Connection предоставляет множество методов для работы с БД, такие как получения метаданных (информация о таблицах,
хранимых процедурах и т. п.), управления транзакциями, информации о текущей схеме, подключении и так далее.

3. Создание Statement (для выполнения запроса к БД): `Statement statement = con.createStatement();`

Методы Statement для выполнения запроса `execute()`, `executeQuery()`, `executeUpdate()` <br>

3.1. executeUpdate - следует использовать для выполнения INSERT, UPDATE или DELETE (Data Manipulation Language) запросов и
   для CREATE TABLE, DROP TABLE (DDL - Data Definition Language) запросов. Результатом выполнения метода executeUpdate
   является целочисленное значение, определяющее, сколько строк было модифицировано. Для выражений DML, которые не
   оперируют со строками, возвращаемое методом executeUpdate значение всегда равно нулю.

3.2. executeQuery - используется в запросах, результатом которых является один единственный набор значений, таких как
   запросов типа SELECT. Результат работы метода - ResultSet.

3.3. execute возвращает true, если объект ResultSet может быть получен. В противном случае он false. Он используется и для
   выполнения DDL SQL.
   
4. Получение объекта ResultSet: `ResultSet resultSet = statement.executeQuery(sqlString);`
   В объекте ResultSet итератор устаналивается на позиции перед первой строкой. И чтобы переместиться к первой строке (и ко всем последующим) 
   необходимо вызвать метод next(). Пока в наборе ResultSet есть доступные строки, метод next будет возвращать true.
   
         while(resultSet.next()){ // получение данных их ResultSet
            методы resultSet для получения данных:
            getBoolean() возвращает значение boolean
            getDate() возвращает значение Date
            getDouble() возвращает значение double
            getInt() возвращает значение int
            getFloat() возвращает значение float
            getLong() возвращает значение long
            getString() возвращает значение String
         }

5. Объект PreparedStatement: 
      
         con.prepareStatement("SELECT * FROM course WHERE title=?"); 
         prepared.setString(1, "Java");
   
Помимо Statement существует так же PreparedStatement. Его особенность заключается в первую очередь в том, что он 
обрабатывается, оптимизируется и кэшируется СУБД один раз при первом исполнении, после чего каждый последующий запрос 
выполняется гораздо быстрее. Помимо этого он защищает от SQL инъекций, т.к. аргументы экранируются.



### Connection, Statement, PreparedStatement, ResultSet должны быть закрыты после окончания работы с ними


### Транзакции:

      // Выключяем автокоммит транзакций
      con.setAutoCommit(false);
      // Создаем statement
      try (Statement stmnt = con.createStatement()) {
         // Выполняем запрос к БД
         int rows = stmnt.executeUpdate("INSERT INTO course(title, duration, price) VALUES ('NodeJS', 102, 34000)");
         System.out.println(rows);
         // Если все хорошо завершаем тразакцию
         con.commit();
      } catch (SQLException e) {
         // В случае ошибки откатываем изменения
         con.rollback();
      }


