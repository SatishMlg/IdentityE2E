package e2e.entity.ui.carValueTests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import e2e.entity.ui.core.EntityTestBase;
import e2e.entity.ui.core.EnvVars;

public class CompareCarValuesTests extends EntityTestBase {

	private static String url = EnvVars.DEFAULT_E2E_URL;
	private List<String> inputRegNumbersList;
	private String fileBasePath = System.getProperty("user.dir") + "\\src\\main\\resources\\";

	public CompareCarValuesTests() {
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, DEFAULT_UIELEMENT_WAIT_TIME);
		PageFactory.initElements(factory, this);
	}

	private void readValuesFromText(String fileName) throws Exception {
		String textread = EnvVars.readFile(fileBasePath, fileName);

		textread.substring(textread.toString().indexOf("registraion "));

		inputRegNumbersList = new ArrayList<>();
		String last = textread.substring(textread.length() - 9, textread.length() - 1);
		inputRegNumbersList.add(last);
		String[] words = textread.split(" ");
		for (int i = 0; i < words.length; i++) {

			if (words[i].equalsIgnoreCase("registration")) {
				String strrr = words[++i];

				if (strrr.length() < 7) {
					String twoo = strrr;
					twoo += words[++i];
					inputRegNumbersList.add(twoo);
				} else {
					inputRegNumbersList.add(strrr);
				}
			}
		}

	}

	@Test(description = "Login with Valid Credentials")
	public void getQuote() throws Exception {
		CarCheckPage carCheckPage = new CarCheckPage();
		SoftAssert sfAssert = new SoftAssert();
		readValuesFromText("car_input.txt");
		String carOutPut = EnvVars.readFile(fileBasePath, "car_output.txt");
		driver.get(url);
		for (int i = 0; i < inputRegNumbersList.size(); i++) {

			if (carCheckPage.getcarFare(inputRegNumbersList.get(i)) == null) {
				// No Vehicles Found Add Log
				System.out.println("No Vehicles found");
				click(carCheckPage.tryAgain);

			} else {
				sfAssert.assertTrue(carOutPut.contains(carCheckPage.getRegistrationText()),
						"Expected Registration number Not Found");

				sfAssert.assertTrue(carOutPut.contains(carCheckPage.getmakeValue()),
						"Expected getmakeValue number Not Found");

				sfAssert.assertTrue(carOutPut.contains(carCheckPage.gemodelValue()),
						"Expected gemodelValue number Not Found");

				sfAssert.assertTrue(carOutPut.contains(carCheckPage.getcolourValue()),
						"Expected getcolourValue number Not Found");

				System.out.println(carCheckPage.getyearValue());
				sfAssert.assertTrue(carOutPut.contains(carCheckPage.getyearValue()),
						"Expected getyearValue number Not Found");

				sfAssert.assertTrue(carOutPut.contains(carCheckPage.getv5CIssueDateValue()),
						"Expected getv5CIssueDateValue number Not Found");
				driver.navigate().back();
				sfAssert.assertAll();
				
			}

		}

	}

}
