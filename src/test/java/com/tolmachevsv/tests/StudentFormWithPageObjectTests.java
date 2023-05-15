package com.tolmachevsv.tests;

import com.github.javafaker.Faker;
import com.tolmachevsv.pages.ChecksPage;
import com.tolmachevsv.pages.RegistrationPage;

import io.qameta.allure.AllureId;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static io.qameta.allure.Allure.step;

public class StudentFormWithPageObjectTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    ChecksPage checksPage = new ChecksPage();
    Faker faker = new Faker();
    String firstNameValue = faker.name().firstName(),
            lastNameValue = faker.name().lastName(),
            emailValue = faker.internet().emailAddress(),
            address = faker.address().fullAddress();
    long phoneValue = faker.number().randomNumber(10, true);

    @Test
    @AllureId("19345")
    @DisplayName("Успешное заполнение формы студента")
    @Tag("critical")
    @Feature("Demo QA")
    @Owner("allure8")
    public void fillFormTest() {
        step("Открыть страницу с формой (https://demoqa.com/automation-practice-form)", () -> {
            registrationPage.openPage();
        });
        step("Ввести имя \"Billy\"", () -> {
            registrationPage.typeFirstName(firstNameValue);
        });
        step("Ввести фамилию \"Gymov\"", () -> {
            registrationPage.typeLastName(lastNameValue);
        });
        step("Ввести email \"BillyGymov@ga.org\"", () -> {
            registrationPage.typeEmail(emailValue);
        });
        step("В поле Пол выбрать \"Другое\"", () -> {
            registrationPage.button.chooseGender("3");
        });
        step("Ввести мобильный телефон \"88005553535\"", () -> {
            registrationPage.typePhone(phoneValue);
        });
        step("Выбрать дату рождения из календаря \"28 января 1996\"", () -> {
            registrationPage.calendar.setDate("03", "May", "1996");
        });
        step("В поле \"Предмет\" ввести значение \"Math\"", () -> {
            registrationPage.chooseSubject("Math");
        });
        step("В поле Хобби выбрать \"Чтение\"", () -> {
            registrationPage.button.chooseHobbies("2");
        });
        step("В поле Хобби выбрать \"Музыка\"", () -> {
            registrationPage.button.chooseHobbies("3");
        });
        step("Загрузить изображение", () -> {
            registrationPage.uploadFile("img/300.png");
        });
        step("Заполнить адрес \"300, Gatchino street, Saint-Peterburg\"", () -> {
            registrationPage.typeAddress(address);
        });
        step("Выбрать из выпадающего списка штат \"Haryana\"", () -> {
            registrationPage.selectState("NCR");
        });
        step("Выбрать из выпадающего списка город \"Karnal\"", () -> {
            registrationPage.selectCity("Delhi");
        });
        step("Нажать кнопку \"Отправить\"", () -> {
            registrationPage.buttonSubmit.click();
            step("Поля формы отображаются согласно введенным значениям", () -> {
                checksPage.successfulTitle();
                checksPage.tableWithResults.shouldHave(
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
                checksPage.closeTable();
            });
        });
    }
}
