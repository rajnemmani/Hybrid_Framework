package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
XSSFWorkbook wb;
//constructor for reading excel path
public ExcelFileUtil(String ExcelPath)throws Throwable
{
	FileInputStream fi = new FileInputStream(ExcelPath);
	wb= new XSSFWorkbook(fi);
}
//Method for counting no of rows in a sheet
public int rowCount(String sheetName)
{
	return wb.getSheet(sheetName).getLastRowNum();
}
//Read String type data from cell
//Method for Reading Cell data
public String getCellData(String sheetname, int row, int column)
{
	String data;
	if(wb.getSheet(sheetname).getRow(row).getCell(column).getCellType()==CellType.NUMERIC)
	{
		int celldata = (int)wb.getSheet(sheetname).getRow(row).getCell(column).getNumericCellValue();
		data = String.valueOf(celldata);
	}
	else
	{
		data = wb.getSheet(sheetname).getRow(row).getCell(column).getStringCellValue();
	}
	return data;
}
//Method for set cell data
public void setCelldata(String sheetname, int row, int column, String status, String writeExcel)throws Throwable
{
	//Get sheet from workbook
	XSSFSheet ws = wb.getSheet(sheetname);
	//Get row from sheet
	XSSFRow rowNum = ws.getRow(row);
	//Create Cell in a Row
	XSSFCell cell = rowNum.createCell(column);
	//Write Status in to Cell
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("pass")) 
	{
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		style.setFont(font);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Fail"))
	{
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		style.setFont(font);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("Blocked"))
	{
		XSSFCellStyle style = wb.createCellStyle();
		XSSFFont font = wb.createFont();
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBold(true);
		style.setFont(font);
		ws.getRow(row).getCell(column).setCellStyle(style);
	}
	FileOutputStream fo = new FileOutputStream(writeExcel); 
	wb.write(fo);
}
}
