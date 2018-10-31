package seleniumlabs.testutils;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TableTestUtilities {
	// Utility method to print table contents
	// A good addition to a Table utility Class....
	// What other methods might you want?
	// May want cell tag instead of class attributed printed
	//
	public static void printTable(String header, WebElement table, HashMap<WebElement, Boolean> seenTables, String indent) {
		if (seenTables.containsKey(table) == true) {
			return;                                          // already printed this table
		}
		seenTables.put(table, true);
		System.out.println(indent + header);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		System.out.println(indent + "num table rows:" + rows.size());

		for (WebElement row : rows) {
			if (seenTables.containsKey(row) == true) {
				continue;                                    // already printed this row
			}
			seenTables.put(row, true);
			System.out.println(indent + "Row: ");
			List<WebElement> cells = row.findElements(By.tagName("td"));

			for (WebElement cell : cells) {
				if (seenTables.containsKey(cell) == true) {
					continue;                                // already printed this cell
				}
				seenTables.put(cell, true);
				List<WebElement> nested = cell.findElements(By.tagName("table"));
				if (nested.isEmpty()) {                      // normal cell
					System.out.println(indent + "\t  Cell: class: " + cell.getAttribute("class") + "   text: ["
							+ cell.getText() + "]");
				} else {                                     // nested table
					String moreIndent = indent + "    ";
					printTable(header + " Nested Table:", nested.get(0), seenTables, moreIndent);
				}
			}
		}
	}
}
