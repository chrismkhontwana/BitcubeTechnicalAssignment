package org.example.bitcubetechnicalassignment;

import com.microsoft.playwright.*;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class BitcubeTests
{
    // Shared between all tests in this class.
    static Playwright playwright;
    static Browser browser;

    // New instance for each test method.
    BrowserContext context;
    Page page;


    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }
    @BeforeEach
    void createPage() {
        context = browser.newContext();
        page = context.newPage();

        page.navigate("http://qainterview.pythonanywhere.com");
    }
    @AfterAll
    static void tearDown()
    {
        browser.close();
        playwright.close();
    }

    @AfterEach
    void closeContext()
    {
        context.close();
    }

}