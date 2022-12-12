package ru.netology.test;


import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.Checks.*;
import static ru.netology.data.ClearDB.deleteAuthCode;
import static ru.netology.data.ClearDB.deleteDataBase;


public class DeadlineTest {
    @BeforeEach
    void setUpp() {
        open("http://localhost:9999");
    }

    @AfterEach
    void closeTest() {
        deleteAuthCode();
    }

    @AfterAll
    static void clearAll() {
        deleteDataBase();
    }

    @Test
    @SneakyThrows
    void testVerificationCode() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        personalAccount();
    }

    @Test
    @SneakyThrows
    void testVerificationCodeNot() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getNotVerificationCode(authInfo);
        var dashboardPage = verificationPage.notValidVerify(verificationCode);
       invalidCode();
    }

    @Test
    @SneakyThrows
    void testTriceNotVerificationCode() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getNotVerificationCode(authInfo);
        var dashboardPage = verificationPage.notValidVerify(verificationCode);
        var dashboardPage1 = verificationPage.notValidVerify(verificationCode);
        var dashboardPage2 = verificationPage.notValidVerify(verificationCode);
        blockedProfile();
    }

}


