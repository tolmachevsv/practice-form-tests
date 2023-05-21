package com.tolmachevsv.pages;

import com.codeborne.selenide.SelenideElement;
import com.tolmachevsv.pages.components.ButtonComponent;
import com.tolmachevsv.pages.components.CalendarComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

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
        step("Открыть страницу с формой (https://demoqa.com/automation-practice-form)", () -> {
            open("/automation-practice-form");
            formTitle.shouldHave(text("Student Registration Form"));
        });
    }

    public RegistrationPage typeFirstName(String fName) {
        step("Ввести имя \"Billy\"", () -> {
            firstNameSelector.setValue(fName);
        });
        return this;
    }

    public void typeLastName(String lName) {
        step("Ввести фамилию \"Gymov\"", () -> {
            lastNameSelector.setValue(lName);
        });
    }

    public void typeEmail(String email) {
        step("Ввести email \"BillyGymov@ga.org\"", () -> {
            emailSelector.setValue(email);
        });
    }

    public void typePhone(Long phoneNumber) {
        step("Ввести мобильный телефон \"88005553535\"", () -> {
            $("#userNumber").setValue(String.valueOf(phoneNumber));
        });
    }

    public void chooseSubject(String name) {
        step("В поле \"Предмет\" ввести значение \"Math\"", () -> {
            $("#subjectsInput").setValue(name).pressEnter();
        });
    }

    public void uploadFile(String pathname) {
        step("Загрузить изображение", () -> {
            picture.uploadFromClasspath(pathname);
        });
    }

    public void typeAddress(String address) {
        step("Заполнить адрес \"300, Gatchino street, Saint-Peterburg\"", () -> {
            $("#currentAddress").setValue(address);
        });
    }

    public void selectState(String state) {
        step("Выбрать из выпадающего списка штат \"Haryana\"", () -> {
            $("#state").scrollTo().click();
            $("#stateCity-wrapper").$(byText(state)).click();
        });
    }

    public void selectCity(String city) {
        step("Выбрать из выпадающего списка город \"Karnal\"", () -> {
            $("#city").click();
            $("#stateCity-wrapper").$(byText(city)).click();
        });
    }

    public void clickSubmitButton() {
        step("Нажать кнопку \"Отправить\"", () -> {
            buttonSubmit.click();
        });
    }
}
