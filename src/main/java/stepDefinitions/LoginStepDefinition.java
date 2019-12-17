package stepDefinitions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import cucumber.api.java.en.Then;

public class LoginStepDefinition {

	WebDriver driver;

	@Test
	@Then("^user enters \"(.*)\" and \"(.*)\"$")
	public void user_already_on_login_page(int idFrom, int idTo) throws IOException, InterruptedException {
		String url = "https://lcr-pjr.doleta.gov/index.cfm?event=ehLCJRExternal.dspCert&doc_id=3&visa_class_id=1&id=";
		String srtID;
		String finalURL;
		// String chromeDriverPath = "C:\\Users\\Malibu
		// User\\Downloads\\chromedriver_win32\\chromedriver.exe" ;
		// System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			ChromeOptions options = new ChromeOptions();  
			options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");  
			WebDriver driver = new ChromeDriver(options); 

		//WebDriver driver = new ChromeDriver();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("us");
		int count = 0;
		try {

			for (int id = idFrom; id > idTo; id--) {

				XSSFRow row = spreadsheet.createRow((short) ++count);

				srtID = Integer.toString(id);
				finalURL = url.concat(srtID);
				System.out.println(finalURL);
				// driver.get(finalURL);
        driver.manage().deleteAllCookies();
				driver.navigate().to(finalURL);
				row.createCell(0).setCellValue(srtID);
				// System.out.println(driver.getTitle());
				if (driver.getTitle().endsWith("LCA Certification")) {
					if (driver.findElement(By.xpath(
							"//h4[contains(text(), 'Employment-Based Nonimmigrant Visa Information')]/parent::div[@class='secHeader']//following-sibling::div/p"))
							.getText() != "N/A") {

						row.createCell(1).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(), 'Employment-Based Nonimmigrant Visa Information')]/parent::div[@class='secHeader']//following-sibling::div/p"))
								.getText());

						// Temporary Need Information
						row.createCell(2).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Temporary Need Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'1. Job Title')]/parent::div/p"))
								.getText());
						row.createCell(3).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Temporary Need Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'2. SOC (ONET/OES) code')]/parent::div/p"))
								.getText());
						row.createCell(4).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Temporary Need Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'3. SOC (ONET/OES) occupation title')]/parent::div/p"))
								.getText());
						if (driver.findElements(By.xpath(
								"//h4[contains(text(),'Temporary Need Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'4. Is this a full-time position?')]/parent::div/ul/li[1]/img[@src='/includes/images/common/ico_chkbox_on.gif']"))
								.size() > 0) {
							row.createCell(5).setCellValue(driver.findElement(By.xpath(
									"//h4[contains(text(),'Temporary Need Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'4. Is this a full-time position?')]/parent::div/ul/li[1]"))
									.getText());
						} else
							row.createCell(5).setCellValue("NO");
						row.createCell(6).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Temporary Need Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'5. Begin Date (mm/dd/yyyy)')]/parent::div/p"))
								.getText());
						row.createCell(7).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Temporary Need Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'6. End Date (mm/dd/yyyy)')]/parent::div/p"))
								.getText());
						row.createCell(8).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Temporary Need Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'Total Worker Positions Being Requested for Certification')]/parent::div/p"))
								.getText());
						row.createCell(9).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Temporary Need Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'a. New employment')]/parent::div/p"))
								.getText());
						row.createCell(10).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Temporary Need Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'b. Continuation of previously approved employment without change with the same employer')]/parent::div/p"))
								.getText());
						row.createCell(11).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Temporary Need Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'c. Change in previously approved employment')]/parent::div/p"))
								.getText());
						row.createCell(12).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Temporary Need Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'d. New concurrent employment')]/parent::div/p"))
								.getText());
						row.createCell(13).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Temporary Need Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'e. Change in employer')]/parent::div/p"))
								.getText());
						row.createCell(14).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Temporary Need Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'f. Amended petition')]/parent::div/p"))
								.getText());
						// Employer Information
						row.createCell(15).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'C. Employer Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'1. Legal business name')]/parent::div/p"))
								.getText());
						row.createCell(16).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'C. Employer Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'2. Trade name/Doing Business As (DBA),')]/parent::div/p"))
								.getText());
						row.createCell(17).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'C. Employer Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'3. Address 1')]/parent::div/p"))
								.getText());
						row.createCell(18).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'C. Employer Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'4. Address 2')]/parent::div/p"))
								.getText());
						row.createCell(19).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'C. Employer Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'5. City')]/parent::div/p"))
								.getText());
						row.createCell(20).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'C. Employer Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'6. State')]/parent::div/p"))
								.getText());
						row.createCell(21).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'C. Employer Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'7. Postal code')]/parent::div/p"))
								.getText());
						row.createCell(22).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'C. Employer Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'8. Country')]/parent::div/p"))
								.getText());
						row.createCell(23).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'C. Employer Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'9. Province')]/parent::div/p"))
								.getText());
						row.createCell(24).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'C. Employer Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'10. Telephone number')]/parent::div/p"))
								.getText());
						row.createCell(25).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'C. Employer Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'11. Extension')]/parent::div/p"))
								.getText());
						row.createCell(26).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'C. Employer Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'13. NAICS code (must be at least 4-digits)')]/parent::div/p"))
								.getText());
						// Employer Point of Contact Information
						row.createCell(27).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Employer Point of Contact Information')]/parent::div[@class='secHeader']//following-sibling::div/h5/parent::div/p"))
								.getText());
						row.createCell(28).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Employer Point of Contact Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'2. First (given) name')]/parent::div/p"))
								.getText());
						row.createCell(29).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Employer Point of Contact Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'3. Middle name(s)')]/parent::div/p"))
								.getText());
						row.createCell(30).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Employer Point of Contact Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'4. Contact')]/parent::div/p"))
								.getText());
						row.createCell(31).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Employer Point of Contact Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'5. Address 1')]/parent::div/p"))
								.getText());
						row.createCell(32).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Employer Point of Contact Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'6. Address 2')]/parent::div/p"))
								.getText());
						row.createCell(33).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Employer Point of Contact Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'7. City')]/parent::div/p"))
								.getText());
						row.createCell(34).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Employer Point of Contact Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'8. State')]/parent::div/p"))
								.getText());
						row.createCell(35).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Employer Point of Contact Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'9. Postal code')]/parent::div/p"))
								.getText());
						row.createCell(36).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Employer Point of Contact Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'10. Country')]/parent::div/p"))
								.getText());
						row.createCell(37).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Employer Point of Contact Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'11. Province')]/parent::div/p"))
								.getText());
						row.createCell(38).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Employer Point of Contact Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'12. Telephone number')]/parent::div/p"))
								.getText());
						row.createCell(39).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Employer Point of Contact Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'13. Extension')]/parent::div/p"))
								.getText());
						row.createCell(40).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Employer Point of Contact Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'14. E-Mail address')]/parent::div/p"))
								.getText());
						// Attorney or Agent Information (If applicable)
						if (driver.findElements(By.xpath(
								"//h4[contains(text(),'E. Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'1. Is the employer represented by an attorney or agent in the filing of this appl')]/parent::div/ul/li[1]/img[@src='/includes/images/common/ico_chkbox_on.gif']"))
								.size() > 0) {
							row.createCell(41).setCellValue(driver.findElement(By.xpath(
									"//h4[contains(text(),'E. Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'1. Is the employer represented by an attorney or agent in the filing of this appl')]/parent::div/ul/li[1]"))
									.getText());
						} else

							row.createCell(41).setCellValue("NO");
						row.createCell(42).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'3. First (given) name')]/parent::div/p"))
								.getText());
						row.createCell(43).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'4. Middle name(s)')]/parent::div/p"))
								.getText());
						row.createCell(44).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'5. Address 1')]/parent::div/p"))
								.getText());
						row.createCell(45).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'6. Address 2')]/parent::div/p"))
								.getText());
						row.createCell(46).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'7. City')]/parent::div/p"))
								.getText());
						row.createCell(47).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'8. State')]/parent::div/p"))
								.getText());
						row.createCell(48).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'9. Postal code')]/parent::div/p"))
								.getText());
						row.createCell(49).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'10. Country')]/parent::div/p"))
								.getText());
						row.createCell(50).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'11. Province')]/parent::div/p"))
								.getText());
						row.createCell(51).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'12. Telephone number')]/parent::div/p"))
								.getText());
						row.createCell(52).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'13. Extension')]/parent::div/p"))
								.getText());
						row.createCell(53).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'14. E-Mail address')]/parent::div/p"))
								.getText());
						row.createCell(54).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'15. Law firm/Business name')]/parent::div/p"))
								.getText());
						row.createCell(57).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'16. Law firm/Business FEIN')]/parent::div/p"))
								.getText());
						row.createCell(58).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'17. State Bar number (only if attorney)')]/parent::div/p"))
								.getText());
						row.createCell(59).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'18. State of highest court where attorney is in good standing (only if attorney)')]/parent::div/p"))
								.getText());
						row.createCell(60).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'Attorney or Agent Information (If applicable)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'19. Name of the highest')]/parent::div/p"
										+ ""))
								.getText());
						// Notice of Obligations
						if (driver.findElements(By.xpath(
								"//h4[contains(text(),'Notice of Obligations')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'1. Last (family) name of hiring')]/parent::div/p"))
								.size() > 0) {

							row.createCell(62).setCellValue(driver.findElement(By.xpath(
									" //h4[contains(text(),'Notice of Obligations')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'1. Last (family) name of hiring')]/parent::div/p"))
									.getText());
							row.createCell(63).setCellValue(driver.findElement(By.xpath(
									" //h4[contains(text(),'Notice of Obligations')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'2. First (given) name of hiring')]/parent::div/p"))
									.getText());
							row.createCell(64).setCellValue(driver.findElement(By.xpath(
									" //h4[contains(text(),'Notice of Obligations')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'3. Middle initial')]/parent::div/p"))
									.getText());
							row.createCell(65).setCellValue(driver.findElement(By.xpath(
									" //h4[contains(text(),'Notice of Obligations')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'4. Hiring or designated official title')]/parent::div/p"))
									.getText());
						} else {
							row.createCell(62).setCellValue("no Obligations data avaliable ");
							row.createCell(63).setCellValue("no Obligations data avaliable ");
							row.createCell(64).setCellValue("no Obligations data avaliable ");
							row.createCell(65).setCellValue("no Obligations data avaliable ");
						}
						// LCA Preparer
						row.createCell(66).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'LCA Preparer')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'1. Last (family) name')]/parent::div/p"))
								.getText());
						row.createCell(67).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'LCA Preparer')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'2. First (given) name')]/parent::div/p"))
								.getText());
						row.createCell(68).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'LCA Preparer')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'3. Middle initial')]/parent::div/p"))
								.getText());
						row.createCell(69).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'LCA Preparer')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'4. Firm/Business name')]/parent::div/p"))
								.getText());
						row.createCell(70).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'LCA Preparer')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'5. E-Mail address')]/parent::div/p"))
								.getText());
						// U.S. Government Agency Use (ONLY)
						row.createCell(71).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'U.S. Government Agency Use (ONLY)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'This certification is valid')]/parent::div/p"))
								.getText());
						row.createCell(72).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'U.S. Government Agency Use (ONLY)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'Department of Labor, Office of Foreign Labor Certification')]/parent::div/p"))
								.getText());
						row.createCell(73).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'U.S. Government Agency Use (ONLY)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'Date (date signed)')]/parent::div/p"))
								.getText());
						row.createCell(74).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'U.S. Government Agency Use (ONLY)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'Case Number')]/parent::div/p"))
								.getText());
						row.createCell(75).setCellValue(driver.findElement(By.xpath(
								"//h4[contains(text(),'U.S. Government Agency Use (ONLY)')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'Case Status\')]/parent::div/p"))
								.getText());
						// Additional Employer Labor Condition Statements - H-1B Employers ONLY
						if (driver.findElements(By.xpath(
								"//h4[contains(text(),'Additional Employer Labor Condition Statements - H-1B Employers ONLY')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'1. At the time of filing this LCA, is the employer H-1B dependent?')]/parent::div/ul/li[1]/img[@src='/includes/images/common/ico_chkbox_on.gif']"))
								.size() > 0) {
							row.createCell(76).setCellValue(driver.findElement(By.xpath(
									"//h4[contains(text(),'Additional Employer Labor Condition Statements - H-1B Employers ONLY')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'1. At the time of filing this LCA, is the employer H-1B dependent?')]/parent::div/ul/li[1]"))
									.getText());
						} else
							row.createCell(76).setCellValue("NO");
						if (driver.findElements(By.xpath(
								"//h4[contains(text(),'Additional Employer Labor Condition Statements - H-1B Employers ONLY')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'2. At the time of filing this LCA, is the employer a willful violator?')]/parent::div/ul/li[1]/img[@src='/includes/images/common/ico_chkbox_on.gif']"))
								.size() > 0) {
							row.createCell(77).setCellValue(driver.findElement(By.xpath(
									"//h4[contains(text(),'Additional Employer Labor Condition Statements - H-1B Employers ONLY')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'2. At the time of filing this LCA, is the employer a willful violator?')]/parent::div/ul/li[1]"))
									.getText());
						} else
							row.createCell(77).setCellValue("NO");
						// Public Disclosure Information
						if (driver.findElements(By.xpath(
								"//h4[contains(text(),'I. Public Disclosure Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'1. Public disclosure information in the United States will be kept at:')]/parent::div/ul/li[1]/img[@src='/includes/images/common/ico_chkbox_on.gif']"))
								.size() > 0) {
							row.createCell(78).setCellValue(driver.findElement(By.xpath(
									"//h4[contains(text(),'I. Public Disclosure Information')]/parent::div[@class='secHeader']//following-sibling::div/h5[contains(text(),'1. Public disclosure information in the United States will be kept at:')]/parent::div/ul/li[1]"))
									.getText());
						} else
							row.createCell(78).setCellValue("Place of employment");

					}

				}
				else if (driver.getTitle().endsWith("Welcome to the iCERT LCR/PJR Portal System"))
				{
					while(driver.getTitle().endsWith("Welcome to the iCERT LCR/PJR Portal System"))
					{
					Thread.sleep(5000);
					driver.navigate().to(finalURL);
					System.out.println("Welcome to the iCERT LCR/PJR Portal System-->  retry logic working");
					id++;
					}
				}
				else if (driver.getTitle().contains("Exception"))
				{
					System.out.println("An Exception Occurred and page skip ");
				}
			}		} finally {

			FileOutputStream out = new FileOutputStream(new File("resunt" + idFrom + "to" + idTo + ".xlsx"));
			workbook.write(out);
			out.close();
			System.out.println(" written successfully");
      driver.close();
		}

	}
}
