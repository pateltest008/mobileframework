package pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.junit.Assert;

public class HomePage extends BasePage {

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Search for Products, Brands and More']")
	private MobileElement SearchBox;
	
	@AndroidFindBy(id = "com.flipkart.android:id/cart_bg_icon")
	private MobileElement CartButton;
	
	@AndroidFindBy(id = "com.flipkart.android:id/search_autoCompleteTextView")
	private MobileElement MainSearchBox;
	
	
	public void AssertOnHomePage() {
		WaitForElementVisibility(CartButton);
		Assert.assertTrue(SearchBox.isDisplayed());
	}
	
	public void enterTextinTextBox(String text) {
		WaitForElementVisibility(SearchBox);
		Click(SearchBox);
		WaitForElementVisibility(MainSearchBox);
		Click(MainSearchBox);
		EnterDataInTextBox(SearchBox, text);
	}
}
