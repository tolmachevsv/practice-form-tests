package com.tolmachevsv.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.tolmachevsv.config.CredentialsConfig;
import com.tolmachevsv.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        CredentialsConfig credentials =
                ConfigFactory.create(CredentialsConfig.class);

        String login = credentials.login();
        String password = credentials.password();

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        String url = System.getProperty("url");
        String browser = System.getProperty("browser", "chrome");
        String browserSize = System.getProperty("browserSize", "3840x2160");

        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        Configuration.browserSize = browserSize;
        Configuration.browser = browser;
//        Configuration.remote = format("https://%s:%s@%s", login, password, url);

        Configuration.remote = System.getProperty("url", "http://localhost:8080");
        System.out.println(Configuration.baseUrl);
        System.out.println(browserSize);
        System.out.println(browser);
        System.out.println(Configuration.remote);
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
