package com.qa.test;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.Pages.CreateNewAccountPage;
import com.qa.Pages.HomePage;

public class HomePageTest extends BaseTest{
	public HomePage homePage;
	public CreateNewAccountPage createNewAccountPage;
	
	
	public void initlizeObject() {
		homePage=new HomePage(driver, wait);
		createNewAccountPage = new CreateNewAccountPage(driver, wait);
	}
	public boolean chekAllFieldStatus() {
		//verify none of field should be blank.
		if(!createNewAccountPage.getFirstNameValue().isEmpty()) {
			return false;
		}else if(!createNewAccountPage.getCreateParentUserNameValue().isEmpty()) {
			return false;
		}else if (!createNewAccountPage.getLastNameValue().isEmpty()) {
			return false;
		}else if (!createNewAccountPage.getCreateParentPasswordValue().isEmpty()) {
			return false;
		}else if (!createNewAccountPage.getEmailAddressValue().isEmpty()) {
			return false;
		}else if (!createNewAccountPage.getConfirmPasswordValue().isEmpty()) {
			return false;
		}else if(!createNewAccountPage.getVerifyCaptchValue().isEmpty()) {
			return false;
		}
		return true;
	}
	@Parameters({"ExcelSheetPath","SheetName"})
	@Test
	public void verifyParentSupportPage(String ExcelSheetPath, String SheetName) {
		initlizeObject();
		
		//Ensure this website loads: https://www.mypedia.pearson.com/
		homePage.waitForPageToGetLoad();
		homePage.swithToFrame();
		homePage.clickOnDoneButton();	
		homePage.swithToDefaultConetent();

		//Confirm that language dropdown has at least 3 languages
		homePage.clickOLanguageDropDown();
		Assert.assertTrue(homePage.getLanguageDropDownCount()==3, "Languge count is not equal to 3");
		homePage.selectEnglishLanguageDropDownOption();
		Assert.assertTrue(homePage.getContinueButtonTextInEnglish().equalsIgnoreCase(createNewAccountPage.ContinueTextInEnglish));

		//Confirm that language dropdown has at least 3 languages
		homePage.clickOLanguageDropDown();
		homePage.selectHindiLanguageDropDownOption();
		Assert.assertTrue(homePage.getContinueButtonTextInHindi().equalsIgnoreCase(createNewAccountPage.ContinueTextInHindi));
		
		//Confirm that language dropdown has at least 3 languages
		homePage.clickOLanguageDropDown();
		homePage.selectSpanishLanguageDropDownOption();
		Assert.assertTrue(homePage.getContinueButtonTextInSpanish().equalsIgnoreCase(createNewAccountPage.ContinueTextInSpanish));
		
		homePage.clickOLanguageDropDown();
		homePage.selectEnglishLanguageDropDownOption();
		
		//Click on "setup parent support" -> Create a new account
		homePage.clickOnSetupParentSupportLink();		
		homePage.clickOnCreateNewAccount();
		createNewAccountPage.waitTillLoadingDisappear();
		
		//Fill all the details to create an account. Make this data driven so it could be executed multiple times.
		XSSFSheet AccountDetailsSheet = getExcelSheet(ExcelSheetPath, SheetName);
		int TotallRows=AccountDetailsSheet.getLastRowNum()+1;
		if (TotallRows>1) {
			for(int row=1;row<AccountDetailsSheet.getLastRowNum()+1; row++) {
				int column=0;
				createNewAccountPage.setFirstName(getCellValeus(AccountDetailsSheet, row, column));
				createNewAccountPage.setCreateParentUserName(getCellValeus(AccountDetailsSheet, row, ++column));
				createNewAccountPage.setLastName(getCellValeus(AccountDetailsSheet, row, ++column));
				createNewAccountPage.setCreateParentPassword(getCellValeus(AccountDetailsSheet, row, ++column));
				createNewAccountPage.setEmailAddress(getCellValeus(AccountDetailsSheet, row, ++column));
				createNewAccountPage.setConfirmPassword(getCellValeus(AccountDetailsSheet, row, ++column));	
				//Assert that "create account" button is disabled till all fields are filled.
				Assert.assertFalse(createNewAccountPage.isCreateAccountButtonEnable(), "Create New Account is disable");
			
			}
		}
	}
	
}
