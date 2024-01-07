package runner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import sources.ExcelReader;
import sources.RowData;

import java.util.concurrent.TimeUnit;

public class TestRunner {

    private WebDriver driver;

    @Before
    public void setUp() {
        // Set up WebDriver (Make sure you have chromedriver installed and its path is set in system properties)
        System.setProperty("webdriver.chrome.driver", "C:/webdriver/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();

        // Open the URL
        driver.get("https://rpachallenge.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Click the visibleinnertext Start Button
        WebElement startButton = driver.findElement(By.xpath("//button[contains(text(),'Start')]"));
        startButton.click();
    }

    @Test
    public void testRpaChallenge() {
        // Read data from Excel and fill the form
        ExcelReader excelReader = new ExcelReader("C:/Users/PRASETYO BINTANG/IdeaProjects/rpachallenge/challenge.xlsx");
        while (excelReader.hasNext()) {
            fillForm(excelReader.getNextRow());
            submitForm();
        }
    }

    private void fillForm(RowData rowData) {
        // Locate elements and fill the form using visibleinnertext
        driver.findElement(By.xpath("//label[contains(text(),'First Name')]/following-sibling::input")).sendKeys(rowData.getFirstName());
        driver.findElement(By.xpath("//label[contains(text(),'Last Name')]/following-sibling::input")).sendKeys(rowData.getLastName());
        driver.findElement(By.xpath("//label[contains(text(),'Email')]/following-sibling::input")).sendKeys(rowData.getEmail());
        driver.findElement(By.xpath("//label[contains(text(),'Address')]/following-sibling::input")).sendKeys(rowData.getAddress());
        driver.findElement(By.xpath("//label[contains(text(),'Company Name')]/following-sibling::input")).sendKeys(rowData.getCompanyName());
        driver.findElement(By.xpath("//label[contains(text(),'Role in Company')]/following-sibling::input")).sendKeys(rowData.getRole());
        driver.findElement(By.xpath("//label[contains(text(),'Phone Number')]/following-sibling::input")).sendKeys(rowData.getPhoneNumber());
    }

    private void submitForm() {
        // Click Submit Button
        WebElement submitButton = driver.findElement(By.cssSelector("input[type='submit']"));
        submitButton.click();
    }

    @After
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
