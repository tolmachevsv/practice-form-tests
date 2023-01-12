package com.tolmachevsv.pages;

import com.codeborne.selenide.SelenideElement;
import com.tolmachevsv.pages.components.ButtonComponent;
import com.tolmachevsv.pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    public CalendarComponent calendar = new CalendarComponent();
    public ButtonComponent button = new ButtonComponent();
    private SelenideElement
            formTitle = $(".practice-form-wrapper"),
            firstNameSelector = $("#firstName"),
            lastNameSelector = $("#lastName"),
            emailSelector = $("input#userEmail"),
            picture = $("#uploadPicture");
    public SelenideElement buttonSubmit = $("#submit");

    public void openPage() {
        open("/automation-practice-form");
        formTitle.shouldHave(text("Student Registration Form"));
    }

    public RegistrationPage typeFirstName(String fName) {
        firstNameSelector.setValue(fName);

        return this;
    }

    public void typeLastName(String lName) {
        lastNameSelector.setValue(lName);
    }

    public void typeEmail(String email) {
        emailSelector.setValue(email);
    }

    public void typePhone(Long phoneNumber) {
        $("#userNumber").setValue(String.valueOf(phoneNumber));
    }

    public void chooseSubject(String name) {
        $("#subjectsInput").setValue(name).pressEnter();
    }

    public void uploadFile(String pathname) {
        picture.uploadFile(new File(pathname));
    }

    public void typeAddress(String address) {
        $("#currentAddress").setValue(address);
    }

    public void selectState(String state) {
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
    }

    public void selectCity(String city) {
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
    }
}
