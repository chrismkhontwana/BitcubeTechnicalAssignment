package Test;

import Base.BaseTest;
import Utility.ExcelReader;
import Utility.SimpleTestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import Pages.FactorialPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Listeners(SimpleTestListener.class)
public class Task_4_functional_tests extends BaseTest
{
    // Put Excel name into TestNG result


    private String testCaseName;
    private  FactorialPage factorialPage;
    @BeforeMethod
    public void setupTest()
    {
        factorialPage = new FactorialPage(page);
    }

    @Test(dataProvider = "excelData")
    public void functionalTest(String tcID,String testType,String testScenario,String inputData,String expectedResult)
    {
        ITestResult result = Reporter.getCurrentTestResult();
        this.testCaseName = tcID+"_"+testScenario;
        result.setAttribute("testCaseName",tcID+"_"+testScenario);


        System.out.println("Running Test Case: " + testCaseName);

        page.navigate("http://qainterview.pythonanywhere.com");

        factorialPage.fillInTextbox(inputData);
        factorialPage.clickCalcuteButton();
        if(testScenario.equalsIgnoreCase("Calculate factorial of 2 and click calculate twice")) {
            factorialPage.clickCalcuteButton();
        }
        if(testType.equalsIgnoreCase("Positive"))
        {
            String factorialResults = factorialPage.getResults();
            assertEquals(expectedResult, factorialResults);
        }
        else
        {
            String factorialResults = factorialPage.getErrorResults();
            assertEquals(expectedResult, factorialResults);
        }

    }

}
