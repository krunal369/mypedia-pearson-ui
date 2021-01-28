package com.qa.Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage{
	private String frameId="contentIframe";
	
	@FindBy(css="#divNext")
	WebElement NextButton; 	
	
	@FindBy(css=".accountDetailsLangDropDown")
	WebElement LanguageDropDown; 	
	
	@FindBy(css="span[role='menuitem']")
	List<WebElement> LanguageDropDownItem;
	
	@FindBy(css=".childSupportLink a")
	WebElement SetupParentSupport;
	
	@FindBy(xpath="//span//div[text()='हिंदी']")
	WebElement HindiLangugeDropDownOption;
	
	@FindBy(xpath="//span//div[text()='English']")
	WebElement EnglishLanguageDropDownOption;
	
	@FindBy(xpath="//span//div[text()='Español']")
	WebElement SpanishLanguageDropDownOption;
	
	
	@FindBy(xpath="//button[@id='SI_0005']//div[text()='अग्रसर रहें']")
	WebElement ContinueButtonForHindi;
	
	@FindBy(xpath="//button[@id='SI_0005']//div[text()='Continue']")
	WebElement ContinueButtonForEnglish;
	
	@FindBy(xpath="//button[@id='SI_0005']//div[text()='Continuar']")
	WebElement ContinueButtonForSpanish;
	
	@FindBy(xpath="//button//div//span[text()='CREATE A NEW ACCOUNT']")
	WebElement CreateNewAccount;

	public HomePage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageFactory.initElements(driver, this);
	}
	public void swithToFrame() {
		swithToFrameById(frameId);
	}
	public void clickOnDoneButton() {
		NextButton.click();
	}
	public void clickOLanguageDropDown() {
		wait.until(ExpectedConditions.visibilityOf(LanguageDropDown));
		LanguageDropDown.click();
	}
	public int getLanguageDropDownCount() {
		return LanguageDropDownItem.size();
	}
	public void clickOnSetupParentSupportLink() {
		SetupParentSupport.click();
	}
	public void clickOnLanguageMenuItem() {
		LanguageDropDownItem.get(0).click();
	}
	public void clickOnCreateNewAccount() {
		CreateNewAccount.click();
	}
	public void selectEnglishLanguageDropDownOption() {
		wait.until(ExpectedConditions.visibilityOf(EnglishLanguageDropDownOption));
		EnglishLanguageDropDownOption.click();
	}
	public void selectHindiLanguageDropDownOption() {
		wait.until(ExpectedConditions.visibilityOf(HindiLangugeDropDownOption));
		HindiLangugeDropDownOption.click();
	}
	public void selectSpanishLanguageDropDownOption() {
		wait.until(ExpectedConditions.visibilityOf(SpanishLanguageDropDownOption));
		SpanishLanguageDropDownOption.click();
	}
	public String getContinueButtonTextInHindi() {
		return ContinueButtonForHindi.getText();
	}
	public String getContinueButtonTextInEnglish() {
		return ContinueButtonForEnglish.getText();
	}
	public String getContinueButtonTextInSpanish() {
		return ContinueButtonForSpanish.getText();
	}
}
