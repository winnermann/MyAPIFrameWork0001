package at.tests;

import at.jdbc.JDBCExample4;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class JDBCTest {

    @Test
    @Description(value = "JDBC")
    @Epic("Функциональные тесты")
    @Severity(value = SeverityLevel.NORMAL)

    public void testCasesJDBC() throws SQLException {
        //JDBCExample1.firsJDBCRequest();
        //JDBCExample2.firsJDBCRequest2();
        //JDBCExample3.firsJDBCRequest();



        JDBCExample4.shouldGetJdbcConnection();


        JDBCExample4.createDataBasePrinter();
        JDBCExample4.insertDataBasePrinterCells();
        JDBCExample4.dataBaseOperations();
        JDBCExample4.updateDataBasePrinterCells();
        JDBCExample4.deleteLine6FromDataBasePrinter();
        JDBCExample4.deleteLine5FromDataBasePrinter();
        JDBCExample4.insertLine6ToDataBasePrinter();
        JDBCExample4.insertLine5ToDataBasePrinter();

        JDBCExample4.dropDataBasePrinter();


        JDBCExample4.connectionClose();
    }
}
