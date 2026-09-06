This is a Java ,Playwright, TestNG Automation Framework
The UI automation testing framework built using Java, Playwright, TestNG, Maven, and Apache POI libraries

**The framework supports:**

Playwright browser and API automation
TestNG test execution
Page Object Model (POM)
Excel-based test data
Data-driven testing
Custom HTML test reports
Screenshots for failed tests
Test case names from Excel in the HTML report 

**Technology**	
Java 17	- Programming language
Playwright - Web UI And API automation
TestNG - Test execution and assertions
Maven	- Dependency management
Apache POI -	Reading Excel test data
IntelliJ IDEA -	Development environment

📁** Project Structure**
BitcubeTechnicalAssignment
│
├── pom.xml
│
├── test-output
│       └── TestReport.html
│
├── screenshots
│   └── failed-test.png
└── src 
      │ 
      ├── main
      │   └── resources
      │       └── TestData
      │           └──TestData.xlsx
      │           
      │
      └── test
          └── java
             │
             ├── Base
             │   ├── BaseTest.java
             │   └── TaskBase.java
             │
             ├── Pages
             │   └── FactorialPage.java
             │
             ├── Test
             │   ├── Task_4_functional_tests.java
             │   ├── Task_4_UI_tests.java
             │   └── Task_5_tests.java
             │
             └── Utility
                 ├── ExcelReader.java
                 └── SimpleTestListener.java


🧪 **Running Tests**
Run from IntelliJ IDEA

**To run the complete test class:**

1. Open the test class.
2. Right-click the class.
3. Select Run.

To run an individual test:

1. Open the test method.
2. Right-click the method.
3. Select Run.
   
To run an all test:

1. Open testng.xml
2. Right-click the method.
3. Select Run

📊 **Excel Test Data**

Test data is maintained in:
resources/TestData/TestData.xlsx 

📑** HTML Test Report**

After execution, the custom TestNG listener generates:
test-output/TestReport.html
                 
