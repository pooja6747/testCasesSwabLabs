package projectsGITHUB;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.*;

public class SwabLab {
	public String baseUrl = "https://www.saucedemo.com/";
	public WebDriver driver ; 

	@BeforeTest
	public void setup()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60)); 
	}


	//Test case 1: To verify the validation message without entering any credentials.
	@Test(priority = 1)
	public void loginWithoutCredentials() throws InterruptedException {
		driver.findElement(By.id("user-name")).sendKeys(" ");

		driver.findElement(By.id("password")).sendKeys(" ");

		driver.findElement(By.id("login-button")).click();

		WebElement errorMessageElement = driver.findElement(By.xpath("//h3[@data-test='error']"));
		String errorMessage = errorMessageElement.getText();

		System.out.println(errorMessage);
		Thread.sleep(5000);
	}


	// Test case 2: To verify the validation message on invalid username.
	@Test(priority = 2)
	public void loginWithInvalidUsername() throws InterruptedException {
		driver.findElement(By.id("user-name")).sendKeys("xyz");

		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();

		if (driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {
			System.out.println("Login successful!");

		}else {
			System.out.println("Login failed!");
		}

		Thread.sleep(5000);

	}


	// Test case 3: Verify user can login successfully
	@Test(priority = 3)
	public void testLogin() throws InterruptedException {
		System.out.println("ENter in testLogin");
		driver.findElement(By.id("user-name")).clear();
		driver.findElement(By.id("user-name")).sendKeys("standard_user");

		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("secret_sauce");

		driver.findElement(By.id("login-button")).click();

		if (driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {
			System.out.println("come in idfhdh");
			System.out.println("Login successful!");

		}else {
			System.out.println("Login failed!");
		}

		Thread.sleep(2000);
	}



	// Test case 4: Add an item to the cart
	@Test(priority = 4 )
	public void testAddToCart() throws InterruptedException {
		// After login, try to add a product to the cart
		WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
		addToCartButton.click();
		Thread.sleep(2000);

		// Verify item has been added to the cart
		WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
		Assert.assertEquals(cartBadge.getText(), "1", "Item  added to cart");

	}

	// Test case 3: Logout
	@Test(priority = 5)
	public void testLogout() throws InterruptedException {
		// Open menu and click logout
		WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
		menuButton.click();

		WebElement logoutButton = driver.findElement(By.id("logout_sidebar_link"));
		logoutButton.click();
		Thread.sleep(2000);

		// Verify login page is displayed
		WebElement loginButton = driver.findElement(By.id("login-button"));
		Assert.assertTrue(loginButton.isDisplayed(), "Login button is not displayed after logout");
		Thread.sleep(2000);
	}

	// Cleanup method to close the browser
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
