package Utility;

import Base.TaskBase;
import com.microsoft.playwright.Page;
import org.testng.*;

import java.io.File;
import java.io.FileWriter;

public class SimpleTestListener implements ITestListener {

    private static String reportFile =
            "test-output/TestReport.html";

    @Override
    public void onStart(ITestContext context) {

        new File("test-output").mkdirs();
        new File("screenshots").mkdirs();

        try {
            FileWriter writer =
                    new FileWriter(reportFile);

            writer.write(
                    "<html>\n" +
                    "<head>\n" +
                    "<title>Test Report</title>\n" +
                    "<style>\n" +
                    "body { font-family: Arial; margin: 30px; }\n" +
                    "table { border-collapse: collapse; width: 100%; }\n" +
                    "th, td { border: 1px solid #ddd; padding: 10px; }\n" +
                    "th { background: #eee; }\n" +
                    ".pass { color: green; font-weight: bold; }\n" +
                    ".fail { color: red; font-weight: bold; }\n" +
                    "img { width: 400px; }\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1>Playwright Test Report</h1>\n" +
                    "<table>\n" +
                    "<tr>\n" +
                    "<th>Test Case</th>\n" +
                    "<th>Status</th>\n" +
                    "<th>Screenshot</th>\n" +
                    "</tr>\n"
            );

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        String testName = getTestCaseName(result);

        writeResult(
                testName,
                "PASS",
                ""
        );
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String testName = getTestCaseName(result);

        String screenshot =
                "screenshots/" +
                cleanFileName(testName) +
                ".png";

        try {

            Object testObject =
                    result.getInstance();

            Page page =
                    ((TaskBase) testObject).getPage();

            if (page != null) {

                page.screenshot(
                        new Page.ScreenshotOptions()
                                .setPath(
                                        new File(
                                                screenshot
                                        ).toPath()
                                )
                                .setFullPage(true)
                );

                writeResult(
                        testName,
                        "FAIL",
                        "<img src='../" +
                        screenshot +
                        "'>"
                );

            }
            else
            {

                writeResult(
                        testName,
                        "FAIL",
                        "Page is null"
                );
            }

        } catch (Exception e) {

            writeResult(
                    testName,
                    "FAIL",
                    "Screenshot failed: " +
                    e.getMessage()
            );
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        String testName =
                getTestCaseName(result);

        writeResult(
                testName,
                "SKIPPED",
                ""
        );
    }


    /*
     * =====================================================
     * GET TEST CASE NAME
     * =====================================================
     *
     * First get the name from Excel.
     *
     * If Excel name is not available,
     * use the Java method name.
     */
    private String getTestCaseName(
            ITestResult result) {

        Object excelTestName =
                result.getAttribute("testCaseName");

        if (excelTestName != null) {

            return excelTestName.toString();
        }

        return result.getMethod()
                .getMethodName();
    }


    private void writeResult(
            String testName,
            String status,
            String screenshot) {

        try {

            FileWriter writer =
                    new FileWriter(
                            reportFile,
                            true
                    );

            String cssClass =
                    status.equals("PASS")
                            ? "pass"
                            : "fail";

            writer.write(
                    "<tr>" +
                    "<td>" +
                    testName +
                    "</td>" +

                    "<td class='" +
                    cssClass +
                    "'>" +
                    status +
                    "</td>" +

                    "<td>" +
                    screenshot +
                    "</td>" +

                    "</tr>\n"
            );

            writer.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    private String cleanFileName(
            String name) {

        return name.replaceAll(
                "[\\\\/:*?\"<>|]",
                "_"
        );
    }


    @Override
    public void onFinish(
            ITestContext context) {

        try {

            FileWriter writer =
                    new FileWriter(
                            reportFile,
                            true
                    );

            writer.write(
                    "</table>\n" +
                    "</body>\n" +
                    "</html>\n"
            );

            writer.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}