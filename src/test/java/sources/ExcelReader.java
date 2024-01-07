package sources;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ExcelReader {

    private Iterator<Row> iterator;

    public ExcelReader(String filePath) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            iterator = sheet.iterator();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasNext() {
        return iterator.hasNext();
    }

    public RowData getNextRow() {
        Row row = iterator.next(); // Move to the next row

        // Skip the first row (header) and start reading from the second row (A2)
        while (row.getRowNum() == 0 && iterator.hasNext()) {
            row = iterator.next();
        }

        RowData rowData = new RowData(
                getCellValueAsString(row.getCell(0)),  // Adjusted index to 0 for the first column (indexing is zero-based)
                getCellValueAsString(row.getCell(1)),
                getCellValueAsString(row.getCell(2)),
                getCellValueAsString(row.getCell(3)),
                getCellValueAsString(row.getCell(4)),
                getCellValueAsString(row.getCell(5)),
                getCellValueAsString(row.getCell(6))   // Assuming the seventh column contains the phone number
        );

        System.out.println("Read from Excel: " + rowData.toString());
        return rowData;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString().trim();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue()).trim();
                }
            default:
                return "";
        }
    }
}
