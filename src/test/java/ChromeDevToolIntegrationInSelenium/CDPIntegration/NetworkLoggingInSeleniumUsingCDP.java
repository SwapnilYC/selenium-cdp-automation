package ChromeDevToolIntegrationInSelenium.CDPIntegration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v145.network.Network;
import org.openqa.selenium.devtools.v145.network.model.Request;
import org.openqa.selenium.devtools.v145.network.model.Response;



public class NetworkLoggingInSeleniumUsingCDP {

	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		DevTools devToolObj = driver.getDevTools();
		devToolObj.createSession();

		// Enables network tracking, network events will now be delivered to the client.
		devToolObj.send(Network.enable(java.util.Optional.empty(), java.util.Optional.empty(),
				java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty()));
		
		// capture API request details before sending to server
		devToolObj.addListener(Network.requestWillBeSent(), request -> {
			Request req = request.getRequest();
			System.out.println("Request sent to: "+req.getUrl());
		});
		
		// capture API response details (status code, URL)
		devToolObj.addListener(Network.responseReceived(), response -> {
			Response res = response.getResponse();
			if(res.getStatus().toString().contains("4")) {
				System.out.println(res.getUrl() + " is failing with "+res.getStatus());
			}
			
		});
		
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("//a[@routerlink='/library']")).click();
	}
}
