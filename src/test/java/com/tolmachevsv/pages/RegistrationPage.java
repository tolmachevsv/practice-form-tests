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

    private SelenideElement
            formTitle = $(".practice-form-wrapper"),
            firstNameSelector = $("#firstName"),
            lastNameSelector = $("#lastName"),
            emailSelector = $("input#userEmail"),
            picture = $("#uploadPicture"),
            closeButton = $("#closeLargeModal");
    public SelenideElement buttonSubmit = $("#submit");
    public SelenideElement tableWithResults = $(".table-responsive");
    public CalendarComponent calendar = new CalendarComponent();
    public ButtonComponent button = new ButtonComponent();

    public void openPage() {
        open("/automation-practice-form");
        // есть ли смысл здесь константу FORM_TITLE создавать, как было в видео, без неё по мне так лучше код читается
        // или здесь необязательна читабельность кода (главное, чтоб код в StudentFormWithPageObjectTests читался) ?
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

    // есть ощущение, что напрашивается проверка случаев, когда ответ != (1, 2, 3)
    // нужно ли какой то свой эксепшн писать? или вообще пропустить
    // вопрос такой еще: как определить, когда нужно в отдельный класс вынести компонент, а когда не нужно?
    // здесь вроде можно создать компонент Button, потом вызывать его: RegistrationPage.button.chooseGender()...

    public void typePhone(Long phoneNumber) {
        $("#userNumber").setValue(String.valueOf(phoneNumber));
    }

    public void chooseSubject(String name) {
        $("#subjectsInput").setValue(name).pressEnter();
    }

    // chooseHobbies убрал в компоненты (нужно ли?)

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

    public void successfulTitle() {
        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
    }

    public void closeTable() {
        closeButton.click();
    }
}
