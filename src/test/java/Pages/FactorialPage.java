package Pages;

import Base.BaseTest;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FactorialPage
{
    protected Page page;
    public FactorialPage(Page page) {
        this.page = page;
    }
    public void clickCalcuteButton()
    {
        Locator calculateButton = page.locator("#getFactorial");
        calculateButton.click();
    }
    public void clickPrivacyLinkText()
    {
        Locator linkText = page.locator("//a[text()='Privacy']");
        linkText.click();
    }
    public void clickTermsAndConditionsLinkText()
    {
        Locator linkText = page.locator("//a[text()='Terms and Conditions']");
        linkText.click();
    }
    public void clickAboutLinkText()
    {
        Locator linkText = page.locator("//a[text()='About']");
        linkText.click();
    }
    public void fillInTextbox(String factorOfNumber)
    {
        Locator numberInput = page.locator("#number");
        numberInput.fill(factorOfNumber);
    }

    public String getResults()
    {
        Locator result = page.locator("#resultDiv");
        assertThat(result).containsText("The factorial of ");
        return result.textContent().trim();
    }

    public String getErrorResults()
    {
        Locator result = page.locator("#resultDiv");
        return result.textContent();
    }

    public String getBodyText()
    {
        return page.locator("//body").innerText();
    }
    public String getAboutHeaderText()
    {
        return page.locator("//h1").textContent();
    }
}
