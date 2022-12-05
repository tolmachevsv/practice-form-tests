package com.tolmachevsv.pages.components;

import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.$;

public class ButtonComponent {

    public void chooseGender(String answerChoice) {
        $(by("for", "gender-radio-" + answerChoice)).click();
    }

    public void chooseHobbies(String answerChoice) {
        $(by("for", "hobbies-checkbox-" +
                answerChoice)).click();
    }
}
