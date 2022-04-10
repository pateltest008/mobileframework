package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.DriverManager;

public class BasePage {
	private AppiumDriver<MobileElement> driver;
	WebDriverWait wait;

	public BasePage() {
		this.driver = new DriverManager().getDriver();
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public FluentWait<WebDriver> WaitForElementVisibility(MobileElement element) {
		Wait<WebDriver> fwait = new FluentWait<WebDriver>(driver).withTimeout(40, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS).ignoring(Exception.class);
		fwait.until(ExpectedConditions.visibilityOf(element));
		return (FluentWait<WebDriver>) fwait;
	}

	public void SendKeys(MobileElement element, String text) {
		WaitForElementVisibility(element);
		Clear(element);
		element.sendKeys(text);
	}
		
	public void EnterDataInTextBox(MobileElement element, String data) {
		element.clear();
		driver.getKeyboard().sendKeys(data);
	}
	
	public void clickOnFieldWithText(String text) {
		MobileElement element = driver.findElement(By.xpath("//android.widget.TextView[@text=\'"+text+"\']"));
		WaitForElementVisibility(element);
		Click(element);
	}
	
	public void scrollAndClick(String visibleText) {
	     driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))")).click();
	    }

	public void Click(MobileElement element) {
		WaitForElementVisibility(element);
		Actions action = new Actions(driver);
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		action.moveToElement(element).click().perform();
	}

	public void Clear(MobileElement element) {
		WaitForElementVisibility(element);
		element.clear();
	}
}
