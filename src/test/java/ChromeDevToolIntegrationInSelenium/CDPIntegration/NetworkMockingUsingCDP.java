package ChromeDevToolIntegrationInSelenium.CDPIntegration;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v145.fetch.Fetch;
import org.openqa.selenium.devtools.v145.network.model.Request;

public class NetworkMockingUsingCDP {

	public static void main(String[] args) {
		ChromeDriver driver = new ChromeDriver();
		DevTools devToolObj = driver.getDevTools();
		devToolObj.createSession();

// Enables issuing of requestPaused events. A request will be paused until client calls one of failRequest, fulfillRequest or continueRequest/continueWithAuth.
		devToolObj.send(Fetch.enable(java.util.Optional.empty(), java.util.Optional.empty()));
// listen Fetch Domain's requestPaused event to capture the request & modify it
		devToolObj.addListener(Fetch.requestPaused(), request -> {
			Request req = request.getRequest();
			if (req.getUrl().contains("=shetty")) {
				String mockedURL = req.getUrl().replace("=shetty", "=BadGuy");

				// If found Continues the request, optionally modifying some of its parameters.
				devToolObj.send(Fetch.continueRequest(request.getRequestId(), java.util.Optional.of(mockedURL),
						java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty(),
						java.util.Optional.empty()));
			}else {
				// if not found continue original request
				devToolObj.send(
						Fetch.continueRequest(request.getRequestId(), Optional.of(req.getUrl()), java.util.Optional.empty(),
								java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty()));
			}

		});
		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
		driver.findElement(By.xpath("//a[@routerlink='/library']")).click();
	}

}
