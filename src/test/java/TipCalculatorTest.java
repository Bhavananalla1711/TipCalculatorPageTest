import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TipCalculatorTest {
    public static void main(String[] args){

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\BHAVANA\\Downloads\\chromedriver-win32-02-09-24 (2)\\chromedriver-win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        //Navigate to the URL https://qatipcalc.ccbp.tech/

        driver.get("https://qatipcalc.ccbp.tech/");

        //testing the tip calculation
        //Find the input element "BILL AMOUNT" - use starts with(CSS selector).
        WebElement billAmountEl = driver.findElement(By.cssSelector("input[id ^= 'bill']"));
        //Enter the bill amount as 1000
        billAmountEl.sendKeys("1000");
        //Find the input element "PERCENTAGE" - use starts with(CSS selector).
        WebElement percentageTipEl = driver.findElement(By.cssSelector("input[id ^= 'percent']"));
        //Enter the tip percentage as 12
        percentageTipEl.sendKeys("12");
        //Click on the "CALCULATE" button - use contains(CSS selector)
        WebElement calculateBtnEl = driver.findElement(By.cssSelector("button[ id ^= 'calculate']"));
        calculateBtnEl.click();
        //Verify the tip amount and total amount
        WebElement tipAmountEl = driver.findElement(By.cssSelector("p[id ^= 'tip']"));
        WebElement totalEl = driver.findElement(By.cssSelector("p[id ^= 'total']"));

        String expectedTipAmount = "120.00";
        String expectedTotalAmount = "1120.00";

        String currentTipAmount = tipAmountEl.getText();
        String currentTotalAmount = totalEl.getText();

        if(currentTipAmount.equals(expectedTipAmount) && currentTotalAmount.equals(expectedTotalAmount)){
            System.out.println("Tip Calculated Correctly.");
        }else{
            System.out.println("Tip Calculated Incorrectly");
        }

        //Testing the display of error message for no inputs.
        //Clear the "TIP PERCENTAGE" input element - use starts with(CSS selector)
        percentageTipEl.clear();
        //Click on the "CALCULATE" button
        calculateBtnEl.click();
        //Verify whether the error message is displayed or not
        WebElement errorMsgEl = driver.findElement(By.cssSelector("p[id ^= 'error']"));

        String expectedErrMsg = "Please Enter a Valid Input.";

        String currentErrMsg = errorMsgEl.getText();

        if(currentErrMsg.equals(expectedErrMsg)){
            System.out.println("Error message displayed for no input.");
        }else{
            System.out.println("Error message missing for no input.");
        }

        //Testing the display of error message for invalid inputs.
        //Enter the tip percentage as 10f
        percentageTipEl.sendKeys("10f");
        //Click on the "CALCULATE" button
        calculateBtnEl.click();

        currentErrMsg = errorMsgEl.getText();
        //Verify whether the error message is displayed or not - use ends with(CSS selector)
        if(currentErrMsg.equals(expectedErrMsg)){
            System.out.println("Error message displayed for invalid input.");
        }else{
            System.out.println("Error message missing for invalid input.");
        }

        driver.quit();


    }
}




