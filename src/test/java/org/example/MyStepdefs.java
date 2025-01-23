package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class MyStepdefs {
    WebDriver driver=new ChromeDriver();
    @Given("open the form")
    public void openTheForm()
    {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

//        driver.navigate().to("");
        try {
            driver.get("https://demoqa.com/automation-practice-form");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement ad = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@style=\"border: 1px solid rgb(229, 229, 229); margin-top: 50px; padding: 50px;\"]")
            ));

            System.out.println("Element loaded: " + ad.getText());

        } catch (Exception e) {
            System.out.println("Element not found within the specified time: " + e.getMessage());
        }
//        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
//        WebElement ad = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[starts-with(@id,'google_ads_iframe')])[3]")));
//        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1700, 1080));

    }

    @When("Enter the all the required values {string},{string},{string},{string} and click submit")
    public void enterTheAllTheRequiredValuesAndClickSubmit(String firstname, String lastname, String gender, String mobile)
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        WebElement firstName = driver.findElement(By.xpath("//input[@id='firstName']"));
        firstName.sendKeys(firstname);

        WebElement lastName = driver.findElement(By.xpath("//input[@id='firstName']/parent::div/following-sibling::div/child::input[@id='lastName']"));
        lastName.sendKeys(lastname);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");

        WebElement genderValue = driver.findElement(By.xpath("//div[@class='col-md-9 col-sm-12']/descendant::label[contains(text(),'"+gender+"')]"));
        genderValue.click();

        WebElement mobileNo = driver.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
        mobileNo.sendKeys(mobile);
        WebElement submit=driver.findElement(By.xpath("//div[@class='text-right col-md-2 col-sm-12']/descendant::button[contains(text(),'Submit')]"));
        js.executeScript("window.scrollBy(0,350)", "");
        Actions action=new Actions(driver);

        WebElement ad = driver.findElement(By.xpath("//div[@id='Ad.Plus-970x250-2']"));
        action.scrollToElement(ad).perform();
        submit.click();

    }

    @Then("verify the values {string},{string},{string},{string} filled are correct")
    public void verifyTheValuesFilledAreCorrect(String firstname, String lastname, String gender, String mobile)
    {
        WebElement submission=driver.findElement(By.xpath("//div[contains(text(),'Thanks for submitting the form')]"));
        Assert.assertTrue(submission.isDisplayed()," Form is submitted Successfully");

        String fullname=firstname+" "+lastname;
        WebElement name= driver.findElement(By.xpath("//td[contains(text(),'Student Name')]/following-sibling::td"));
        String namegot=name.getText();
        Assert.assertEquals(namegot,fullname,"name mismatch");
        WebElement getgender=driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td"));
        String genderGot=getgender.getText();
        Assert.assertEquals(genderGot,gender,"gender mismatch");
        WebElement getmobile=driver.findElement(By.xpath("//td[contains(text(),'Mobile')]/following-sibling::td"));
        String mobileGot=getmobile.getText();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");

        Assert.assertEquals(mobileGot,mobile);
        System.out.println("Test case passed : Form submitted successfully when all the required fields are filled");
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
        driver.close();
    }


    @When("Enter the field values {string},{string},{string},{string},{string}")
    public void enterTheFieldValues(String firstname, String lastname, String email, String gender, String mobile)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='firstName']")));
        firstName.sendKeys(firstname);

        WebElement lastName = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='firstName']/parent::div/following-sibling::div/child::input[@id='lastName']")));
        lastName.sendKeys(lastname);

        Actions action = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");

        WebElement email1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userEmail")));
        action.moveToElement(email1).perform();
        email1.sendKeys(email);

        WebElement genderValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='col-md-9 col-sm-12']/descendant::label[contains(text(),'" + gender + "')]")));
        genderValue.click();

        WebElement mobileNo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Mobile Number']")));
        mobileNo.sendKeys(mobile);

        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='text-right col-md-2 col-sm-12']/descendant::button[contains(text(),'Submit')]")));
        js.executeScript("window.scrollBy(0,350)", "");
        WebElement ad = driver.findElement(By.xpath("//div[@id='Ad.Plus-970x250-2']"));

//        WebElement ad = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[starts-with(@id,'google_ads_iframe')])[3]")));
        action.scrollToElement(ad).perform();
        submit.click();
    }





    @Then("validate the form with these values {string},{string},{string},{string},{string}")
    public void validateTheFormWithTheseValues(String firstname, String lastname, String email, String gender, String mobile)
    {
        try {

            WebElement emailField = driver.findElement(By.xpath("//input[@placeholder='name@example.com']"));
            String enteredEmail = emailField.getAttribute("value");
            String emailRegex = "^[\\w-]+@[\\w-]+\\.[a-z]{2,4}$";
            boolean isEmailValid = enteredEmail.matches(emailRegex);
            Assert.assertTrue(isEmailValid, "Email format is invalid: " + enteredEmail);

            WebElement mobileField = driver.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
            String enteredMobile = mobileField.getAttribute("value");
            String numberRegex = "\\d+";
            boolean isValidNumber = enteredMobile.matches(numberRegex);
            Assert.assertTrue(isValidNumber, "Mobile number contains invalid characters: " + enteredMobile);
            int expectedLength = 10;
            Assert.assertEquals("Mobile number length is invalid: " + enteredMobile, expectedLength, String.valueOf(enteredMobile.length()));
        }
        catch (AssertionError e)
        {
            System.out.println("Assertion failed: " + e.getMessage());
        }
        finally
        {
            driver.quit();
        }
    }


    @When("Enter the values {string},{string},{string},{string},{string},{string},{string},{string},{string},{string} and submit")
    public void enterTheValuesAndSubmit(String firstname, String lastname, String email, String gender, String mobile, String month, String year, String date, String hobby1, String address)
    {
        WebElement firstName = driver.findElement(By.xpath("//input[@id='firstName']"));
        firstName.sendKeys(firstname);

        WebElement lastName = driver.findElement(By.xpath("//input[@id='firstName']/parent::div/following-sibling::div/child::input[@id='lastName']"));
        lastName.sendKeys(lastname);

        WebElement mail=driver.findElement(By.xpath("//input[@placeholder='name@example.com']"));
        mail.sendKeys(email);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");

        WebElement genderValue = driver.findElement(By.xpath("//div[@class='col-md-9 col-sm-12']/descendant::label[contains(text(),'"+gender+"')]"));
        genderValue.click();

        WebElement mobileNo = driver.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
        mobileNo.sendKeys(mobile);
        FluentWait<WebDriver> wait1=new FluentWait<>(driver).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
        WebDriverWait wait2=new WebDriverWait(driver,Duration.ofSeconds(20));
        WebElement dob=wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='dateOfBirth']/descendant::input[@id='dateOfBirthInput']")));
        dob.click();
        WebElement monthValue=driver.findElement(By.xpath("//div[@class='react-datepicker__month-dropdown-container react-datepicker__month-dropdown-container--select']/child::select[@class='react-datepicker__month-select']"));
        monthValue.click();
        Select selectmonth=new Select(monthValue);
        selectmonth.selectByVisibleText(month);
        WebElement yearValue=driver.findElement(By.xpath("//div[@class='react-datepicker__month-dropdown-container react-datepicker__month-dropdown-container--select']/following-sibling::div[@class='react-datepicker__year-dropdown-container react-datepicker__year-dropdown-container--select']/child::select[@class='react-datepicker__year-select']"));
        yearValue.click();
        Select selectyear=new Select(yearValue);
        selectyear.selectByVisibleText(year);
        WebElement dateValue=driver.findElement(By.xpath("//div[@class='react-datepicker__month']/descendant::div[contains(text(),'"+date+"')]"));
        dateValue.click();
        js.executeScript("window.scrollBy(0,250)", "");
        WebElement hobbies=driver.findElement(By.xpath("//div[@class='custom-control custom-checkbox custom-control-inline']/child::label[contains(text(),'"+hobby1+"')]"));
        hobbies.click();
        WebElement addressValue=driver.findElement(By.xpath("//textarea[@id='currentAddress']"));
        addressValue.sendKeys(address);
        WebElement submit=driver.findElement(By.xpath("//div[@class='text-right col-md-2 col-sm-12']/descendant::button[contains(text(),'Submit')]"));
        js.executeScript("window.scrollBy(0,350)", "");
        Actions action=new Actions(driver);
        WebElement ad = driver.findElement(By.xpath("//div[@id='Ad.Plus-970x250-2']"));

//        WebElement ad = driver.findElement(By.xpath("(//div[starts-with(@id,'google_ads_iframe')])[3]"));
//        WebElement ad = driver.findElement(By.xpath("(//a[@class=\"ns-j3d7t-e-20\"]"));
        action.scrollToElement(ad).perform();
        submit.click();
    }

    @Then("Validate the submission {string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void validateTheSubmission(String firstname, String lastname, String email, String gender, String mobile, String month, String year, String date, String hobby, String address)
    {
        WebElement submission=driver.findElement(By.xpath("//div[contains(text(),'Thanks for submitting the form')]"));
        Assert.assertTrue(submission.isDisplayed()," Form is submitted Successfully");

        String fullname=firstname+" "+lastname;
        WebElement name= driver.findElement(By.xpath("//td[contains(text(),'Student Name')]/following-sibling::td"));
        String namegot=name.getText();
        Assert.assertEquals(namegot,fullname);
        WebElement emailValue=driver.findElement(By.xpath("//td[contains(text(),'Student Email')]/following-sibling::td"));
        String emailgot=emailValue.getText();
        Assert.assertEquals(emailgot,email);
        WebElement getgender=driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td"));
        String genderGot=getgender.getText();
        Assert.assertEquals(genderGot,gender);
        WebElement getmobile=driver.findElement(By.xpath("//td[contains(text(),'Mobile')]/following-sibling::td"));
        String mobileGot=getmobile.getText();
        Assert.assertEquals(mobileGot,mobile);
        WebElement dob=driver.findElement(By.xpath("//td[contains(text(),'Date of Birth')]/following-sibling::td"));
        String dobGot=dob.getText();
        Assert.assertEquals(dobGot,date+" "+month+","+year);
        System.out.println("Test case passed : Form submitted successfully when all the fields are filled");
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
        driver.close();
    }

    @When("Enter the values {string},{string},{string},{string} and submit")
    public void enterTheValuesAndSubmit(String firstname, String lastname, String gender, String mobile)
    {

        WebElement firstName = driver.findElement(By.xpath("//input[@id='firstName']"));
        firstName.sendKeys(firstname);

        WebElement lastName = driver.findElement(By.xpath("//input[@id='firstName']/parent::div/following-sibling::div/child::input[@id='lastName']"));
        lastName.sendKeys(lastname);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");

        WebElement genderValue = driver.findElement(By.xpath("//div[@class='col-md-9 col-sm-12']/descendant::label[contains(text(),'"+gender+"')]"));
        genderValue.click();

        WebElement mobileNo = driver.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
        mobileNo.sendKeys(mobile);
        WebElement submit=driver.findElement(By.xpath("//div[@class='text-right col-md-2 col-sm-12']/descendant::button[contains(text(),'Submit')]"));
        js.executeScript("window.scrollBy(0,350)", "");
        Actions action=new Actions(driver);
        WebElement ad = driver.findElement(By.xpath("//div[@id='Ad.Plus-970x250-2']"));

//        WebElement ad = driver.findElement(By.xpath("(//div[starts-with(@id,'google_ads_iframe')])[3]"));
//        WebElement ad = driver.findElement(By.xpath("(//a[@class=\"ns-j3d7t-e-20\"]"));
        action.scrollToElement(ad).perform();
        submit.click();

    }

    @Then("validate the form when any of the required field is not filled {string},{string},{string},{string}")
    public void validateTheFormWhenAnyOfTheRequiredFieldIsNotFilled(String firstname, String lastname, String gender, String mobile)
    {
        try {
            if (firstname.isEmpty()) {
                WebElement firstNameRequiredField = driver.findElement(By.xpath("//input[@id='firstName' and @required]"));
                Assert.assertTrue(firstNameRequiredField.isDisplayed(), "First Name field is required but not filled.");
            }
        } catch (AssertionError e) {
            System.out.println("Assertion failed for First Name: " + e.getMessage());
        }

        try {
            if (lastname.isEmpty()) {
                WebElement lastNameRequiredField = driver.findElement(By.xpath("//input[@id='lastName' and @required]"));
                Assert.assertTrue(lastNameRequiredField.isDisplayed(), "Last Name field is required but not filled.");
            }
        } catch (AssertionError e) {
            System.out.println("Assertion failed for Last Name: " + e.getMessage());
        }

        try {
            if (gender.isEmpty()) {
                WebElement genderRequiredField = driver.findElement(By.xpath("//input[@name='gender' and @required]"));
                Assert.assertTrue(genderRequiredField.isDisplayed(), "Gender field is required but not filled.");
            }
        } catch (AssertionError e) {
            System.out.println("Assertion failed for Gender: " + e.getMessage());
        }

        try {
            if (mobile.isEmpty()) {
                WebElement mobileRequiredField = driver.findElement(By.xpath("//input[@placeholder='Mobile Number' and @required]"));
                Assert.assertTrue(mobileRequiredField.isDisplayed(), "Mobile field is required but not filled.");
            }
        } catch (AssertionError e) {
            System.out.println("Assertion failed for Mobile: " + e.getMessage());
        }
        finally
        {
            driver.quit();

        }


    }


//    @When("Enter the required field values and submit")
//    public void enterTheRequiredFieldValuesAndSubmit()
//    {
//        WebElement submit1 = driver.findElement(By.xpath("//div[@class='text-right col-md-2 col-sm-12']/descendant::button[contains(text(),'Submit')]"));
//        submit1.click();
//
//    }
//
//
//    @Then("Validate the required fields")
//    public void validateTheRequiredFields()
//    {
//        List<WebElement> requiredFields = driver.findElements(By.xpath("//input[@required]"));
//
//        int emptyFieldCount = 0;
//        for (WebElement field : requiredFields) {
//            String fieldValue = field.getAttribute("value");
//            Assert.assertTrue(fieldValue == null || fieldValue.isEmpty(), "Required field is filled: " + field.getAttribute("placeholder"));
//            emptyFieldCount++;
//        }
//
//        Assert.assertTrue(emptyFieldCount >= 4, "Number of required fields not met. Expected at least 4, but found: " + emptyFieldCount);
//
//        System.out.println("Test case passed: Submission failed as all required fields were not filled.");
//        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
//
//        driver.quit();
//    }
}

//        elements=driver.findElement(By.xpath("//input[@id='firstName']"));
//        elements.sendKeys(firstname);
//        elements=driver.findElement(By.xpath("//input[@id='firstName']/parent::div/following-sibling::div/child::input[@id='lastName']"));
//        elements.sendKeys(lastname);
//        WebElement rb= driver.findElement(By.xpath("//div[@class='col-md-9 col-sm-12']/descendant::label[contains(text(),gender)]"));
//        rb.click();
//        elements=driver.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
//        elements.sendKeys(mobileno);