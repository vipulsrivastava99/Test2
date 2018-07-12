package com.qait.test2;

import org.testng.annotations.Test;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

public class TestQuestion2 {
	WebDriver driver;
	JavascriptExecutor js;
	private int invalidImageCount;

	/* public TestQuestion2(WebDriver driver) {
		// TODO Auto-generated constructor stub
		 js=(JavascriptExecutor)driver;
	}*/
	@BeforeMethod
	public void openTheFirstPage() {
		driver = new ChromeDriver();
		driver.get("http://10.0.31.161:9292/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		Reporter.log("Launched the application:" + driver.getTitle());
	//	TestQuestion2 o=new TestQuestion2(driver);
	}
/*
	@Test
	public void TestCase5_FormAuthenticationInvalidCredentials() {
		String username="sdx";
		String password="ddwq";
		driver.findElement(By.linkText("Form Authentication")).click();
		
		Reporter.log("User clicked on Form Authentication",true);
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		Reporter.log("Username and Password Values sent- "+username+" and "+password);
		js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('i').click();");
		Reporter.log("User clicked on Login Button");
		String invalidMsg = (String) js.executeScript("return document.getElementById('flash').textContent;");
		invalidMsg = invalidMsg.trim();
		System.out.println(invalidMsg);
		invalidMsg=invalidMsg.replace("×","");
		invalidMsg=invalidMsg.replace("\n","");
		invalidMsg = invalidMsg.trim();

		Assert.assertEquals(invalidMsg,"Your username is invalid!","Assertion Failed : Can't find Message:Your username is invalid!");
		Reporter.log("Assertion Passed : Message:Your username is invalid! is displayed", true);
	}


	@Test
	public void TestCase6_FormAuthenticationValidCredentials() throws InterruptedException {
		 js=(JavascriptExecutor)driver;
		driver.findElement(By.linkText("Form Authentication")).click();
		Reporter.log("Clicked on Form Authentication");
		String heading2 = (String) js.executeScript("return document.querySelector('h2').textContent");
		System.out.println(heading2);

		Assert.assertEquals(heading2, "Login Page");
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		Reporter.log("Username and Password Values sent ('tomsmith' and 'SuperSecretPassword!')");
		js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('i').click();");
		Reporter.log("User clicked on Login Button");
		String heading = (String) js.executeScript("return document.querySelector('h2').textContent");
		Assert.assertEquals(heading, " Secure Area","Secure area page is not opened");
		Reporter.log("Secure area page is opened");
		js.executeScript("document.querySelector('a.button').click();");
		String logout = ((String) js.executeScript("return document.getElementById('flash').textContent;"));
		logout = logout.replace("\n", "").trim();
		logout = logout.replace("×", "").trim();
		System.out.println(logout);
		Assert.assertEquals(logout, "You logged out of the secure area!","Unable to logout");
		Reporter.log("Successfully Logged out");
	}
	
	@Test
	public void TestCase3_ExitIntent() throws AWTException, InterruptedException {
		driver.findElement(By.linkText("Exit Intent")).click();
		Reporter.log("User clicked on Exit Intent");
		js=(JavascriptExecutor)driver;
		String heading1 = (String) js.executeScript("return document.querySelector('h3').textContent");
		Assert.assertEquals(heading1, "Exit Intent", "Assertion Failed : Exit Intent page is not displayed");
		Reporter.log("Exit Intent page is opened");
		Robot robot = new Robot();
		robot.mouseMove(400, 400);
		robot.mouseMove(0, 0);
		String modalWindow = ((String) js.executeScript("return document.querySelectorAll('h3')[1].textContent;"))
				.trim();
		Assert.assertTrue(modalWindow.equalsIgnoreCase("THIS IS A MODAL WINDOW"),
				"Assertion Failed : Modal window is'nt opened");
		Reporter.log("Modal window is opened");
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#ouibounce-modal > div.modal > div.modal-footer > p")).click();
		heading1 = (String) js.executeScript("return document.querySelector('h3').textContent");
		Assert.assertEquals(heading1, "Exit Intent", "Assertion Failed : Modal window is not closed");
		Reporter.log("User clicked on close button and Modal window is closed");
	}

	@Test
	public void TestCase2_BrokenImages() {
		driver.findElement(By.linkText("Broken Images")).click();
		try {
			invalidImageCount = 0;
			List<WebElement> imagesList = driver.findElements(By.tagName("img"));
			Reporter.log("Got list of images");
			System.out.println("Total no. of images are " + imagesList.size());
			
			for (WebElement imgElement : imagesList) {
				if (imgElement != null) {
					verifyimageActive(imgElement);
				}
			}
			System.out.println("Total no. of invalid images are " + invalidImageCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public void verifyimageActive(WebElement imgElement) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
			HttpResponse response = client.execute(request);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode() != 200)
				invalidImageCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void TestCase7_wyswygEditor() {

		driver.findElement(By.linkText("WYSIWYG Editor")).click();
		Reporter.log("User clicked on wysiwyg Button");
		js=(JavascriptExecutor)driver;
		String heading = (String) js.executeScript("return document.querySelector('h3').textContent");
		Assert.assertEquals(heading, "An iFrame containing the TinyMCE WYSIWYG Editor");
		Reporter.log("editor page is opened");
		driver.switchTo().frame("mce_0_ifr");
		Reporter.log("Switched to text field frame");
		driver.findElement(By.xpath("//*[@id=\"tinymce\"]/p")).clear();
		Reporter.log("Written text is cleared");
		driver.findElement(By.id("tinymce")).sendKeys("QA Infotech");
		Reporter.log("QA infotech is written");
		driver.findElement(By.id("tinymce")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
		Reporter.log("user pressed ctrl+ a on keyboard");
		driver.switchTo().defaultContent();
		Reporter.log("Switched to the default frame");
		driver.findElement(By.xpath("//*[@id=\"mceu_3\"]/button/i")).click();
		Reporter.log("User clicked on bold button");
		driver.switchTo().frame("mce_0_ifr");
		Reporter.log("Again switched to inner frame");
		String Expected = "QA Infotech";
		String Actual = driver.findElement(By.xpath("//*[@id=\"tinymce\"]/p/strong")).getText();
		System.out.println(Actual);
		Assert.assertEquals(Actual, Expected);
		Reporter.log(" Text entered in text editor now appears in Bold style.");
	}

	@Test
	public void TestCase4_SortedDataTables() {

		driver.findElement(By.linkText("Sortable Data Tables")).click();
		Reporter.log("User clicked on Sorted data tables");
		 js=(JavascriptExecutor)driver;
		String heading = (String) js.executeScript("return document.querySelector('h3').textContent");
		Assert.assertEquals(heading, "Data Tables");
		Reporter.log("Data Tables page is opened");
		driver.findElement(By.xpath("//span[text()='Due']")).click();
	}
	@Test
	public void TestCase6_HoverImage() {

		driver.findElement(By.xpath("//*[text()[contains(.,'Hovers')]]")).click();
		Reporter.log("User clicked on Hover link");
		String text = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
		Assert.assertEquals(text, "Hovers");
		Reporter.log("Hover page is opened");
		List<WebElement> listImages = driver.findElements(By.className("figure"));
		Reporter.log("User Got all images");
		System.out.println("No. of Images: " + listImages.size());

		Assert.assertEquals(listImages.size(), 3);
		Reporter.log("Number of images are 3");
		WebElement web_Element_To_Be_Hovered = driver
				.findElement(By.cssSelector("#content > div > div:nth-child(3) > img"));
		Actions builder = new Actions(driver);
		builder.moveToElement(web_Element_To_Be_Hovered).build().perform();
		Reporter.log("User Hovered over first image");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//h5[contains(text(),'user1')]")).isDisplayed(), true,
				"Name is not present");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//h5[contains(text(),'user1')]/parent::div//a")).isDisplayed(), true,
				"View profile is not present");
		Reporter.log("Name and user profile appeared");
		driver.navigate().refresh();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/h5")).isDisplayed(), false,
				"Name is present");
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a")).isDisplayed(), false,
				"View profile is present");
		Reporter.log("Name and user profile disappeared");

	}*/
	@Test
	public void TestCase8_StatusCodes() {

		driver.findElement(By.linkText("Status Codes")).click();
		
		Reporter.log("User clicked on Status codes");
		String text=driver.findElement(By.xpath("//*[@id=\"content\"]/div/h3")).getText();
		Assert.assertEquals(text,"Status Codes");
		Reporter.log("Status code page is opened");
		driver.findElement(By.xpath("//div[@id='content']/div/ul/li[3]/a")).click();
		Reporter.log("User Clicked on 404");
		String s=driver.findElement(By.tagName("p")).getText();
		String s2=s.substring(0,37);
		String s1="This page returned a 404 status code.";
		
		Assert.assertEquals(s2, s1);
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(driver.getCurrentUrl());
			HttpResponse response = client.execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			Assert.assertTrue(response.getStatusLine().getStatusCode() == 404, "[ASSERTION FAILED]: Status Code is not 404");
			Reporter.log("[ASSERTION PASSED]: As expected status code is 404.", true);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
		//	if (response.getStatusLine().getStatusCode() != 200)
			///	invalidImageCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Reporter.log("HTTP Status code of the page is 404");
	}
	
	@AfterMethod
	public void closeBrowser() {
		Reporter.log("Closed the browser", true);
		driver.close();
	}
}
