package ui.Utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static ui.Utils.TestUtils.getProperty;

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
        FileInputStream fileInputStream = new FileInputStream(getProperty("ExcelPath")); // convert the Excel into inputstream
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);  //
        XSSFSheet sheet = workbook.getSheetAt(Sheetno);
        Row row = sheet.getRow(Rowno);
        Cell cell = row.getCell(Cellno);
        String cellData = cell.toString();
        return cellData;
    }
}
