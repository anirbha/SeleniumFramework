package ui.Utils;

import api.Pojo.DataDrivenPojo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    private  Workbook workbook;
    private  Sheet sheet;
    private  String filePath;

    public ExcelUtils(String sheetName, String filePath) {
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet(sheetName);
        this.filePath = filePath;
    }

    public void writeHeader(String[] headers) {
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
    }

    public void writeRow(int rowNum, String[] values) {
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i < values.length; i++) {
            row.createCell(i).setCellValue(values[i]);
        }
    }

    public void saveAndClose() throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
        workbook.close();
    }

    public static String readExcelData(int Sheetno, int Rowno, int Cellno) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(TestUtils.getProperty("ExcelPath")); // convert the Excel into inputstream
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);  //
        XSSFSheet sheet = workbook.getSheetAt(Sheetno);
        Row row = sheet.getRow(Rowno);
        Cell cell = row.getCell(Cellno);
        String cellData = cell.toString();
        return cellData;
    }

    public static String readExcelData(int Sheetno, int Rowno, int Cellno, String filePath) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(TestUtils.getProperty(filePath)); // convert the Excel into inputstream
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);  //
        XSSFSheet sheet = workbook.getSheetAt(Sheetno);
        Row row = sheet.getRow(Rowno);
        Cell cell = row.getCell(Cellno);
        String cellData = cell.toString();
        return cellData;
    }

    public static List<DataDrivenPojo> readExcelData()
    {
        List<DataDrivenPojo> dataDrivenPojos = new ArrayList<>();
        String filepath=TestUtils.getProperty("APIUsersExcel");

        try
        {
            FileInputStream fis = new FileInputStream(filepath);
            Workbook workbook = new XSSFWorkbook(fis);

        Sheet sheet = workbook.getSheetAt(0);
        int nameCol = -1, usernameCol = -1, emailCol = -1;

        // Get header row and column indices
        Row headerRow = sheet.getRow(0);
        for (Cell cell : headerRow) {
            String header = cell.getStringCellValue().trim().toLowerCase();
            if (header.equals("name")) nameCol = cell.getColumnIndex();
            if (header.equals("username")) usernameCol = cell.getColumnIndex();
            if (header.equals("email")) emailCol = cell.getColumnIndex();
        }

        // Read data rows
        for (int i = 1; i <= sheet.getLastRowNum(); i++)
        {
            Row row = sheet.getRow(i);
            if (row == null) continue;
            String name = row.getCell(nameCol).getStringCellValue();
            String username = row.getCell(usernameCol).getStringCellValue();
            String email = row.getCell(emailCol).getStringCellValue();
            dataDrivenPojos.add(new DataDrivenPojo(name, username, email));
        }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return dataDrivenPojos;
    }

    public static List<Map<String, String>> getData(String filePath,String sheetName) {

//        String filePath= TestUtils.getProperty("QueryParamExcel");

        List<Map<String, String>> dataList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getPhysicalNumberOfCells();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < colCount; j++) {
                    String key = headerRow.getCell(j).getStringCellValue();
                    String value = row.getCell(j) != null ? row.getCell(j).toString() : "";
                    rowData.put(key, value);
                }
                dataList.add(rowData);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading Excel file", e);
        }
        return dataList;
    }


}
