package Test;

import Base.BaseTest;
import Pages.FactorialPage;
import Utility.SimpleTestListener;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Request;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicReference;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Listeners(SimpleTestListener.class)
public class Task_5_tests extends BaseTest
{
    private FactorialPage factorialPage;
    @BeforeMethod
    public void setupTest()
    {
        factorialPage = new FactorialPage(page);
    }

    @Test
    void validateStylingForEmptyInput()
    {
        page.navigate("http://qainterview.pythonanywhere.com");
        String expectedStyle = "border: 2px solid red";
        factorialPage.clickCalcuteButton();
        String style = page.locator("#number").getAttribute("style");

        System.out.println("Style: " + style);
        assertTrue(style.contains(expectedStyle), "Expected red border, but actual style was: " + style);

    }

    @Test
    void calculateFactorialOf12()
    {
        page.navigate("http://qainterview.pythonanywhere.com");
        String expectedNumber = "479001600";
        String factorOfNumber = "12";

        factorialPage.fillInTextbox(factorOfNumber);
        factorialPage.clickCalcuteButton();

        Locator result = page.locator("#resultDiv");

        assertThat(result).containsText("The factorial of ");

        String text = result.textContent().trim();
        String number = text.substring(text.lastIndexOf(":") + 1).trim();

        assertEquals(expectedNumber, number);
    }

    @Test
    void validateFactorialApiRequest()
    {
        page.navigate("http://qainterview.pythonanywhere.com");
        String factorOfNumber = "5";
        String expectedMethod = "POST";
        String factorialUri = "/factorial";
        AtomicReference<Request> apiRequest = new AtomicReference<>();

        page.onRequest(request -> {

            if (request.url().contains(factorialUri))
            {
                apiRequest.set(request);
            }
        });

        factorialPage.fillInTextbox(factorOfNumber);
        factorialPage.clickCalcuteButton();

        page.waitForTimeout(1000);

        assertNotNull(apiRequest.get(), "Factorial API request was not made");

        Request request = apiRequest.get();
        String methodUsed = request.method();
        assertEquals(expectedMethod, methodUsed);

        assertTrue(request.postData().contains("number="+factorOfNumber), "API request body does not contain number="+factorOfNumber);

        assertEquals("application/x-www-form-urlencoded; charset=UTF-8", request.headerValue("content-type"));
        assertEquals("*/*", request.headerValue("Accept"));
        assertEquals("keep-alive", request.headerValue("Connection"));
        assertEquals("qainterview.pythonanywhere.com", request.headerValue("Host"));
        assertEquals("https://qainterview.pythonanywhere.com", request.headerValue("Origin"));
        assertEquals("XMLHttpRequest", request.headerValue("X-Requested-With"));
        assertEquals("8", request.headerValue("Content-Length"));
    }
}
