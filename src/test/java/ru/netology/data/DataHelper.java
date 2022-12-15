package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.*;

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


    public static VerificationCode getNotVerificationCode(AuthInfo authInfo) {
        Faker faker = new Faker();
        String code = faker.numerify("######");
        return new VerificationCode(code);
    }


}

