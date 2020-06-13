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
		String textread = EnvVars.readFile(fileBasePath, "car_output.txt");
		driver.get(url);
		for (int i = 0; i < inputRegNumbersList.size(); i++) {

			if (carCheckPage.getcarFare(inputRegNumbersList.get(i)) == null) {
				// No Vehicles Found Add Log
				System.out.println("No Vehicles found");

			}
			else {
			carCheckPage.getcarFare(inputRegNumbersList.get(i));
			System.out.println(carCheckPage.getRegistrationText());
			sfAssert.assertTrue(textread.contains(carCheckPage.getRegistrationText()),
					"Expected Registration number Not Found");

			carCheckPage.getcarFare(inputRegNumbersList.get(i));
			System.out.println(carCheckPage.getmakeValue());
			sfAssert.assertTrue(textread.contains(carCheckPage.getmakeValue()),
					"Expected Registration number Not Found");

			carCheckPage.getcarFare(inputRegNumbersList.get(i));
			System.out.println(carCheckPage.gemodelValue());
			sfAssert.assertTrue(textread.contains(carCheckPage.gemodelValue()),
					"Expected Registration number Not Found");

			carCheckPage.getcarFare(inputRegNumbersList.get(i));
			System.out.println(carCheckPage.getcolourValue());
			sfAssert.assertTrue(textread.contains(carCheckPage.getcolourValue()),
					"Expected Registration number Not Found");

			carCheckPage.getcarFare(inputRegNumbersList.get(i));
			System.out.println(carCheckPage.getyearValue());
			sfAssert.assertTrue(textread.contains(carCheckPage.getyearValue()),
					"Expected Registration number Not Found");

			carCheckPage.getcarFare(inputRegNumbersList.get(i));
			System.out.println(carCheckPage.getv5CIssueDateValue());
			sfAssert.assertTrue(textread.contains(carCheckPage.getv5CIssueDateValue()),
					"Expected Registration number Not Found");
			}

		}

	}

}
