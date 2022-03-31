package test1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class ReUsables {
	public WebDriver driver;
	  @BeforeClass
	  public void beforeClass() {
	  
		  System.setProperty("webdriver.chrome.driver", "C:\\Users\\kiran\\Downloads\\chromedriver_win32\\chromedriver.exe");
		  driver = new ChromeDriver();
  
	  }

	  @Test
	  //
	  public void test1() {
		  LoginPage();
		  displaydeviceinfo();	  
	  }
	  
	  //method to login to the application
	  public void LoginPage() {
		  driver.get("http://localhost:3001");
		  driver.manage().window().maximize();
	      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  WebElement listoptionsbox = driver.findElement(By.xpath("//div[@class='list-options-box']"));
		  if (listoptionsbox.isDisplayed()) {
			  Reporter.log("NinjaOne Device Page is displayed successfully" + "\n");
			  Reporter.log("The page title is : " + driver.getTitle() + "\n");
		  } else {
			  Reporter.log("NinjaOne Device Page is not displayed. So aborting test" + "\n");
			  driver.quit();
		  }
	  }
	
	  //method to retrieve the device list displayed
	  public void retrieveDeviceList() {	  
		  int listSize = driver.findElements(By.xpath("//div[@class='device-main-box']")).size();
		  if (listSize == 0){
			  Reporter.log("No devices are displayed in the page" + "\n"); 
			  return;
		  }else {
			  Reporter.log(listSize + " is the total number of devices displayed in the page" + "\n");
		  }
		  List<WebElement> listofdeviceNames = driver.findElements(By.xpath("//div[@class='device-main-box']//span[@class='device-name']"));
		  for(int i=0; i<listSize-1; i++) {
			  Reporter.log((listofdeviceNames.get(i).getText()) + "\n", true);			  		  
		  }	
	  }
	  
//	  method to display device info, capacity, type and if edit/delete buttons are displayed for all the devices
	  public void displaydeviceinfo() {
		List<WebElement> listofdeviceNames = driver.findElements(By.xpath("//div[@class='device-main-box']//span[@class='device-name']"));
		List<WebElement> listofdeviceTypes = driver.findElements(By.xpath("//div[@class='device-main-box']//span[@class='device-type']"));
		List<WebElement> listofdeviceCapacity = driver.findElements(By.xpath("//div[@class='device-main-box']//span[@class='device-capacity']"));
	
		for(int i=0; i<listofdeviceNames.size(); i++) {
			int n = i+1;
			  Reporter.log("The device no "+ n + " is displayed as " + (listofdeviceNames.get(i).getText()) + " of Type "+ (listofdeviceTypes.get(i).getText()) + " and capacity "+ (listofdeviceCapacity.get(i).getText()) + "\n", true);			  		  
		}
		checkforDevicesEditBtn();
		checkforDevicesDeleteBtn();		  
	  }
	  
	  //Method to check if device is visible in the UI and not hidden
	  public void checkfordevicevisible() {
		  List<WebElement> listofdeviceNames = driver.findElements(By.xpath("//div[@class='device-main-box']//span[@class='device-name']"));
		  for(int i=0; i<listofdeviceNames.size(); i++) {
			  if (!(listofdeviceNames.get(i)).isDisplayed()) {
				  Reporter.log((listofdeviceNames.get(i).getText()) + " is not displayed on the page"+ "\n", true);	
				  return;
			  }
		  }
		  Reporter.log("All the devices are displayed and visible on the page"+ "\n", true); 
	  }
	  
	  //Method to check if device edit button is displayed
	  public void checkforDevicesEditBtn() {
		  List<WebElement> deviceEditbtn = driver.findElements(By.className("device-edit"));
		  List<WebElement> listofdeviceNames = driver.findElements(By.xpath("//div[@class='device-main-box']//span[@class='device-name']"));
		  for(int i=0; i<listofdeviceNames.size(); i++) {
			  if (!(deviceEditbtn.get(i)).isDisplayed()) {
				  Reporter.log("Edit button for " + (listofdeviceNames.get(i).getText()) + " is not displayed"+ "\n", true);
				  return;
			  }		  
		  }	 
		  Reporter.log("Edit button is displayed for all the devices"+ "\n", true); 
		  
	  }
	  
	//Method to check if device Delete button is displayed
	  public void checkforDevicesDeleteBtn() {
		  List<WebElement> deviceDeletebtn = driver.findElements(By.className("device-edit"));
		  List<WebElement> listofdeviceNames = driver.findElements(By.xpath("//div[@class='device-main-box']//span[@class='device-name']"));
		  for(int i=0; i<listofdeviceNames.size(); i++) {
			  if (!(deviceDeletebtn.get(i)).isDisplayed()) {
				  Reporter.log("Remove button for " + (listofdeviceNames.get(i).getText()) + " is not displayed"+ "\n", true);
				  return;
			  }		  
		  }	 
		  Reporter.log("Remove button is displayed for all the devices"+ "\n", true); 		  
	  }
		
	  
	 
	  @AfterClass
	  public void afterClass() {
//		  driver.quit();
		  driver.close();
	  }

}
