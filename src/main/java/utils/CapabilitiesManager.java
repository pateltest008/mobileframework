package utils;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.remote.MobileCapabilityType;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class CapabilitiesManager {
	TestUtils utils = new TestUtils();

	public DesiredCapabilities getCaps() throws IOException {
		GlobalParams params = new GlobalParams();
		Properties props = new PropertyManager().getProps();

		try {
			utils.log().info("getting capabilities");
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, params.getPlatformName());
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, params.getDeviceName());

			switch (params.getPlatformName()) {
			case "Android":
				caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,
						props.getProperty("androidAutomationName", "uiautomator2"));
				caps.setCapability("autoGrantPermissions", "true");
				caps.setCapability("appPackage", "com.flipkart.android");
				caps.setCapability("appActivity", "com.flipkart.android.activity.HomeFragmentHolderActivity");
				caps.setCapability("autoAcceptAlerts", true);
				;
				break;
			case "iOS":
				caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("iOSAutomationName"));
				String iOSAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
						+ File.separator + "resources" + File.separator + "apps" + File.separator
						+ "SwagLabsMobileApp.app";
				utils.log().info("appUrl is" + iOSAppUrl);
				caps.setCapability("bundleId", props.getProperty("iOSBundleId"));
				caps.setCapability("wdaLocalPort", params.getWdaLocalPort());
				caps.setCapability("webkitDebugProxyPort", params.getWebkitDebugProxyPort());
				caps.setCapability("app", iOSAppUrl);
				break;
			}
			return caps;
		} catch (Exception e) {
			e.printStackTrace();
			utils.log().fatal("Failed to load capabilities. ABORT!!" + e.toString());
			throw e;
		}
	}
}
