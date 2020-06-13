package e2e.ui.core.browsermanager;

import java.util.Collections;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeDriverManager extends DriverManager {
	/** Method to create instance of chrome driver */
	protected void createDriver() {
		WebDriverManager.chromedriver().setup();

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		options.addArguments("ignore-certificate-errors");
		options.addArguments("--disable-single-click-autofill");
		options.addArguments("--ignore-autocomplete-off-autofill");

		options.setExperimentalOption("useAutomationExtension", false);
		options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
		System.setProperty("webdriver.chrome.silentOutput", "true"); 
		this.driver = new ChromeDriver(options);
	}
}

