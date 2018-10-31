package framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class KeyWordController {
	private static final String TestCases    = "KwTestCases.xlsx";
	private static final String openBrowser  = "openbrowser";
	private static final String goToURL      = "gotourl";
	private static final String enterText    = "entertext";
	private static final String clickOnLink  = "clickonlink";
	private static final String clickOnButton= "clickonbutton";
	private static final String clickOnRadio = "clickonradio";
	private static final String closeBrowser = "closebrowser";
	private static final String AssertEquals = "assertequals";
	private static final String SelectValue  = "selectvalue";
	private static final String SelectIndex  = "selectindex";
	
	private KeyWordAction kwAction;

	public KeyWordController() {
		this.kwAction = new KeyWordAction();
	}

	public void performAction(String action, String locType, String locVal, String data) throws InterruptedException  {
		System.out.printf("performAction():  action: %s Locator Type:%s Locator Value: %s Test Data: %s\n",
					       action, locType, locVal, data);
		action = action.toLowerCase();			// remove capital letters
		switch(action)  {
		case openBrowser: 
			kwAction.openBrowser(data);
			break;
		case goToURL: 
			kwAction.goToURL(data);
			break;
		case enterText: 
			kwAction.enterText(locType, locVal, data);
			break;
		case clickOnLink: 
		case clickOnButton: 
			kwAction.clickOnElement(locType, locVal);
			break;
		case clickOnRadio: 
			kwAction.clickOnRadio(locType, locVal, data);
			break;
		case closeBrowser: 
			Thread.sleep(300);					// so can see what happened 
			kwAction.closeBrowser();
			break;
		case AssertEquals: 
			kwAction.assertEquals(locType, locVal, data);
			break;
		case SelectValue: 
			kwAction.selectValue(locType, locVal, data);
			break;
		case SelectIndex: 
			kwAction.selectIndex(locType, locVal, data);
			break;

		default:
			System.err.println("Unknown Action Type: " + action);		
		}
	}

	public void runTestSteps(XSSFSheet sheet) throws InterruptedException {
		XSSFRow step;
		Row.MissingCellPolicy convert = Row.MissingCellPolicy.CREATE_NULL_AS_BLANK ;
		Iterator<Row> tstSteps = sheet.rowIterator(); 	// each row is a test step
		tstSteps.next();								// skip header row
		while (tstSteps.hasNext()) {
			step = (XSSFRow) tstSteps.next();			// get next test step
			String action  = cellToString(step.getCell(0, convert));
			String locType = cellToString(step.getCell(1, convert));
			String locVal  = cellToString(step.getCell(2, convert));
			String data    = cellToString(step.getCell(3, convert));

			performAction(action, locType, locVal, data);
		}
	}

	private String cellToString(XSSFCell cell) {
		String val = "";
		switch (cell.getCellType()) {
		case BLANK:
			val = "";
			break;
		case STRING:
			val = cell.getStringCellValue();
			break;
		case NUMERIC:
			Integer intVal =  new Double(cell.getNumericCellValue()).intValue();
			val = intVal.toString();
			break;
		case BOOLEAN:
			val = new Boolean(cell.getBooleanCellValue()).toString();
			break;
		case FORMULA:
			val = cell.getCellFormula();
			break;
		case ERROR:
			val = cell.getErrorCellString();
			break;
		default:
			System.err.println("Unknown Cell Type: " + cell.getCellType());
			val = "";
		}
		return val;
	}

	
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		KeyWordController exeKey = new KeyWordController();
		FileInputStream readFile = null;
		XSSFWorkbook workbook = null;
		
		readFile = new FileInputStream(new File(TestCases));
		workbook = new XSSFWorkbook(readFile); 		// Get workbook from xlsx file
		XSSFSheet sheet = workbook.getSheetAt(0); 	// get first sheet
		
		exeKey.runTestSteps(sheet);					// execute each test step
		
		readFile.close();							// clean up!
		workbook.close();
	}
}
