package common_method;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class get_data {
	public static ArrayList<String> getdataexcel(String testsheetname,String testcasename) throws IOException
	{
		ArrayList<String> ArrayOfData = new ArrayList<String>();
		// step-1 to open the file
		//Step -2 locate excel data file by creating object of fileinputstream
		FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\Desktop\\test_data.xlsx");
		
	    //create the object of XSSFWorkbook to open the excel file
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		//Step-3 access the desired sheet
		//Step-3.1 fetch the count of sheet available in the excel file
		int countOfSheet = workbook.getNumberOfSheets();
		
		//Step-3.2 fetch the name of sheet and compare against desired sheet name
		for(int i=0;i<countOfSheet;i++)
		{
			String sheetname = workbook.getSheetName(i);
			if(sheetname.equalsIgnoreCase(testsheetname))
			{
				//System.out.println(testsheetname);
				//step-4 access the sheet and iterate through rows to fetch the coloumn in which testcasename found
				 XSSFSheet sheet =workbook.getSheetAt(i);
				
				 //Step-4.1 create iterator for rows
				 Iterator<Row> Rows = sheet.iterator();
				 Row firstRow = Rows.next();
				  // Step 4.2 create iterator for cells
				 Iterator<Cell> Cells = firstRow.cellIterator();
				 int j=0;
				 int tc_column=0;
				 
				 // Step 4.3 read the cell values of rownumber 1 to comapare against the test case name
				 while(Cells.hasNext())
				 {
					 Cell CellValue = Cells.next();
					 if (CellValue.getStringCellValue().equalsIgnoreCase("tc_name"))
					 {
						 tc_column = j;
				     }
					 j++;
				 }
				 //Step 5 fetch the data for designated test case
				 while(Rows.hasNext())
				 {
					 Row dataRow = Rows.next();
					 if (dataRow.getCell(tc_column).getStringCellValue().equalsIgnoreCase(testcasename))
					 {
						 Iterator<Cell> dataCellValue = dataRow.cellIterator();
						 while(dataCellValue.hasNext())
						 { 
							 Cell dataOfCell = dataCellValue.next();
							 
							 //Method -1 to extract cell value by using try and catch
//							 try
//							 { 
//							   String testdata = dataOfCell.getStringCellValue();
//							   //System.out.println(testdata);
//							   ArrayOfData.add(testdata);
//						     }
//           					 catch(IllegalStateException e) 
//							 {
//							   int intTestData =  (int) dataOfCell.getNumericCellValue();
//							   String stringtestdata  = Integer.toString(intTestData);
//							   //System.out.println(stringtestdata);
//							   ArrayOfData.add(stringtestdata);
//							 }
							 
							 //Method -2 to extract cell value by using if and else
//							   CellType dataType = dataOfCell.getCellType();
//							  if (dataType.toString() == "NUMERIC")
//                              {
//                            	  int intTestData = (int) dataOfCell.getNumericCellValue();
//                            	  String stringtestdata  = Integer.toString(intTestData);
//                            	  //System.out.println(intTestData);
//                            	  ArrayOfData.add(stringtestdata);
//							 
//	                          }
//							 else if (dataType.toString() == "STRING")
//							 {
//								 String testdata = dataOfCell.getStringCellValue();
//								 //System.out.println(testdata);
//							     ArrayOfData.add(testdata);							      
//							 }
					 
							 //Method -3 extract the data by converting it into string
//							 String testdata = dataOfCell.toString().replaceAll("\\.\\d+$","");
//							 System.out.println(testdata);
//							 ArrayOfData.add(testdata);	
							 
							
							 //Method -4 Extract data by using Dataformatter
							 DataFormatter format = new DataFormatter();
							 String testdata = format.formatCellValue(dataOfCell);
							 System.out.println(testdata);
                       	     ArrayOfData.add(testdata);	
							 
					     }
				 }
			}
		}
	}
   return ArrayOfData;
  }
	
}