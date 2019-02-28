package com.test.automation.RakuRaku.excelReader;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Reader
{

	
	public FileOutputStream fileOut=null;
	public FileInputStream fis;
	public String path;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	
	
	

	public Excel_Reader(String path) 
	{
		this.path = path;
		try 
		{
			fis= new FileInputStream(path);
			workbook= new XSSFWorkbook(fis);	
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public String[][] getDataFromSheet(String sheetName,String ExcelName)
	{
		String dataSets[][]= null;
		try {
			//Get sheet from excel workbook
			XSSFSheet sheet= workbook.getSheet(sheetName);
			//Count number of active rows
			int totalrow = sheet.getLastRowNum() + 1;
			//Count number of active columns in row
			int totalcolumn = sheet.getRow(0).getLastCellNum();
			//Create array of rows and column
			dataSets = new String[totalrow-1][totalcolumn];
			//Run  the loop and store data in array
			//This for loop will run on rows 
			for (int i=1; i<totalrow; i++ ) 
			{
				XSSFRow rows= sheet.getRow(i) ;
				//This for loop will run on columns of that row
				for(int j=0; j<totalcolumn; j++) 
				{
					XSSFCell cell=rows.getCell(j);
					//If cell of string type, then this if condition will work
					if(cell.getCellType()==Cell.CELL_TYPE_STRING)
					dataSets[i-1][j] = cell.getStringCellValue();
					//If cell of number type, then this if condition will work
					else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
					{
						String cellText= String.valueOf(cell.getNumericCellValue());
						dataSets[i-1][j] = cellText;
					}else
						   //If cell of boolean type, then this if condition will work
							dataSets[i-1][j] = String.valueOf(cell.getBooleanCellValue());	
					}
				}return dataSets;
			}
			catch(Exception e) 
			{
				System.out.println("Exception is reading Xlxs file" +e.getMessage());
				e.printStackTrace();
			}
			return dataSets;
			
		}	
		
	
	
	public String getCellData(String sheetName, String colName, int rowNum) 
	{
		try
	 {
		int col_Num=0;
		int index= workbook.getSheetIndex(sheetName);
		sheet= workbook.getSheetAt(index);
		XSSFRow rows= sheet.getRow(0);
		for(int i=0; i<row.getLastCellNum(); i++ )
		{
			if(row.getCell(i).getStringCellValue().equals(colName)) 
			{
				col_Num= i;
				break;
			}
		}
		row = sheet.getRow(rowNum-1);
		XSSFCell cell= row.getCell(col_Num);
		if(cell.getCellType()==Cell.CELL_TYPE_STRING) 
		{
			return cell.getStringCellValue();
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_BLANK) 
		{
			return "";
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	 }
		
}
				

