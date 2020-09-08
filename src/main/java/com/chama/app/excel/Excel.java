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

    public List<Receipt> excelFile(InputStream inputStream) throws IOException {

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheet("Receipts");
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

                switch (cellIndex) {
                    case 0 -> receipt.setReceiptId((int) currentCell.getNumericCellValue());//receipt id
                    case 2 -> receipt.setMemberId((int) currentCell.getNumericCellValue());//member id
                    case 3 -> receipt.setReceiptNumber(currentCell.getStringCellValue());//receipt number
                    case 4 -> receipt.setReceiptAmount((int) currentCell.getNumericCellValue());//amount
                    case 5 -> receipt.setReceiptDate((Date) currentCell.getDateCellValue());//date
                    case 6 -> receipt.setPaymentMode(currentCell.getStringCellValue());//payment mode
                    case 7 -> receipt.setPaymentDescription(currentCell.getStringCellValue());//payment description
                    case 8 -> receipt.setReceiptStatus(currentCell.getStringCellValue());//receipt status
                    case 9 -> receipt.setContributionType(currentCell.getStringCellValue());//type of contribution
                    case 10 -> receipt.setReceiptType(currentCell.getStringCellValue());//type of receipt
                }

                cellIndex++;
            }

            receipts.add(receipt);

        }

        workbook.close();
        return receipts;

    }
}
