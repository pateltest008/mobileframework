package utils;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverManager {
	private static ThreadLocal<AppiumDriver<MobileElement>> driver = new ThreadLocal<>();
	TestUtils utils = new TestUtils();

	public AppiumDriver<MobileElement> getDriver() {
		return driver.get();
	}

	public void setDriver(AppiumDriver<MobileElement> driver2) {
		driver.set(driver2);
	}

	public void initializeDriver() throws Exception {

		AppiumDriver<MobileElement> driver = null;

		GlobalParams params = new GlobalParams();

		if (driver == null) {
			try {

				utils.log().info("initializing Appium driver");
				switch (params.getPlatformName()) {
				case "Android":
					driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),
							new CapabilitiesManager().getCaps());
					break;
				case "iOS":
					driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"),
							new CapabilitiesManager().getCaps());
					break;
				}
				if (driver == null) {
					throw new Exception("driver is null. ABORT!!!");
				}
				utils.log().info("Driver is initialized");
				this.driver.set(driver);
			} catch (IOException e) {
				e.printStackTrace();
				utils.log().fatal("Driver initialization failure. ABORT !!!!" + e.toString());
				throw e;
			}
		}

	}

}
