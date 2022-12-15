package ru.netology.page;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    public void personalAccount() {
        $("[data-test-id='dashboard']")
                .shouldHave(Condition.text("Личный кабинет"), Duration.ofSeconds(5))
                .shouldBe(visible);
    }
}
