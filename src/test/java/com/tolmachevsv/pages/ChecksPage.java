package com.tolmachevsv.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ChecksPage {

    public SelenideElement tableWithResults = $(".table-responsive");
    private SelenideElement closeButton = $("#closeLargeModal");

    public void successfulTitle() {
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
    }

    public void closeTable() {
        closeButton.click();
    }
}
