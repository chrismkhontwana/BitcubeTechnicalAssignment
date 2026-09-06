package Utility;

//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader
{
    public static Object[][] getData(
            String filePath,
            String sheetName) throws IOException {

        FileInputStream fis = new FileInputStream(filePath);

        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        List<Object[]> data = new ArrayList<>();

        // Skip header row
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {

            Row row = sheet.getRow(i);

            if (row == null) {
                continue;
            }
            String tcID = getCellValue(row.getCell(0));
            String testType = getCellValue(row.getCell(1));
            String testScenario = getCellValue(row.getCell(2));
            String inputData = getCellValue(row.getCell(3));
            String expectedResult = getCellValue(row.getCell(4));

            data.add(new Object[] {
                    tcID,
                    testType,
                    testScenario,
                    inputData,
                    expectedResult
            });
        }

        workbook.close();
        fis.close();

        return data.toArray(new Object[0][]);
    }

    private static String getCellValue(Cell cell) {

        if (cell == null) {
            return "";
        }

        DataFormatter formatter = new DataFormatter();

        return formatter.formatCellValue(cell);
    }
}
