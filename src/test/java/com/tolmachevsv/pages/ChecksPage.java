package com.tolmachevsv.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

public class ChecksPage {

    public SelenideElement tableWithResults = $(".table-responsive");
    private SelenideElement closeButton = $("#closeLargeModal");

    public void successfulTitle() {
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
    }

    public void closeTable() {
        closeButton.click();
    }

    public void checkValidData(String firstNameValue, String lastNameValue,
                               String emailValue, String phoneValue, String address) {
        step("Поля формы отображаются согласно введенным значениям", () -> {
            step("Появляется текст об успешной отправке формы", this::successfulTitle);
            step("Поля формы заполнены согласно введенным значениям", () -> {
                System.out.println(firstNameValue);
                System.out.println(lastNameValue);
                tableWithResults.shouldHave(
                        text(firstNameValue + " " + lastNameValue),
                        text(emailValue),
                        text("Other"),
                        text(String.valueOf(phoneValue)),
                        text("03 May,1996"),
                        text("Maths"),
                        text("Reading, Music"),
                        text("300.png"),
                        text(address),
                        text("NCR Delhi")
                );
            });
            step("Закрыть таблицу", this::closeTable);
        });
    }
}

