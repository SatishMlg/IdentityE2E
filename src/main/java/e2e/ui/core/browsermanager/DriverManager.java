package e2e.ui.core.browsermanager;


import org.openqa.selenium.WebDriver;

public abstract class DriverManager {
	protected WebDriver driver;

	protected abstract void createDriver();

	/** Quit Driver in action */
	public void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	/**
	 * Return instance of active web driver
	 *
	 * @return Instance of Webdriver
	 */
	public WebDriver getDriver() {
		if (driver == null)
			createDriver();
		return driver;
	}
}

