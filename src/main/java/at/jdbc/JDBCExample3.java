package at.jdbc;

import at.common.ConfigJDBC3;
import io.qameta.allure.Step;

import java.sql.*;

import static io.restassured.RestAssured.*;

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

        //Закрыть подключение
        con.close();
        System.out.println("program is exited");

    }
}
