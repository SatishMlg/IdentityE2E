package e2e.ui.core.browsermanager;

import e2e.ui.core.browsermanager.ChromeDriverManager;

public class DriverManagerFactory {

	/**
	 * Method to create driver based on browser type and return instance of Driver
	 * Manager
	 *
	 * @param driverType Enum - Type of browser like chrome or firefox
	 * @return Instance of Driver Manager based on browser type passed
	 */
	public static DriverManager getDriverManager(DriverType driverType) {
		DriverManager driverManager = null;
		switch (driverType) {
		case CHROME:
			driverManager = new ChromeDriverManager();
			break;
		case FIREFOX:
			//ToDo
			break;

		default:
			driverManager = getDriverManager(DriverType.CHROME);
			break;
		}
		return driverManager;
	}
}

