package at.jdbc;

import at.common.ConfigJDBC3;
import com.sun.rowset.JdbcRowSetImpl;
import io.qameta.allure.Step;

import javax.sql.rowset.JdbcRowSet;
import java.sql.*;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class JDBCExample3 {

    @Step("SQL-request")
    public static void firsJDBCRequest() throws SQLException {
        baseURI = ConfigJDBC3.getJDBCHost();
        port = ConfigJDBC3.getJDBCPort();
        basePath = ConfigJDBC3.getJDBCPath();

        //Настройки подключения к базе данных и создание подключения
        //Connection con = DriverManager.getConnection("jdbc:oracle:thin@localhost:1521/orcl", "SYS", "1");
        //Connection con = DriverManager.getConnection("jdbc:oracle:thin@localhost:1521/orcl", "SYS as sysdba", "1");
        Connection con = DriverManager.getConnection(baseURI+port+basePath, ConfigJDBC3.getJDBCDefaultUsername(), ConfigJDBC3.getJDBCDefaultPassword());

        //Проверяет что соединение успешно
        assertTrue(con.isValid(1), "Connection success");
        //Проверяет что соединение не закрыто
        assertFalse(con.isClosed(), "Connection is not closed");

        //Создать выражение
        Statement stmt = con.createStatement();

        //Execute sql Statement
        String s = "select * from printer";
        stmt.executeQuery(s);

        //Получает ответ из базы данных Oracle в переменную resultSet
        ResultSet resultSet = stmt.getResultSet();

        //Выводит номер столбца по названию столбца в базе данных Oracle
        System.out.println(resultSet.findColumn("code"));
        System.out.println(resultSet.findColumn("model"));
        System.out.println(resultSet.findColumn("color"));
        System.out.println(resultSet.findColumn("type"));
        System.out.println(resultSet.findColumn("price"));



        /**
         * Обработаем результат
         * Проверка результатов из таблицы
         * способом ResultSet
         */

        //устанавливает курсор в первую строку
        resultSet.next();
        //получает значение из ячейки на пересечении столбца code и строки выделенной методом next()
        int code = resultSet.getInt("code");
        assertEquals(1, code);
        System.out.println(code);

        int model = resultSet.getInt("model");
        assertEquals(1276, model);
        System.out.println(model);

        String color = resultSet.getString("color");
        assertEquals("n", color);
        System.out.println(color);

        String type = resultSet.getString("type");
        assertEquals("Laser", type);
        System.out.println(type);

        int price = resultSet.getInt("price");
        assertEquals(400, price);
        System.out.println(price);



        /**
         * Обработаем результат
         * Проверка результатов из таблицы
         * способом RowSet
         */

        JdbcRowSet jdbcRs = new JdbcRowSetImpl(con);
        jdbcRs.setCommand("select * from product");
        jdbcRs.execute();
        jdbcRs.next();

        String maker = jdbcRs.getString("maker");
        System.out.println(maker);

        model = jdbcRs.getInt("model");
        System.out.println(model);

        type = jdbcRs.getString("type");
        System.out.println(type);


        //Закрыть подключение
        con.close();
        System.out.println("program is exited");

    }

}
