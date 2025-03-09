package com.selenium.loginpage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrangeHRM_Login_Automation {
	static WebDriver driver;
	static WebElement loginBtn;
	static WebElement usernameRequiredError;
	static WebElement passwordRequiredError;
	static WebElement usernameTextbox;
	static WebElement passwordTextbox;
	static WebElement InvalidCredError;

	public static void setup() throws InterruptedException
	{
		// Invoke the Browser.
		driver = new ChromeDriver();
		// Redirect to OrangeHRM web-site.
		String url = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
		driver.get(url);
		Thread.sleep(6000);
	}

	// Test case 1 : Sign in with blank username and blank password fields.
	public static void Blank_Login_Test() {
		// Click on Login button.
		loginBtn = driver.findElement(By.cssSelector("[class*='oxd-button']"));
		loginBtn.click();
		// Verify error message is displayed.
		usernameRequiredError = driver.findElement(By.xpath("//input[@name='username']/parent::div/following-sibling::span"));
		passwordRequiredError = driver.findElement(By.xpath("//input[@name='password']/parent::div/following-sibling::span"));
		usernameRequiredError.isDisplayed();
		passwordRequiredError.isDisplayed();
		// Compare actual error message with the expected error message.
		assertEquals(usernameRequiredError.getText(), "Required");
		assertEquals(passwordRequiredError.getText(), "Required");

		System.out.println("Test case 1 success.");
	}

	// Test case 2 : Sign in with Invalid username and blank password field. 
	public static void Invalid_Username_Test() {
		// Enter Invalid username
		usernameTextbox = driver.findElement(By.xpath("//input[@name='username']"));
		usernameTextbox.sendKeys("Invalid username");
		// Click on Login button.
		loginBtn.click();
		// Verify error message is displayed for blank password field.
		passwordRequiredError.isDisplayed();
		// Compare actual error message with the expected error message.
		assertEquals(passwordRequiredError.getText(), "Required");

		System.out.println("Test case 2 success.");
	}

	// Test case 3 : Sign in with valid username and blank password field.
	public static void Valid_Username_Test() throws InterruptedException {
		// clear username field data.
		usernameTextbox.sendKeys(Keys.CONTROL + "a");
		usernameTextbox.sendKeys(Keys.BACK_SPACE);
		// Enter valid username
		usernameTextbox.sendKeys("Admin");
		// Click on Login button.
		loginBtn.click();
		// Verify error message is displayed for blank password field.
		passwordRequiredError.isDisplayed();
		// Compare actual error message with the expected error message.
		assertEquals(passwordRequiredError.getText(), "Required");

		System.out.println("Test case 3 success.");
	}

	// Test case 4 : Sign in with blank username and invalid password field. 
	public static void Invalid_Password_Test() throws InterruptedException {
		// Clear username field data.
		usernameTextbox.sendKeys(Keys.CONTROL + "a");
		usernameTextbox.sendKeys(Keys.BACK_SPACE);
		// Enter invalid password.
		passwordTextbox = driver.findElement(By.xpath("//input[@name='password']"));
		passwordTextbox.sendKeys("Invalid password");
		//Click on Login button.
		loginBtn.click();
		//Locate username field again because it get refreshed in the dom.
		usernameRequiredError = driver.findElement(By.xpath("//input[@name='username']/parent::div/following-sibling::span"));
		// Verify error message is displayed for blank user-name field.
		usernameRequiredError.isDisplayed();
		//Compare actual error message with the expected error message.
		assertEquals(usernameRequiredError.getText(), "Required");

		System.out.println("Test case 4 success.");
	}

	// Test case 5 : Sign in with blank username field and valid password.
	public static void Valid_Password_Test() throws InterruptedException {
		// Clear password field data.
		passwordTextbox.sendKeys(Keys.CONTROL + "a");
		passwordTextbox.sendKeys(Keys.BACK_SPACE);
		// Enter valid password.
		passwordTextbox.sendKeys("admin123");
		// Click on login button.
		loginBtn.click();
		//Verify error message is displayed for blank user-name field.
		usernameRequiredError.isDisplayed();
		//Compare actual error message with the expected error message.
		assertEquals(usernameRequiredError.getText(), "Required");

		System.out.println("Test case 5 success.");
	}

	// Test case 6 : Sign in with Invalid username and valid password. 
	public static void InvalidUser_ValidPwd_Test() throws InterruptedException {
		// Clear password field data.
		passwordTextbox.sendKeys(Keys.CONTROL + "a");
		passwordTextbox.sendKeys(Keys.BACK_SPACE);
		// Enter Invalid username.
		usernameTextbox.sendKeys("Invalid username");
		// Enter valid password.
		passwordTextbox.sendKeys("admin123");
		// Click on login button.
		loginBtn.click();
		Thread.sleep(8000);
		// Verify error message is displayed for Invalid credentials.
		WebElement InvalidCredError = driver.findElement(By.cssSelector("p[class*='oxd-alert-content-text']"));
		InvalidCredError.isDisplayed();
		// Compare actual error message with the expected error message.
		assertEquals(InvalidCredError.getText(), "Invalid credentials");

		System.out.println("Test case 6 success.");
	}

	// Test case 7 : Sign in with valid username and Invalid password.
	public static void ValidUser_InvalidPwd_Test() throws InterruptedException {
		// Enter valid username.
		usernameTextbox = driver.findElement(By.xpath("//input[@name='username']"));
		usernameTextbox.sendKeys("admin");
		// Enter Invalid password.
		passwordTextbox = driver.findElement(By.xpath("//input[@name='password']"));
		passwordTextbox.sendKeys("Invalid Password");
		// Click on login button.
		loginBtn = driver.findElement(By.cssSelector("[class*='oxd-button']"));
		loginBtn.click();
		Thread.sleep(8000);
		// Verify error message is displayed for Invalid credentials.
		InvalidCredError = driver.findElement(By.cssSelector("p[class*='oxd-alert-content-text']"));
		InvalidCredError.isDisplayed();
		// Compare actual error message with the expected error message.
		assertEquals(InvalidCredError.getText(), "Invalid credentials");

		System.out.println("Test case 7 success.");
	}

	// Test case 8 : Sign in with Invalid username and Invalid password.
	public static void InvalidUser_InvalidPwd_Test() throws InterruptedException {
		// Enter Invalid username.
		usernameTextbox = driver.findElement(By.xpath("//input[@name='username']"));
		usernameTextbox.sendKeys("Invalid Username");
		// Enter Invalid password.
		passwordTextbox = driver.findElement(By.xpath("//input[@name='password']"));
		passwordTextbox.sendKeys("Invalid Password");
		// Click on login button.
		loginBtn = driver.findElement(By.cssSelector("[class*='oxd-button']"));
		loginBtn.click();
		Thread.sleep(8000);
		// Verify error message is displayed for Invalid credentials.
		InvalidCredError = driver.findElement(By.cssSelector("p[class*='oxd-alert-content-text']"));
		InvalidCredError.isDisplayed();
		// Compare actual error message with the expected error message.
		assertEquals(InvalidCredError.getText(), "Invalid credentials");

		System.out.println("Test case 8 success.");
	}

	// Test case 9 : Sign in with valid username and valid password.
	public static void ValidUser_ValidPwd_Test() throws InterruptedException {
		// Enter valid username.
		usernameTextbox = driver.findElement(By.xpath("//input[@name='username']"));
		usernameTextbox.sendKeys("Admin");
		// Enter valid password.
		passwordTextbox = driver.findElement(By.xpath("//input[@name='password']"));
		passwordTextbox.sendKeys("admin123");
		// Click on login button.
		loginBtn = driver.findElement(By.cssSelector("[class*='oxd-button']"));
		loginBtn.click();
		Thread.sleep(8000);
		// Verify successful login & page is redirected to dashboard.
		WebElement dashboardHeader = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
		assertTrue(dashboardHeader.isDisplayed());
		WebElement dashboardMenu = driver.findElement(By.xpath("//a[contains(@href,'dashboard')]"));
		assertTrue(dashboardMenu.isDisplayed());

		System.out.println("Test case 9 success.");
	}

	// Test case 10 : Verify logout.
	public static void Logout_Test() throws InterruptedException {
		// Click on profile drop-down.
		WebElement profileDropdown = driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']"));
		profileDropdown.click();
		Thread.sleep(2000);
		// Click on logout button.
		WebElement logoutButton = driver.findElement(By.xpath("//a[text()='Logout']")); 
		logoutButton.click();
		Thread.sleep(8000);
		// Verify redirection to login page.
		WebElement loginTitle = driver.findElement(By.cssSelector("[class*='orangehrm-login-title']"));
		assertEquals(loginTitle.getText(), "Login");

		System.out.println("Test case 10 success.");
	}

	public static void teardown()
	{
		driver.quit();
	}

	public static void main(String[] args) throws InterruptedException {
		setup();
		Blank_Login_Test();
		Invalid_Username_Test();
		Valid_Username_Test();
		Invalid_Password_Test();
		Valid_Password_Test();
		InvalidUser_ValidPwd_Test();
		ValidUser_InvalidPwd_Test();
		InvalidUser_InvalidPwd_Test();
		ValidUser_ValidPwd_Test();
		Logout_Test();
		teardown();
	}

}