package ru.netology.data;

import com.codeborne.selenide.Condition;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class Checks {

    public static void personalAccount (){
        $("[data-test-id='dashboard']").shouldBe(Condition.visible, Duration.ofSeconds(5));
    }

    public static void invalidCode (){
        $("[data-test-id='error-notification']").shouldBe(Condition.visible, Duration.ofSeconds(5));
    }

}
