package e2e.entity.ui.carValueTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import e2e.entity.ui.core.HtmlOps;

public class CarCheckPage extends HtmlOps {

	public CarCheckPage() {
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, DEFAULT_UIELEMENT_WAIT_TIME);
		PageFactory.initElements(factory, this);
	}

	public CarCheckPage getcarFare(String registrationNumber) {
		setInputField(registrationNumber, registrationNumberInput);
		click(freeCarCheckBtn);
		
		if (isRegistrationValue()) {
			System.out.println("Continue to verify other elementss");
			return this;
		} else {
			isTryAgainAlertDisplayed();
			return null;
		}

	}

	public boolean isTryAgainAlertDisplayed() {
		return isElementVisisbleByWait(noVehicleFoundAlert);
	}

	public boolean isRegistrationValue() {
		return isElementVisisbleByWait(registrationValue);
	}
	
	public String getRegistrationText() {
		return getElementText(registrationValue);
	}

	public String getmakeValue() {
		return getElementText(makeValue);
	}

	public String gemodelValue() {
		return getElementText(modelValue);
	}

	public String getcolourValue() {
		return getElementText(colourValue);
	}

	public String getyearValue() {
		return getElementText(yearValue);
	}

	public String getv5CIssueDateValue() {
		return getElementText(v5CIssueDateValue);
	}

	public String getRegistrationTexts() {
		return getElementText(registrationValue);
	}

	@FindBy(xpath = "//dt[text()='Registration']/following-sibling::dd[1]")
	public WebElement registrationValue;

	@FindBy(xpath = "//dt[text()='Make']/following-sibling::dd[1]")
	public WebElement makeValue;

	@FindBy(xpath = "//dt[text()='Model']/following-sibling::dd[1]")
	public WebElement modelValue;

	@FindBy(xpath = "//dt[text()='Colour']/following-sibling::dd[1]")
	public WebElement colourValue;

	@FindBy(xpath = "//dt[text()='Year']/following-sibling::dd[1]")
	public WebElement yearValue;

	@FindBy(xpath = "//dt[text()='Registered']/following-sibling::dd[1]")
	public WebElement registeredValue;

	@FindBy(xpath = "//dt[text()='V5C Issue Date']/following-sibling::dd[1]")
	public WebElement v5CIssueDateValue;

	@FindBy(xpath = "//button[text()='Free Car Check']")
	public WebElement freeCarCheckBtn;

	@FindBy(id = "vrm-input")
	private WebElement registrationNumberInput;

	@FindBy(xpath = "//span[text()='Vehicle Not Found']")
	private WebElement noVehicleFoundAlert;

	@FindBy(xpath = "//a[text()='Try Again']")
	public WebElement tryAgain;

}
