package ru.netology.test;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DBHelper.deleteAuthCode;
import static ru.netology.data.DBHelper.deleteDataBase;


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
    void testVerificationCode() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DBHelper.getVerificationCode(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.personalAccount();
    }

    @Test
    void testVerificationCodeNot() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getNotVerificationCode(authInfo);
        var dashboardPage = verificationPage.notValidVerify(verificationCode);
       verificationPage.invalidCode();
    }

    @Test
    void testTriceNotVerificationCode() {
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getNotVerificationCode(authInfo);
        var dashboardPage = verificationPage.notValidVerify(verificationCode);
        var dashboardPage1 = verificationPage.notValidVerify(verificationCode);
        var dashboardPage2 = verificationPage.notValidVerify(verificationCode);
        verificationPage.blockedProfile();
    }

}


