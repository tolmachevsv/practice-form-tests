package com.tolmachevsv.pages.components;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class ButtonComponent {

    public void chooseGender(String answerChoice) {
        step("В поле Пол выбрать \"Другое\"", () -> {
            $(by("for", "gender-radio-" + answerChoice)).click();
        });
    }

    public void chooseHobbies(String answerChoice) {
        step("В поле Хобби выбрать " + answerChoice + " вариант ответа", () -> {
            $(by("for", "hobbies-checkbox-" +
                    answerChoice)).click();
        });
    }
}
