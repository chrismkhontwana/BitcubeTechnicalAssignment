package Test;

import Base.BaseTest;
import Pages.FactorialPage;
import Utility.SimpleTestListener;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.testng.annotations.Listeners;

@Listeners(SimpleTestListener.class)
public class Task_4_UI_tests extends BaseTest
{
    private FactorialPage factorialPage;
    @BeforeMethod
    public void setupTest()
    {
        factorialPage = new FactorialPage(page);
    }
    @Test
    public void verifyPageTitle()
    {
        page.navigate("http://qainterview.pythonanywhere.com");
        String pageTitle = page.title();
        System.out.println("Page Title is : "+pageTitle);
        assertEquals("Factorial", pageTitle);
    }

    @Test
    public void ValidatePrivacyLinkText()
    {
        String expectedBodyText = "This is the privacy document. We are not yet ready with it. Stay tuned!";
        page.navigate("http://qainterview.pythonanywhere.com");
        factorialPage.clickPrivacyLinkText();
        String privacyText = factorialPage.getBodyText();
        System.out.println("Privacy Text : "+privacyText);
        assertEquals(expectedBodyText,privacyText);

    }

    @Test
    public void ValidateTermsAndConditionsLinkText()
    {
        String expectedBodyText = "This is the terms and conditions document. We are not yet ready with it. Stay tuned!";
        page.navigate("http://qainterview.pythonanywhere.com");
        factorialPage.clickTermsAndConditionsLinkText();
        String tAndCText = factorialPage.getBodyText();
        System.out.println("T & C Text : "+tAndCText);
        assertEquals(expectedBodyText,tAndCText);

    }
    @Test
    public void ValidateAboutLinkText()
    {
        String expectedText = "About the QA interview application";
        page.navigate("http://qainterview.pythonanywhere.com");
        factorialPage.clickAboutLinkText();
        String aboutText = factorialPage.getAboutHeaderText();
        System.out.println("About Text : "+aboutText);
        assertEquals(expectedText,aboutText);

    }
}
