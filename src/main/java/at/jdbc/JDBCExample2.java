package at.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class JDBCExample2 {
    //@Step("SQL-request")
    private static Connection con;
    public static void firsJDBCRequest2() throws SQLException {
        Locale.setDefault(Locale.ENGLISH);
        String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
        String DB_CONNECTION = "jdbc:oracle:thin@localhost:1521/orcl";
        String DB_USER = "SYS";
        String DB_PASSWORD = "1";

        Connection dbConnection = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Error");
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION,DB_USER,DB_PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("program is exited");

    }
}
