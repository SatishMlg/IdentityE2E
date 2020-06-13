package e2e.entity.ui.core;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeClass;

import e2e.ui.core.browsermanager.DriverManager;
import e2e.ui.core.browsermanager.DriverManagerFactory;
import e2e.ui.core.browsermanager.DriverType;

public class EntityTestBase extends HtmlOps{
	public static String BROWSER = EnvVars.BROWSER;
	
	@BeforeClass
	public void initDriver() {
		System.out.println("Initialising WebDriver");
		try {
			DriverType driverType = BROWSER.toUpperCase().equals("CHROME") ? DriverType.CHROME : DriverType.FIREFOX;

			DriverManager driverManager = DriverManagerFactory.getDriverManager(driverType);
			driver = driverManager.getDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(DEFAULT_UIELEMENT_WAIT_TIME, TimeUnit.SECONDS);

		} catch (NullPointerException ex) {
			throw new RuntimeException("Web driver could not be initialised for device ");
		}
	}

}
