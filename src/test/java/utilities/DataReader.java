package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {

	static FileInputStream file;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	static XSSFRow row;
	static XSSFCell cell;
	public static List<Map<String,String>> datamap(String filePath,String sheetName) throws IOException {
		List<Map<String,String>> listmap=new ArrayList();
		file=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(file);
		XSSFSheet sheet=workbook.getSheet(sheetName);
		XSSFRow headerRow=sheet.getRow(0);
		int rows=sheet.getLastRowNum();
		int cells=sheet.getRow(1).getLastCellNum();
		for(int r=1;r<=rows;r++) {
			Map<String,String> hm=new HashMap<String,String>();
			for(int c=0;c<cells;c++) {
				String cellValue = sheet.getRow(r).getCell(c).toString();
				hm.put(headerRow.getCell(c).toString(), cellValue);
			}
			listmap.add(hm);
		}
		workbook.close();
		file.close();
		return listmap;
	}

}
