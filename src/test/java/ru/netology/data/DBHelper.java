package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

    private final static Connection conn = establishConnection();
    private final static QueryRunner runn = new QueryRunner();

    private DBHelper() {
    }

    @SneakyThrows
    private static Connection establishConnection() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static DataHelper.VerificationCode getVerificationCode(DataHelper.AuthInfo authInfo) {
        String codeSQL ="SELECT code FROM auth_codes WHERE created=(SELECT MAX(created) FROM auth_codes);";
        String code = runn.query(conn, codeSQL, new ScalarHandler<>());
        return new DataHelper.VerificationCode(code);
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
