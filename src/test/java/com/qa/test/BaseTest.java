package com.qa.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import com.qa.Pages.BasePage;
public class BaseTest {
	//class will use to define some basic activities like initialize browser, reading values from property file, close browser etc.
	public WebDriver driver;
	public WebDriverWait wait;
	public BasePage basePage;

	
	public Properties readPropertyFileValue() {
		//Will load property file and return property class reference.
		Properties prop = new Properties();
		FileInputStream input;
		try {
			input = new FileInputStream(new File("./configurations/configuration.properties"));
			prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
			return prop;
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("resource")
	public XSSFSheet getExcelSheet(String WorkBookPath, String SheetName) {
		try{  
			//creating Workbook instance that refers to .xlsx file  
			return new XSSFWorkbook(new FileInputStream(new File(WorkBookPath))).getSheet(SheetName);     //creating a Sheet object to retrieve object  
		}catch(Exception e){  
			e.printStackTrace();
		}  
		return null;
	}
	public String getCellValeus(XSSFSheet sheet, int row, int column) {
		Cell cell=sheet.getRow(row).getCell(column);
		switch (cell.getCellType()) {
			case STRING:
				return cell.getStringCellValue();
			case FORMULA:
				return cell.getCellFormula();
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					return cell.getDateCellValue().toString();
				} else {
					return Double.toString(cell.getNumericCellValue());
				}
			case BLANK:
				return "";
			case BOOLEAN:
				return Boolean.toString(cell.getBooleanCellValue());

			default:
				return null;
		}
	}

	@BeforeMethod
	public void setUp(){
		//will initialize browser.
		try {
			System.setProperty("webdriver.chrome.driver", new File("./chormeDriverExe/chromedriver.exe").getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
		driver.manage().window().maximize();
		driver.get(readPropertyFileValue().getProperty("baseUrl").trim());
		basePage= new BasePage(driver, wait);
	}
	
	@AfterMethod
	public void tearDown() {
		//close browser.
		driver.quit();
	}

}
