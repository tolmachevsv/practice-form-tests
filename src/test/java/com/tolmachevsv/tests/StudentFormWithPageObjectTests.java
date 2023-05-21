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
        registrationPage.openPage();
        registrationPage.typeFirstName(firstNameValue);
        System.out.println(firstNameValue);
        registrationPage.typeLastName(lastNameValue);
        System.out.println(lastNameValue);
        registrationPage.typeEmail(emailValue);
        registrationPage.button.chooseGender("3");
        registrationPage.typePhone(phoneValue);
        registrationPage.calendar.setDate("03", "May", "1996");
        registrationPage.chooseSubject("Math");
        registrationPage.button.chooseHobbies("2");
        registrationPage.button.chooseHobbies("3");
        registrationPage.uploadFile("img/300.png");
        registrationPage.typeAddress(address);
        registrationPage.selectState("NCR");
        registrationPage.selectCity("Delhi");
        registrationPage.clickSubmitButton();
        checksPage.checkValidData(firstNameValue, lastNameValue, emailValue,
                String.valueOf(phoneValue), address);

    }
}
