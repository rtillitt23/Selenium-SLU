package excelutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Use Apache Poi to read and write from an Excel Spread Sheet This method will
 * be used in a Keyword-Driven Framework to read in keywords and argument.
 * 
 * Steps: Open Excel Workbook Read each row, and each column in eac row Write a
 * new row Close the Excel Workbook
 * 
 * @author bbirze
 */
public class ExcelIO {
	private static String TestExcelFile = "testXlsx.xlsx";

	public void writeXLSXFileTest(String fileName) throws FileNotFoundException, IOException {
		XSSFWorkbook workbook = null;
		FileOutputStream writeFile = null;
		File f = new File(fileName);
		if (f.exists() == true) {
			f.delete(); // file exists, delete it...
		}
		workbook = new XSSFWorkbook(); // create Excel file!
		XSSFSheet spreadsheet = workbook.createSheet(" Employee Info "); 
		XSSFRow row;

		// This data needs to be written (Object[])
		Map<String, Object[]> empinfo = new TreeMap<String, Object[]>();
		empinfo.put("1", new Object[] { "EMP-ID", "   EMP-NAME  ", "    EMP-ROLE   " });
		empinfo.put("2", new Object[] { "283647", "Smarty Pants ", "Architect       " });
		empinfo.put("3", new Object[] { "077547", "Tippy Markits", "VP of Marketing " });
		empinfo.put("4", new Object[] { "529876", "Turna Fraze  ", "Technical Writer" });
		empinfo.put("5", new Object[] { "658745", "Justa Facks  ", "S/W Engineer    " });
		empinfo.put("6", new Object[] { "895214", "Lotta Hellp  ", "Human Resources " });

		Set<String> keyid = empinfo.keySet(); // Iterate over data, write to sheet
		int rowid = 0;

		for (String key : keyid) {
			row = spreadsheet.createRow(rowid++);
			Object[] objectArr = empinfo.get(key);
			int cellid = 0;

			for (Object obj : objectArr) {
				Cell cell = row.createCell(cellid++);
				cell.setCellValue((String) obj);
			}
		}
		writeFile = new FileOutputStream(new File(fileName));
		workbook.write(writeFile);
		writeFile.close();
		workbook.close();
		System.out.println(TestExcelFile + " written successfully!\n");
	}

	public void readXLSXFileTest(String fileName) throws FileNotFoundException, IOException {
		FileInputStream readFile = null;
		XSSFWorkbook workbook = null;
		System.out.println("Reading Excel file: " + TestExcelFile);
		
		readFile = new FileInputStream(new File(fileName));
		workbook = new XSSFWorkbook(readFile); // Get the workbook from xlsx file

		XSSFSheet sheet = workbook.getSheetAt(0); 		// get first sheet
		XSSFRow row;
		XSSFCell cell;

		Iterator<Row> rows = sheet.rowIterator(); // Iterating all the rows in
													// the sheet
		while (rows.hasNext()) {
			row = (XSSFRow) rows.next();

			Iterator<Cell> cells = row.cellIterator(); // Iterating all cells of
														// current row
			while (cells.hasNext()) {
				cell = (XSSFCell) cells.next();

				switch (cell.getCellType()) {
				case STRING:
					System.out.print(cell.getStringCellValue() + " ");
					break;
				case NUMERIC:
					System.out.print(cell.getNumericCellValue() + " ");
					break;
				case BOOLEAN:
					System.out.print(cell.getBooleanCellValue() + " ");
					break;
				case FORMULA:
					System.out.print(cell.getCellFormula() + " ");
					break;
				case ERROR:
					System.out.print(cell.getErrorCellString() + " ");
					break;
				case BLANK:
					System.out.print("<Blank Cell> ");
					break;
				default:
					System.err.println("Unknown Cell Type: " + cell.getCellType());
				}
			}
			System.out.println();
		}
		try {
			readFile.close();
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		ExcelIO xlsxIO = new ExcelIO();
		xlsxIO.writeXLSXFileTest(TestExcelFile); // write new Excel File
		xlsxIO.readXLSXFileTest(TestExcelFile);  // Read new File 
	}

}
