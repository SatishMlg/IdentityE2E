package e2e.entity.ui.carValueTests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import e2e.entity.ui.core.HtmlOps;

public class CarCheckPage extends HtmlOps {

	public CarCheckPage() {
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,
				DEFAULT_UIELEMENT_WAIT_TIME);
		PageFactory.initElements(factory, this);
	}
	
	public CarCheckPage getcarFare(String registrationNumber) {
		setInputField(registrationNumber, registrationNumberInput);
		click(freeCarCheckBtn);
		return new CarCheckPage();
	}
	
	
	 	@FindBy(xpath = "//dt[text()='Registration']/following-sibling::dd[1]")
		public WebElement registrationText;
		
		@FindBy(xpath = "//button[text()='Free Car Check']")
		public WebElement freeCarCheckBtn;
		
		@FindBy(id="vrm-input")
		private WebElement registrationNumberInput;
		
		@FindBy(xpath = "//button[contains(@class,'submit-accept-request')]")
		private WebElement acceptOkBtn;
	
}
