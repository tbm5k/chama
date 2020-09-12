package com.chama.app.excel;

import com.chama.app.models.Receipt;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Excel {

    public static List<Receipt> excelFile(InputStream inputStream) throws IOException {

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        List<Receipt> receipts = new ArrayList<>();

        int rowNumber = 0;

        while (rows.hasNext()){

            Row currentRow = rows.next();

            //skipping the header
            if(rowNumber == 0){
                rowNumber++;
                continue;
            }

            Iterator<Cell> rowCells = currentRow.iterator();

            Receipt receipt = new Receipt();

            int cellIndex = 0;
            while(rowCells.hasNext()){
                Cell currentCell = rowCells.next();

                if (cellIndex == 0) {
                    receipt.setMemberId((int) currentCell.getNumericCellValue());//member id
                } else if (cellIndex == 1) {
                    receipt.setReceiptNumber(currentCell.getStringCellValue());//receipt number
                } else if (cellIndex == 2) {
                    receipt.setReceiptAmount((int) currentCell.getNumericCellValue());//amount
                } else if (cellIndex == 3) {
                    receipt.setReceiptDate((Date) currentCell.getDateCellValue());//date
                } else if (cellIndex == 4) {
                    receipt.setPaymentMode(currentCell.getStringCellValue());//payment mode
                } else if (cellIndex == 5) {
                    receipt.setPaymentDescription(currentCell.getStringCellValue());//payment description
                } else if (cellIndex == 7) {
                    receipt.setContributionType(currentCell.getStringCellValue());//type of contribution
                } else if (cellIndex == 8) {
                    receipt.setReceiptType(currentCell.getStringCellValue());//type of receipt
                }

                cellIndex++;
            }

            receipts.add(receipt);

        }

        workbook.close();
        return receipts;

    }
}
