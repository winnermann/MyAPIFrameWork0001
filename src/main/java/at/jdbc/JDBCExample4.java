package at.jdbc;

import at.common.ConfigJDBC3;
import com.sun.rowset.JdbcRowSetImpl;
import io.qameta.allure.Step;

import javax.sql.rowset.JdbcRowSet;
import java.sql.*;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class JDBCExample4 {

    private static Connection getNewConnection() throws SQLException {
        baseURI = ConfigJDBC3.getJDBCHost();
        port = ConfigJDBC3.getJDBCPort();
        basePath = ConfigJDBC3.getJDBCPath();

        //Настройки подключения к базе данных и создание подключения
        //Connection con = DriverManager.getConnection("jdbc:oracle:thin@localhost:1521/orcl", "SYS", "1");
        //Connection con = DriverManager.getConnection("jdbc:oracle:thin@localhost:1521/orcl", "SYS as sysdba", "1");
        return DriverManager.getConnection(baseURI+port+basePath, ConfigJDBC3.getJDBCDefaultUsername(), ConfigJDBC3.getJDBCDefaultPassword());
    }

    @Step("Проверка подключения к базе данных")
    public static void shouldGetJdbcConnection() throws SQLException{
        try(Connection con = getNewConnection()) {
            //Проверяет что соединение успешно
            assertTrue(con.isValid(1), "Connection success");
            //Проверяет что соединение не закрыто
            assertFalse(con.isClosed(), "Connection is not closed");
        }
    }

    private static int executeUpdate(String query) throws SQLException {
        Statement statement = getNewConnection().createStatement();

// Для Insert, Update, Delete

        int result = statement.executeUpdate(query);
        return result;
    }

    @Step("Создать пустую таблицу 'printer2'")
    public static void createDataBasePrinter() throws SQLException{
        String printerTableCreateQuery = "CREATE TABLE printer2" +
                "(code INT, model VARCHAR(50), color CHAR(1), type VARCHAR(10), price INT)";
        executeUpdate(printerTableCreateQuery);

    }

    @Step("Заполнить данными пустые ячейки в таблице 'printer2'")
    public static void insertDataBasePrinterCells() throws SQLException {
        String printerTableLine1Query = "INSERT INTO printer2(code, model, color, type, price) " +
                "VALUES (1, 1276, 'n', 'Laser', 400)";
        String printerTableLine2Query = "INSERT INTO printer2(code, model, color, type, price) " +
                "VALUES (2, 1433, 'y', 'Jet', 270)";
        String printerTableLine3Query = "INSERT INTO printer2(code, model, color, type, price) " +
                "VALUES (3, 1434, 'y', 'Jet', 290)";

        String printerTableLine4Query = "INSERT INTO printer2(code, model, color, type, price) " +
                "VALUES (4, 1401, 'n', 'Matrix', 150)";

        String printerTableLine5Query = "INSERT INTO printer2(code, model, color, type, price) " +
                "VALUES (5, 1408, 'n', 'Matrix', 270)";

        String printerTableLine6Query = "INSERT INTO printer2(code, model, color, type, price) " +
                "VALUES (6, 1288, 'n', 'Laser', 400)";
        executeUpdate(printerTableLine1Query);
        executeUpdate(printerTableLine2Query);
        executeUpdate(printerTableLine3Query);
        executeUpdate(printerTableLine4Query);
        executeUpdate(printerTableLine5Query);
        executeUpdate(printerTableLine6Query);

    }



    @Step("Операции с базой данных")
    public static void dataBaseOperations() throws SQLException {
        //Создать выражение
        Statement stmt = getNewConnection().createStatement();

        //Execute sql Statement
        String s = "select * from printer2";
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

        JdbcRowSet jdbcRs = new JdbcRowSetImpl(getNewConnection());
        jdbcRs.setCommand("select * from product");
        jdbcRs.execute();
        jdbcRs.next();

        String maker = jdbcRs.getString("maker");
        System.out.println(maker);

        model = jdbcRs.getInt("model");
        System.out.println(model);

        type = jdbcRs.getString("type");
        System.out.println(type);
    }

    @Step("Удалить таблицу 'printer2'")
    public static void dropDataBasePrinter() throws SQLException{
        String printerTableDropQuery = "DROP TABLE printer2";
        executeUpdate(printerTableDropQuery);

    }

    @Step("Закрыть подключение")
    public static void connectionClose() throws SQLException {
        //Закрыть подключение
        getNewConnection().close();
        System.out.println("program is exited");
    }


}
