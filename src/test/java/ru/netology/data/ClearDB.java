package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.DriverManager;

public class ClearDB {

    private final static Connection conn = establishConnection();
    private final static QueryRunner runn = new QueryRunner();

    private ClearDB() {
    }

    @SneakyThrows
    private static Connection establishConnection() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }
    @SneakyThrows
    public static void deleteDataBase() {
        runn.execute(conn, "TRUNCATE auth_codes");
        runn.execute(conn, "TRUNCATE cards;");
        runn.execute(conn, "TRUNCATE card_transactions;");
    }

    @SneakyThrows
    public static void deleteAuthCode() {
        runn.execute(conn, "TRUNCATE auth_codes;");
    }
}
