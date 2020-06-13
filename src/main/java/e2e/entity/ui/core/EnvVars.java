package e2e.entity.ui.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Collectors;

public class EnvVars {

	public static String DEFAULT_E2E_URL;
	public static String DEFAULT_USERNAME;
	public static String DEFAULT_PASSWORD;
	public static String BROWSER;
	private static Properties props = null;
	private static String configPropertyPath = "/config.properties";
	public static String carInput = "/car_input.txt";
	
	static {
		DEFAULT_E2E_URL = getExtProperty("DEFAULT_E2E_URL");
		DEFAULT_USERNAME = getExtProperty("DEFAULT_USERNAME");
		DEFAULT_PASSWORD = getExtProperty("DEFAULT_PASSWORD");
		BROWSER = getExtProperty("BROWSER");
	}
	
	private static String getExtProperty(String propName) {
		String prop = System.getenv(propName);
		EnvVars envVars = new EnvVars();
		if (prop == null || prop.equals("${" + propName + "}") || prop.isEmpty()) {
			System.out.println("Param '" + propName + "' is not defined at CI/CD job and = '" + prop
					+ "'. Getting value from config.properties file.");
			prop = envVars.getPropertyValue(propName);
			if (prop == null) {
				System.out.println("Param '" + propName + "' is missed (at CI/CD, "
						+ "config.properties) and will be set to null.");
			}
		}
		return prop;
	}
	
	public static String getPropertyValue(String propertyName) {
		try {
			props = getAllProperty(configPropertyPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return props.getProperty(propertyName);
	}
	
	public static Properties getAllProperty(String... fileNames) throws Exception {
		EnvVars envVars  = new EnvVars();
		if (props == null) {
			props = new Properties();
			for (String fileName : fileNames) {
				InputStream file = envVars.getFileFromResources(fileName);
				if (file != null) {
					props.load(file);
					System.out.println("File with name " + fileName + " Loaded");
				} else {
					System.out.println("No File with name " + fileName + " Loaded");
				}
			}
		}
		return props;
	}
	
	public InputStream getFileFromResources(String fileName) {
		return getClass().getResourceAsStream(fileName);

	}
	
	public String getFilePath(String fileName) {
		File file = new File(getClass().getClassLoader().getResource(fileName).getFile());
		return file.getAbsolutePath();
	}
	
	 public static String readFile(String pathToFile, String fileName) {
	        Path fullPath = Paths.get(pathToFile, fileName);

	        try {
	            String contentsOfFile = Files.lines(fullPath).collect(Collectors.joining("\n"));
	            System.out.println("Input File Path  " + fullPath.toString());
	            return contentsOfFile;
	        } catch (IOException e) {
	        	System.out.println(fullPath.toString());
	        }
	        return "";
	    }
}


