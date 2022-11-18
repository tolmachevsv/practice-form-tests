package guru.qa.tests;

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
        // enter first name
        $("#firstName").setValue("Billy");

        // enter last name
        $("#lastName").setValue("Gymov");

        // enter user's email
        $("input#userEmail").setValue("BillyGymov@ga.org");

        // choose a random radiobutton
        $(by("for", "gender-radio-3")).click();

        // enter user's mobile number
        $("#userNumber").setValue("88005553535");

        // click on field of user's birthday
        $("#dateOfBirthInput").click();

        // choose a random date
        $(".react-datepicker__month-select").selectOption("February");
        $(".react-datepicker__year-select").selectOption("1996");
        $(by("aria-label", "Choose Sunday, January 28th, 1996")).click();

        // enter subject
        $("#subjectsInput").setValue("Math").pressEnter();

        // choose the checkbox №2
        $(by("for", "hobbies-checkbox-2")).click();

        // choose the checkbox №3
        $(by("for", "hobbies-checkbox-3")).click();

        // upload a random file
        $("#uploadPicture").uploadFile(new File("src/test/data/300.png"));

        // verify successful upload (can't find name of downloaded file in devtools)
        // $(".form-file").shouldHave(text("300.png"));

        $("#currentAddress").setValue("300, Gatchino street, Saint-Peterburg");

        // choose a random state
        $("#state").click();
        $("#stateCity-wrapper").$(byText("Haryana")).click();

        // choose a random city
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Karnal")).click();

        // press the button "Submit"
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
        sleep(2000);
    }
}
