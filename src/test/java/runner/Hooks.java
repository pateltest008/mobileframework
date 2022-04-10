package runner;

import java.io.IOException;

import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.Scenario;
import utils.DriverManager;
import utils.GlobalParams;

public class Hooks {

	@Before
	public void initialize() throws Exception {
		GlobalParams params = new GlobalParams();
		params.initializeGlobalParams();
		ThreadContext.put("ROUTINGKEY", params.getPlatformName() + "_" + params.getDeviceName());
		new DriverManager().initializeDriver();
	}

	@After
	public void quit(Scenario scenario) throws IOException {
		if (scenario.isFailed()) {
			byte[] screenshot = new DriverManager().getDriver().getScreenshotAs(OutputType.BYTES);
			scenario.embed(screenshot, "image/png");
		}

		DriverManager driverManager = new DriverManager();
		if (driverManager.getDriver() != null) {
			driverManager.getDriver().quit();
			driverManager.setDriver(null);
		}

	}
}
