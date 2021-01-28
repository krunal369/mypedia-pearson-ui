package com.qa.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
	//Class will contains all General webelement like textbox, button and activities related to webelement.
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	public BasePage(WebDriver driver, WebDriverWait wait) {
		this.driver=driver;
		this.wait=wait;
	}
	public String getElementText(WebElement element) {
		//function will return text for webelement.
		return element.getText();
	}
	public void setValueForTextBox(WebElement element, String Value) {
		element.clear();
		element.sendKeys(Value);
	}
	public void waitForPageToGetLoad() {
		wait.until(WebDriver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
	}
	public void swithToFrameById(String IdName) {
		driver.switchTo().frame(IdName);
	}
	public void swithToDefaultConetent() {
		driver.switchTo().defaultContent();
	}
}
