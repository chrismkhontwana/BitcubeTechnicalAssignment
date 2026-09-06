package Base;
import Pages.FactorialPage;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;
import org.testng.annotations.*;
import Utility.ExcelReader;

public class BaseTest
{
    protected  Playwright playwright;
    protected Browser browser;

    protected BrowserContext context;
    protected Page page;


    @BeforeClass
     public void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }


    @BeforeMethod
    public void setupPage() {
        page = browser.newPage();
    }

    @AfterClass
    public void tearDown()
    {
        if(browser != null)
        {
            browser.close();
        }
        if(playwright != null)
        {
            playwright.close();
        }
    }
    @AfterMethod
    public void closePage()
    {

        if(page != null)
        {
            page.close();
        }
    }

    @DataProvider(name = "excelData")
    public Object[][] getExcelData() throws Exception {

        return ExcelReader.getData(
                "src/main/resources/TestData/testData.xlsx",
                "data"
        );
    }
}