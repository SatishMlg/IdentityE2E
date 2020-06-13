package e2e.entity.ui.core;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HtmlOps {
	static long ajax_pageload_time = 15; // Seconds for page load.
	public static final int DEFAULT_UIELEMENT_WAIT_TIME = 15;
	protected static final int WAIT_TIME = 45; // in seconds
	private static final long WAIT_POLL_TIME = 5000; // in milliseconds
	public static WebDriver driver;

	public WebElement setInputField(String value, WebElement htmlelement) throws ElementNotVisibleException {
		int switchedToFrame = 0;
		WebElement element = isElementPresentByWait(htmlelement);
		try {
			if (null == element) {
				WebElement loginFrame = driver.findElement(By.tagName("iframe"));
				driver.switchTo().frame(loginFrame);
				element = htmlelement;
				switchedToFrame = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != element) {
			int tries = 0;

			while (!(element.isDisplayed()) && tries < 100) {
				// page might still be loading and element is found but not yet displayed.
				// to prevent troubleshooting efforts, ensure the element is displayed before
				// input is
				// entered.
				try {

					Thread.sleep(10);
					tries++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getStackTrace().toString());
				}
			}

			try {
				// intermittently unable to enter the data for fields like password and confirm
				// password.
				// try click on the element text field before entering the input.
				element.click();
			} catch (Exception e) {

			}
			try {

				element.clear();
				element.sendKeys(value);
			} catch (Exception e) {
			}
		} else {
			System.out.println("Element not found: html element: " + htmlelement + ". so value could not be entered: " + value);
		}

		if (switchedToFrame == 1) {
			driver.switchTo().defaultContent();
		}

		return element;
	}
	public void click(WebElement element) {
		try {
			waitForElementVisibility(20,element);
			element.click();
		} catch (StaleElementReferenceException sere) {
			// simply retry finding the element in the refreshed DOM
			element.click();
		} catch (ElementClickInterceptedException interceptedEx) {
			scrollingToElementofAPage(element);
			element.click();
		} catch (TimeoutException toe) {
			System.out.println("Element identified by " + element.toString() + " was not clickable after 10 seconds");
			throw toe;
		}
	}
	
	public void waitForElementVisibility(long secondsToWait, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
		if (wait.until(ExpectedConditions.visibilityOfAllElements(element)) != null)
			System.out.println("element is visible" + element.toString().substring(element.toString().indexOf("->")));
		else
			System.out.println("element is not visible" + element.toString().substring(element.toString().indexOf("->")));
	}
	
	public void waitForElementVisiblitySize(long secondsToWait, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
		int size = wait.until(ExpectedConditions.visibilityOfAllElements(element)).size();
		if (size==0) {
			System.out.println("No elements found");
			
		}
	}
	
	public void scrollingToBottomofAPage() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void scrollingToElementofAPage(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public WebElement isElementPresentByWait(WebElement element) {
		

		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_UIELEMENT_WAIT_TIME);
		try {
			if (wait.until(ExpectedConditions.visibilityOfAllElements(element)) != null) {
				return element;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}
	
public boolean isElementVisisbleByWait(WebElement element) {
		

		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_UIELEMENT_WAIT_TIME);
		try {
			if (wait.until(ExpectedConditions.visibilityOfAllElements(element)) != null) {
				return true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return false;
	}
	
	 public String getElementText(WebElement element) {
		 waitForElementVisiblitySize(23, element);
		 String val = element.getText();
			if (null != val && !val.isEmpty()) {
				return val;
			}
			return element.getAttribute("value");
	 }

}
