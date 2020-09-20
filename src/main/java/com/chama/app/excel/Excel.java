package com.chama.app.excel;

import com.chama.app.models.Receipt;
import com.chama.app.models.multipledata.ExcelReceipt;
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

    public static List<ExcelReceipt> excelFile(InputStream inputStream) throws IOException {

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        List<ExcelReceipt> receipts = new ArrayList<>();

        int rowNumber = 0;

        while (rows.hasNext()){

            Row currentRow = rows.next();

            //skipping the header
            if(rowNumber == 0){
                rowNumber++;
                continue;
            }

            Iterator<Cell> rowCells = currentRow.iterator();

            ExcelReceipt receipt = new ExcelReceipt();

            int cellIndex = 0;
            Cell currentCell = null;
            while(rowCells.hasNext()){
                currentCell = rowCells.next();

                switch (cellIndex) {
                    case 0 -> receipt.setUserId((int) currentCell.getNumericCellValue());//member id
                    case 1 -> receipt.setReceiptNumber(currentCell.getStringCellValue());//receipt number
                    case 2 -> receipt.setReceiptAmount((int) currentCell.getNumericCellValue());//amount
                    case 3 -> receipt.setReceiptDate(new Date(currentCell.getDateCellValue().getTime()));
                    case 4 -> receipt.setPaymentMode(currentCell.getStringCellValue());//payment mode
                    case 5 -> receipt.setPaymentDescription(currentCell.getStringCellValue());//payment description
                    case 7 -> receipt.setContributionType(currentCell.getStringCellValue());//type of contribution
                }

                cellIndex++;
            }

            if (cellIndex == 8) receipt.setReceiptType(currentCell.getStringCellValue());//type of receipt

            receipts.add(receipt);

        }

        workbook.close();
        return receipts;

    }
}
