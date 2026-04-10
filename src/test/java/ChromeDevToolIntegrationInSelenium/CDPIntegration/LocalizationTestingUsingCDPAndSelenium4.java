package ChromeDevToolIntegrationInSelenium.CDPIntegration;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import org.openqa.selenium.devtools.v145.emulation.Emulation;

public class LocalizationTestingUsingCDPAndSelenium4 {

	public static void main(String[] args) throws InterruptedException {
		// Here we twick the location so that we can test the website based on location
		ChromeDriver driver = new ChromeDriver();
		DevTools devToolObj = driver.getDevTools();
		devToolObj.createSession();
		driver.manage().window().maximize();

		// Set location
		double lat = 17;   //-23.55618;
		double lon = 78;   //-46.63921;
		devToolObj.send(Emulation.setGeolocationOverride(Optional.of(lat), // latitude
				Optional.of(lon), // longitude
				Optional.of(1), // accuracy
				Optional.empty(), // altitude
				Optional.empty(), // altitudeAccuracy
				Optional.empty(), // heading
				Optional.empty() // speed
		));

		// Go to netflix
		driver.get("https://www.google.com");
		driver.findElement(By.name("q")).sendKeys("Netflix", Keys.ENTER);  // reCAPTCH issue
		devToolObj.disconnectSession();
	
		
	}

}
