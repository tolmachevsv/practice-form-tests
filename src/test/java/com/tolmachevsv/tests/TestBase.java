package com.tolmachevsv.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = "FIREFOX";
        Configuration.browserSize = "3060x2080";
        Configuration.baseUrl = "https://demoqa.com";
    }
}
