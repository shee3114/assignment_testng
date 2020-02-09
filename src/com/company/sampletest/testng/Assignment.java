package com.company.sampletest.testng;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Assignment {
	WebDriver driver;

	// Launch Browser
	@BeforeSuite
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver", "/Users/maddy/stack/drv/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// Navigate to Automation Practice page 
	@Test(priority = 0)
	public void navivatToBrowserStackPage() {
		driver.navigate().to("http://automationpractice.com/index.php");
		WebElement signInButton = driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div/nav/div[1]/a"));
		signInButton.click();
	}

	// Create an account.
	@Test(priority = 1)
	public void createAccount() {
		WebElement enterEmail = driver.findElement(By.id("email_create"));
		enterEmail.sendKeys("maddysnehal@ggmaail.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement clickRegister = driver.findElement(By.xpath("//*[@id='SubmitCreate']/span"));
		clickRegister.click();

		WebElement selectTitle = driver.findElement(By.xpath("//*[@id='id_gender2']"));
		selectTitle.click();
		// Enter the First Name
		WebElement firstName = driver.findElement(By.xpath("//*[@id='customer_firstname']"));
		firstName.sendKeys("Sheetal");
		// Enter the Last Name
		WebElement lastName = driver.findElement(By.xpath("//*[@id='customer_lastname']"));
		lastName.sendKeys("Desai");
		// Set Password
		WebElement setPassword = driver.findElement(By.xpath("//*[@id='passwd']"));
		setPassword.sendKeys("qwerty@1234");

		// Set Address
		WebElement address = driver.findElement(By.xpath("//*[@id='address1']"));
		address.sendKeys("Mumbai 40079");

		WebElement city = driver.findElement(By.xpath("//*[@id='city']"));
		city.sendKeys("Navi Mumbai");

		Select state = new Select(driver.findElement(By.xpath("//*[@id='id_state']")));
		state.selectByVisibleText("California");

		WebElement postcode = driver.findElement(By.xpath("//*[@id='postcode']"));
		postcode.sendKeys("40079");

		WebElement phone = driver.findElement(By.xpath("//*[@id='phone_mobile']"));
		phone.sendKeys("7208489571");

		WebElement register = driver.findElement(By.xpath("//*[@id='submitAccount']/span"));
		register.click();

	}

	// Select Product from Woman Section
	@Test(priority = 2)
	public void selectOneProductFromWomanSection() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement womanSection = driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[1]/a"));
		womanSection.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,620)", "");

		Actions actions = new Actions(driver);
		WebElement menuOption = driver.findElement(By.xpath("//*[@id='center_column']/ul/li/div/div[1]/div/a[1]/img"));
		actions.moveToElement(menuOption).perform();

		WebElement quickView = driver.findElement(By.xpath("//a[@class='quick-view']"));
		quickView.click();
		Thread.sleep(5000);
		WebElement iframe = driver.findElement(By.className("fancybox-iframe"));
		driver.switchTo().frame(iframe);
		WebElement plusButton = driver.findElement(By.xpath("//*[@id='quantity_wanted']"));
		plusButton.clear();
		plusButton.sendKeys("2");
		WebElement  addToCardBtn = driver.findElement(By.xpath("//*[@id='add_to_cart']/button/span"));
		addToCardBtn.click();
		driver.switchTo().defaultContent();
	}


	//Complete Checkout Process and Payment Flow and Verify Total Amount
	@Test(priority = 4)
	public void checkoutAndVerifyAmount() throws InterruptedException {
		Thread.sleep(2000);
		WebElement  getTotalAmt = driver.findElement(By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[3]/span"));
		String totalAmount= getTotalAmt.getText();

		WebElement checkOutBtn = driver.findElement(By.xpath("//a[@title='Proceed to checkout']"));
		checkOutBtn.click();

		WebElement summaryCheckOutBtn = driver.findElement(By.xpath("//*[@id='center_column']/p[2]/a[1]/span"));
		summaryCheckOutBtn.click();

		WebElement addressCheckOutBtn = driver.findElement(By.xpath("//*[@id='center_column']/form/p/button/span"));
		addressCheckOutBtn.click();

		WebElement acceptTermsandCondition = driver.findElement(By.xpath("//*[@id='cgv']"));
		acceptTermsandCondition.click();

		WebElement shippingCheckOutBtn = driver.findElement(By.xpath("//*[@id='form']/p/button/span"));
		shippingCheckOutBtn.click();

		WebElement  getPaymentTotalAmt = driver.findElement(By.xpath("//*[@id='total_price']"));
		String totalAmt= getPaymentTotalAmt.getText();
		if(totalAmount.equals(totalAmt)){
			System.out.println("Amount Verfied");
		}
		else{
			System.out.println("Amount Incorrect");
		}

		WebElement paymentMethod = driver.findElement(By.xpath("//*[@id='HOOK_PAYMENT']/div[1]/div/p/a/span"));
		paymentMethod.click();

		WebElement confirmOrder = driver.findElement(By.xpath("//*[@id='cart_navigation']/button/span"));
		confirmOrder.click();

		WebElement orderConfMsg = driver.findElement(By.xpath("//*[@id='center_column']/div/p/strong"));
		System.out.println(orderConfMsg.getText());

	}

	//Verify the amount in Order History.
	@Test(priority = 5)
	public void amountVerificationInProfilePage() throws InterruptedException {

		Thread.sleep(3000);
		WebElement amount = driver.findElement(By.xpath("//span[@class='price']"));
		String totalAmt= amount.getText();

		WebElement profileName = driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div/nav/div[1]/a/span"));
		profileName.click();

		WebElement orderHistory = driver.findElement(By.xpath("//*[@id='center_column']/div/div[1]/ul/li[1]/a/span"));
		orderHistory.click();

		WebElement  verifyTotalAmount = driver.findElement(By.xpath("//*[@id='order-list']/tbody/tr[1]/td[3]/span"));
		String totalAmount= verifyTotalAmount.getText();
		if(totalAmount.equals(totalAmt)){
			System.out.println("Amount Verfied");
		}
		else{
			System.out.println("Amount Incorrect");
		}

	}


	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

}