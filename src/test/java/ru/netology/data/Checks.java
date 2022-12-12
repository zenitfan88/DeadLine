package ru.netology.data;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Checks {

    public static void personalAccount (){
        $("[data-test-id='dashboard']")
                .shouldHave(Condition.text("Личный кабинет"), Duration.ofSeconds(5))
                .shouldBe(visible);
    }

    public static void invalidCode (){
        $("[data-test-id='error-notification']")
                .shouldHave(Condition.text("Неверно указан код! Попробуйте ещё раз."), Duration.ofSeconds(5))
                .shouldBe(visible);
    }

    public static void blockedProfile (){
        $("[data-test-id='error-notification']")
                .shouldHave(Condition.text("Профиль заблокирован после трех неудачных попыток ввести код из СМС"), Duration.ofSeconds(5))
                .shouldBe(visible);

    }

}
