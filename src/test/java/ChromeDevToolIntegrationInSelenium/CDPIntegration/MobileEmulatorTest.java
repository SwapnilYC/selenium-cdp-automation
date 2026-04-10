package ChromeDevToolIntegrationInSelenium.CDPIntegration;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v145.emulation.Emulation;

public class MobileEmulatorTest {

	public static void main(String[] args) throws InterruptedException {
// CDP is supported by chromedriver not WebDriver
		ChromeDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
// Create an object of Devtool to access the CDP commands
		DevTools devToolObj = driver.getDevTools();

// Ceate an session to use CDP commands
		devToolObj.createSession();

// Send Commands to CDP
		// 1. Display as iphone screen (Never use maximize() with CDP emulation:-> Window commands override CDP device emulation settings)
		devToolObj.send(Emulation.setDeviceMetricsOverride(390, 844, 3, true, Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty(), Optional.empty()));
			// deviceScaleFactor ≠ zoom. Actually it controls pixel density, not layout
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		Thread.sleep(3000);
		driver.findElement(By.className("navbar-toggler-icon")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@routerlink='/library']")).click();
	}

}
