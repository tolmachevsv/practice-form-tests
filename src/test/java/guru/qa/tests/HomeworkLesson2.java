package guru.qa.tests;

import org.junit.jupiter.api.BeforeAll;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomeworkLesson2 {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "2100x1080";
        open("https://demoqa.com/automation-practice-form");
    }

    @Test
    static void fillFormTest() {
        $("#firstName").setValue("Sergey");
    }
}
