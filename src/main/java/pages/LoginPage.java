package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;
import java.util.Map;

//import junit.framework.Assert;
import org.junit.Assert;

public class LoginPage extends BasePage {

	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Log in']")
	private MobileElement LoginButton;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='English']")
	private MobileElement LanguageSelection;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='CONTINUE']")
	private MobileElement ContinueButton;
	
	@AndroidFindBy(className = "android.widget.EditText")
	private MobileElement PhoneInputTextBox;
	
	public void AssertOnLogin() {
		//Whenever sign in is required, there is a language selection checkbox available below code handles that
		scrollAndClick("English");
		Click(ContinueButton);
		WaitForElementVisibility(LoginButton);
		String elementText = LoginButton.getText();
		Assert.assertEquals("Log in", elementText);
	}
	
	public void enterMobileNumber(List<Map<String, String>> table) {
		String MobileNumber = table.get(0).get("MobileNumber");
		Click(PhoneInputTextBox);
		EnterDataInTextBox(PhoneInputTextBox, MobileNumber);
		clickOnFieldWithText("Continue");
	}
}
