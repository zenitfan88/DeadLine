package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;


public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id='code'] input");
    private SelenideElement verifyButton = $("[data-test-id='action-verify']");


    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }

    public DashboardPage notValidVerify(DataHelper.VerificationCode notVerificationCode) {
        codeField.sendKeys(Keys.CONTROL + "A"+ Keys.BACK_SPACE);
        codeField.setValue(notVerificationCode.getCode());
        verifyButton.click();
        return new DashboardPage();
    }
}
