package e2e.entity.ui.carValueTests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.annotations.Test;

import e2e.entity.ui.core.EntityTestBase;
import e2e.entity.ui.core.EnvVars;

public class CompareCarValuesTests extends EntityTestBase{
	
	public static String url = EnvVars.DEFAULT_E2E_URL;
	
	public CompareCarValuesTests() {
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,
				DEFAULT_UIELEMENT_WAIT_TIME);
		PageFactory.initElements(factory, this);
	}
	
	
	private void readValuesFromText() throws Exception {
		String textread = EnvVars.readFile(System.getProperty("user.dir") + "\\src\\main\\resources\\",
				"car_input.txt");

		textread.substring(textread.toString().indexOf("registraion "));

		List<String> list = new ArrayList<>();
		String last = textread.substring(textread.length() - 9, textread.length() - 1);
		list.add(last);
		String[] words = textread.split(" ");
		for (int i = 0; i < words.length; i++) {

			if (words[i].equalsIgnoreCase("registration")) {
				String strrr = words[++i];

				if (strrr.length() < 7) {
					String twoo = strrr;
					twoo += words[++i];
					list.add(twoo);
				} else {
					list.add(strrr);
				}
			}
		}

	}
	
	 @Test(description = "Login with Valid Credentials")
	 public void getQuote()  throws Exception {
		 	CarCheckPage carCheckPage = new CarCheckPage();
			  driver.get(url);
			  carCheckPage.getcarFare("DN09HRM");
		/*
		 * driver.findElement(By.id("vrm-input")).sendKeys("DN09HRM");
		 * driver.findElement(By.xpath("//button[text()='Free Car Check']")).click();
		 */
			  System.out.println(carCheckPage.registrationText.getText());
			  
	
	
	 }
	 
	 public String getElementText(WebElement element) {
		 String val = element.getText();
			if (null != val && !val.isEmpty()) {
				return val;
			}
			return element.getAttribute("value");
	 }
	
	 
}
