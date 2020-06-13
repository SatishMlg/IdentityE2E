package e2e.entity.ui.core;

import org.openqa.selenium.ElementClickInterceptedException;
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
	
	public void scrollingToBottomofAPage() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	
	public void scrollingToElementofAPage(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}
}
