package e2e.entity.ui.carValueTests;

import org.testng.annotations.Test;

import e2e.entity.ui.core.EntityTestBase;
import e2e.entity.ui.core.EnvVars;

public class CompareCarValuesTests extends EntityTestBase{
	
	public static String url = EnvVars.DEFAULT_E2E_URL;
	@Test(description = "Login with Valid Credentials")
	public void loginToPortal() throws Exception {
		driver.get(url);
	}
}
