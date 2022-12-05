package com.tolmachevsv.tests;

import com.github.javafaker.Faker;
import com.tolmachevsv.pages.RegistrationPage;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;

public class StudentFormWithPageObjectTests extends TestBase {
    Faker faker = new Faker();
    String firstNameValue = faker.name().firstName(),
            lastNameValue = faker.name().lastName(),
            emailValue = faker.internet().emailAddress(),
            address = faker.address().fullAddress();
    long phoneValue = faker.number().randomNumber(10, true);
    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    public void fillFormTest() {
        registrationPage.openPage();
        registrationPage.typeFirstName(firstNameValue)
                        .typeLastName(lastNameValue);
        registrationPage.typeEmail(emailValue);
        registrationPage.button.chooseGender("3");
        registrationPage.typePhone(phoneValue);
        registrationPage.calendar.setDate("03", "May", "1996");
        registrationPage.chooseSubject("Math");
        registrationPage.button.chooseHobbies("2");
        registrationPage.button.chooseHobbies("3");
        registrationPage.uploadFile("src/test/data/300.png");
        registrationPage.typeAddress(address);
        registrationPage.selectState("NCR");
        registrationPage.selectCity("Delhi");
        registrationPage.buttonSubmit.click();

        registrationPage.successfulTitle();
        registrationPage.tableWithResults.shouldHave(
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
        registrationPage.closeTable();
    }
}
