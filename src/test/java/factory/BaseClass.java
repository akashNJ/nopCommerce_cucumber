package factory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.github.javafaker.Faker;

public class BaseClass {

	static Properties p;
	static WebDriver driver;
	static FileInputStream file;
	static FileOutputStream file1;
	public static WebDriver initializeBrowser() throws IOException {
		if(getProperties().getProperty("execution_env").equals("local")) {
			switch(getProperties().getProperty("browser")) {
			case "chrome": driver=new ChromeDriver();break;
			case "edge": driver=new EdgeDriver();break;
			default:System.out.println("No matching browser");
			driver= null;
			}

		}
		else if(getProperties().getProperty("execution_env").equals("remote")) {
			DesiredCapabilities cap=new DesiredCapabilities();
			switch(getProperties().getProperty("browser")) {
			case "chrome":cap.setBrowserName("chrome");break;
			case "edge":cap.setBrowserName("MicrosoftEdge");break;
			default: System.out.println("No matching browser found");
			driver=null;
			}
			switch(getProperties().getProperty("os")) {
			case "windows":cap.setPlatform(Platform.WINDOWS);break;
			case "mac":cap.setPlatform(Platform.MAC);break;
			default: System.out.println("No matching os found");
			}
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();

		return driver;
	}

	public static Properties getProperties() throws IOException {
		p=new Properties();
		file=new FileInputStream(".//src/test/resources/config.properties");
		p.load(file);
		return p;

	}

	public static void setProperties(String userName,String password) throws IOException {
		p.setProperty("userName", userName);
		p.setProperty("password", password);
		file1=new FileOutputStream(".//src/test/resources/config.properties");
		p.store(file1,"user details" );

	}


	public static WebDriver getDriver() {
		return driver;
	}

	public static  Logger logger() {
		Logger logs=LogManager.getLogger();
		return logs;
	}

	public static String randomString() {
		Faker fake=new Faker();
		String name=fake.name().firstName();
		return name;
	}

	public static String randomAlphaNumeric()
	{
		Faker fake=new Faker();
		String alphanumeric=fake.lorem().characters(8);
		return alphanumeric;
	}

}
