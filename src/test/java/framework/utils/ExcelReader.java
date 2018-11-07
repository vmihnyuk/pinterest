package framework.utils;

import framework.CommonFunctions;
import jxl.Sheet;
import jxl.Workbook;

import java.io.FileInputStream;

public class ExcelReader {

    public static String[][] getExcelData(String fileName, String sheetName) {
        String[][] arrayExcelData = null;
        String path = new StringBuilder().append(CommonFunctions.getCanonicalPathToResource()).append(fileName).toString();
        try {
            FileInputStream fstream = new FileInputStream(path);
            Workbook workbook = Workbook.getWorkbook(fstream);
            Sheet sheet = workbook.getSheet(sheetName);

            int numberOfColomns = sheet.getColumns();
            int numberOfRows = sheet.getRows();

            arrayExcelData = new String[numberOfRows-1][numberOfColomns];

            for (int i= 1 ; i < numberOfRows; i++) {
                for (int j=0; j < numberOfColomns; j++) {
                    arrayExcelData[i-1][j] = sheet.getCell(j, i).getContents();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayExcelData;
    }
}
