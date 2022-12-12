package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;


public class DataHelper {

    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }


    @Value
    public static class VerificationCode {
        String code;
    }

    @SneakyThrows
    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        QueryRunner runner = new QueryRunner();
        var conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "app", "pass"
        );
        String codeSQL = "SELECT code FROM auth_codes;";
        String code = runner.query(conn, codeSQL, new ScalarHandler<>());
        return new VerificationCode(code);
    }


    public static VerificationCode getNotVerificationCode(AuthInfo authInfo) {
        Faker faker = new Faker();
        String code = faker.numerify("######");
        return new VerificationCode(code);
    }


}

