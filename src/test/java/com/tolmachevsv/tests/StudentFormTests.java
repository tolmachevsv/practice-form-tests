package com.tolmachevsv.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeAll;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class StudentFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "FIREFOX";
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    void fillFormTest() {
        $("#firstName").setValue("Billy");
        $("#lastName").setValue("Gymov");
        $("input#userEmail").setValue("BillyGymov@ga.org");
        $(by("for", "gender-radio-3")).click();
        $("#userNumber").setValue("88005553535");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("February");
        $(".react-datepicker__year-select").selectOption("1996");
        $(by("aria-label", "Choose Sunday, January 28th, 1996")).click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $(by("for", "hobbies-checkbox-2")).click();
        $(by("for", "hobbies-checkbox-3")).click();
        $("#uploadPicture").uploadFile(new File("src/test/data/300.png"));
        $("#currentAddress").setValue("300, Gatchino street, Saint-Peterburg");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();
        $("#submit").click();

        $(".modal-title").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text("Billy Gymov"),
                text("BillyGymov@ga.org"),
                text("Other"),
                text("8800555353"),
                text("28 January,1996"),
                text("Maths"),
                text("Reading, Music"),
                text("300.png"),
                text("300, Gatchino street, Saint-Peterburg"),
                text("Haryana Karnal")
                );
        $("#closeLargeModal").click();
    }
}
