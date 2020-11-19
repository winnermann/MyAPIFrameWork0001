package at.tests;

import at.jdbc.JDBCExample1;
import at.jdbc.JDBCExample2;
import at.jdbc.JDBCExample3;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class JDBCTest {

    @Test
    @Description(value = "API")
    @Epic("Функциональные тесты")
    @Severity(value = SeverityLevel.NORMAL)

    public void testCasesJDBC() throws SQLException {
        //JDBCExample1.firsJDBCRequest();
        //JDBCExample2.firsJDBCRequest2();
        JDBCExample3.firsJDBCRequest();
    }
}
