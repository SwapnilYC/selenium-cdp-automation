package ChromeDevToolIntegrationInSelenium.CDPIntegration;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

public class CustomCDPFunction {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();
		DevTools devToolObj =driver.getDevTools();
		devToolObj.createSession();
		
		// Instead of using : devToolObj.send(command) we will use custom CDP function
		String commandString = "Emulation.setDeviceMetricsOverride";
		Map <String, Object> deviceMetrics = new HashMap<>();
		deviceMetrics.put("width", 600);
		deviceMetrics.put("height", 1000);
		deviceMetrics.put("deviceScaleFactor", 3);
		deviceMetrics.put("mobile", true);
		driver.executeCdpCommand(commandString, deviceMetrics);
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		Thread.sleep(3000);
		driver.findElement(By.className("navbar-toggler-icon")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@routerlink='/library']")).click();

	}

}
