package com.qa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateNewAccountPage extends BasePage{
	
	public String ContinueTextInHindi="अग्रसर रहें";
	public String ContinueTextInSpanish="Continuar";
	public String ContinueTextInEnglish="CONTINUE";
	
	@FindBy(xpath="//input[contains(@id,'undefined-undefined-Firstname')]")
	WebElement FirstName;
	
	@FindBy(xpath="//input[contains(@id,'undefined-undefined-Createparentusername')]")
	WebElement CreateParentUserName;

	@FindBy(xpath="//input[contains(@id,'undefined-undefined-Lastname')]")
	WebElement LastName;
	
	@FindBy(xpath="//input[contains(@id,'undefined-undefined-Createparentpassword')]")
	WebElement CreateParentPassword;
	
	@FindBy(xpath="//input[contains(@id,'undefined-undefined-Emailaddress')]")
	WebElement EmailAddress;
	
	@FindBy(xpath="//input[contains(@id,'undefined-undefined-Confirmpassword')]")
	WebElement ConfirmPassword;
	
	@FindBy(xpath="//input[contains(@id, 'undefined-undefined-ValidateCaptcha')]")
	WebElement VerifyCaptch;
	
	@FindBy(xpath="//span[text()='CREATE ACCOUNT']")
	WebElement CreateAccount;
	
	By LoadingElement = By.xpath("//div[text()='Loading...']");
			
	public CreateNewAccountPage(WebDriver driver, WebDriverWait wait) {
		super(driver, wait);
		PageFactory.initElements(driver, this);
	}
	public void waitTillLoadingDisappear() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(LoadingElement));
	}
	public String getFirstNameValue() {
		return FirstName.getAttribute("value");
	}
	public void setFirstName(String firstName) {
		setValueForTextBox(FirstName, firstName);
	}
	public String getCreateParentUserNameValue() {
		return CreateParentUserName.getAttribute("value");
	}
	public void setCreateParentUserName(String createParentUserName) {
		setValueForTextBox(CreateParentUserName, createParentUserName);
	}
	public String getLastNameValue() {
		return LastName.getAttribute("value");
	}
	public void setLastName(String lastName) {
		setValueForTextBox(LastName, lastName);
	}
	public String getCreateParentPasswordValue() {
		return CreateParentPassword.getAttribute("value");
	}
	public void setCreateParentPassword(String createParentPassword) {
		setValueForTextBox(CreateParentPassword, createParentPassword);
	}
	public String getEmailAddressValue() {
		return EmailAddress.getAttribute("value");
	}
	public void setEmailAddress(String emailAddress) {
		setValueForTextBox(EmailAddress, emailAddress);
	}
	public String getConfirmPasswordValue() {
		return ConfirmPassword.getAttribute("value");
	}
	public void setConfirmPassword(String confirmPassword) {
		setValueForTextBox(ConfirmPassword, confirmPassword);
	}
	public String getVerifyCaptchValue() {
		return VerifyCaptch.getAttribute("value");
	}	
	public boolean isCreateAccountButtonEnable() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",CreateAccount );
		if(CreateAccount.getAttribute("style").split(";")[1].split(":")[1].trim().equals("1")) {
			return true;
		}
		return false;
	}
}