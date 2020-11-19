package at.jdbc;

import io.qameta.allure.Step;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample1 {
    @Step("SQL-request")
    public static void firsJDBCRequest() throws SQLException {
        //Настройки подключения к базе данных и создание подключения
        //Connection con = DriverManager.getConnection("jdbc:oracle:thin@localhost:1521/orcl", "SYS", "1");
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl", "SYS as sysdba", "1");

        //Создать выражение
        Statement stmt = con.createStatement();

        //Execute sql Statement
        String s = "select * from printer";
        stmt.executeQuery(s);

        //Закрыть подключение
        con.close();
        System.out.println("program is exited");

    }
}
