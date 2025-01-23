package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {

//        WebDriver driver=new ChromeDriver();
//
//        System.out.println("Positive case (All the required fields are filled)");
//        driver.navigate().to("https://demoqa.com/automation-practice-form");
//        driver.manage().window().maximize();
//        String firstname="swetha",lastname="d",gender="Female",mobile="9876543210";
//        WebElement firstName = driver.findElement(By.xpath("//input[@id='firstName']"));
//        firstName.sendKeys(firstname);
//
//        Thread.sleep(2000);
//
//        WebElement lastName = driver.findElement(By.xpath("//input[@id='firstName']/parent::div/following-sibling::div/child::input[@id='lastName']"));
//        lastName.sendKeys(lastname);
//        Thread.sleep(2000);
//
//        WebElement g = driver.findElement(By.xpath("//div[@class='col-md-9 col-sm-12']/descendant::label[contains(text(),'"+gender+"')]"));
//        g.click();
//        Thread.sleep(2000);
//
//        WebElement mobileNo = driver.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
//        mobileNo.sendKeys(mobile);
//        Thread.sleep(2000);
//        WebElement submit=driver.findElement(By.xpath("//div[@class='text-right col-md-2 col-sm-12']/descendant::button[contains(text(),'Submit')]"));
//        submit.click();
//        Thread.sleep(3000);
////
//        WebElement submission=driver.findElement(By.xpath("//div[contains(text(),'Thanks for submitting the form')]"));
//        Assert.assertTrue(submission.isDisplayed()," Form is submitted Successfully");
//
//        String fullname=firstname+" "+lastname;
//        WebElement name= driver.findElement(By.xpath("//td[contains(text(),'Student Name')]/following-sibling::td"));
//        String namegot=name.getText();
//        Assert.assertEquals(namegot,fullname);
//        WebElement getgender=driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td"));
//        String genderGot=getgender.getText();
//        Assert.assertEquals(genderGot,gender);
//        WebElement getmobile=driver.findElement(By.xpath("//td[contains(text(),'Mobile')]/following-sibling::td"));
//        String mobileGot=getmobile.getText();
//        Assert.assertEquals(mobileGot,mobile);
//        System.out.println("Test case passed : Form submitted successfully when all the required fields are filled");
//        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
//        driver.close();


        System.out.println("Negative case 1 : When the required fields are not filled");

        WebDriver driver1=new ChromeDriver();
        driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver1.navigate().to("https://demoqa.com/automation-practice-form");
        driver1.manage().window().maximize();
//        Thread.sleep(3000);

        WebElement submit1 = driver1.findElement(By.xpath("//div[@class='text-right col-md-2 col-sm-12']/descendant::button[contains(text(),'Submit')]"));
        submit1.click();

        List<WebElement> requiredFields = driver1.findElements(By.xpath("//input[@required]"));

        int emptyFieldCount = 0;
        for (WebElement field : requiredFields) {
            String fieldValue = field.getAttribute("value");
            Assert.assertTrue(fieldValue == null || fieldValue.isEmpty(), "Required field is filled: " + field.getAttribute("placeholder"));
            emptyFieldCount++;
        }

        Assert.assertTrue(emptyFieldCount >= 4, "Number of required fields not met. Expected at least 4, but found: " + emptyFieldCount);

        System.out.println("Test case passed: Submission failed as all required fields were not filled.");
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");

        driver1.quit();


        ////////////////////////////////////////////////////////////////////////////

//        System.out.println("Negative case 2 : When any one of the required field is not filled");
//        WebDriver driver2=new ChromeDriver();
//        driver2.get("https://demoqa.com/automation-practice-form");
//        driver2.manage().window().maximize();
//        String fname="abcdef",lname="",gender1="Male",mobile1="9880765432";
//        WebElement firstNameField = driver2.findElement(By.xpath("//input[@id='firstName']"));
//        firstNameField.sendKeys(fname);
//
//        Thread.sleep(2000);
//
//        WebElement lastNameField = driver2.findElement(By.xpath("//input[@id='firstName']/parent::div/following-sibling::div/child::input[@id='lastName']"));
//        lastNameField.sendKeys(lname);
//
//        Thread.sleep(2000);
//
//        WebElement g1= driver2.findElement(By.xpath("//div[@class='col-md-9 col-sm-12']/descendant::label[contains(text(),'"+gender1+"')]"));
//        g1.click();
//        Thread.sleep(2000);
//
//        WebElement mobileNoField = driver2.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
//        mobileNoField.sendKeys(mobile1);
//        Thread.sleep(2000);
//        WebElement submit2=driver2.findElement(By.xpath("//div[@class='text-right col-md-2 col-sm-12']/descendant::button[contains(text(),'Submit')]"));
//        submit2.click();
//        Thread.sleep(3000);
//        int unfilledCount=0;
//        String getvalue="";
//        List<WebElement> requiredfields1 = driver2.findElements(By.xpath("//input[@required]"));
//        for(WebElement w:requiredfields1)
//        {
//            String value = w.getAttribute("value");
//            if(value==null || value.isEmpty())
//            {
//                getvalue=w.getDomAttribute("id");
//                unfilledCount++;
//            }
//        }
//        System.out.println(unfilledCount+" field is not filled ("+getvalue+")");
//        if(unfilledCount==1)
//        {
//            System.out.println("Test Case passed : Submission failed as any one of the field was not filled");
//        }
//        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
//        driver2.quit();
//
//        ////////////////////////////////////////////////////////////////////////////
//
//        System.out.println("Negative case 3 : When the mobile number is not a 10 digit number");
//
//        WebDriver driver3 = new ChromeDriver();
//        driver3.get("https://demoqa.com/automation-practice-form");
//        driver3.manage().window().maximize();
//
//        String fname1 = "abcdef", lname1 = "gh", gender2 = "Male", mobile2 = "988076543";
//
//        WebDriverWait wait = new WebDriverWait(driver3, Duration.ofSeconds(10));
//
//        WebElement firstName1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='firstName']")));
//        firstName1.sendKeys(fname1);
//        Thread.sleep(1000);
//
//        WebElement lastName1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='firstName']/parent::div/following-sibling::div/child::input[@id='lastName']")));
//        lastName1.sendKeys(lname1);
//        Thread.sleep(1000);
//
//        WebElement g2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='col-md-9 col-sm-12']/descendant::label[contains(text(),'" + gender2 + "')]")));
//        g2.click();
//        Thread.sleep(1000);
//
//        WebElement mobileNo1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Mobile Number']")));
//        mobileNo1.sendKeys(mobile2);
//        Thread.sleep(3000);
//
//        WebElement submit3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='text-right col-md-2 col-sm-12']/descendant::button[contains(text(),'Submit')]")));
//        submit3.click();
//        Thread.sleep(2000);
//
//        WebElement mobileNoEntered = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Mobile Number']")));
//        String mobilenoGot = mobileNoEntered.getAttribute("value");
//
//        if (mobilenoGot.length() != 10) {
//            System.out.println("Test case Passed : Submission failed as the Mobile number is not a 10 digit no");
//        }
//
//        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
//        driver3.quit();
//
//        /////////////////////////////////////////////////////////
//
        WebDriver driver=new ChromeDriver();

        System.out.println("Positive case (All the required fields are filled)");
        driver.navigate().to("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();
        String firstname="swetha",lastname="d",gender="Female",mobile="9876543210";
        WebElement firstName = driver.findElement(By.xpath("//input[@id='firstName']"));
        firstName.sendKeys(firstname);

        Thread.sleep(2000);

        WebElement lastName = driver.findElement(By.xpath("//input[@id='firstName']/parent::div/following-sibling::div/child::input[@id='lastName']"));
        lastName.sendKeys(lastname);
        Thread.sleep(2000);

        WebElement g = driver.findElement(By.xpath("//div[@class='col-md-9 col-sm-12']/descendant::label[contains(text(),'"+gender+"')]"));
        g.click();
        Thread.sleep(2000);

        WebElement mobileNo = driver.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
        mobileNo.sendKeys(mobile);
        Thread.sleep(2000);
        WebElement submit=driver.findElement(By.xpath("//div[@class='text-right col-md-2 col-sm-12']/descendant::button[contains(text(),'Submit')]"));
        submit.click();
        Thread.sleep(3000);
        WebElement submission=driver.findElement(By.xpath("//div[contains(text(),'Thanks for submitting the form')]"));
        Assert.assertTrue(submission.isDisplayed()," Form is submitted Successfully");

        String fullname=firstname+" "+lastname;
        WebElement name= driver.findElement(By.xpath("//td[contains(text(),'Student Name')]/following-sibling::td"));
        String namegot=name.getText();
        Assert.assertEquals(namegot,fullname);
        WebElement getgender=driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td"));
        String genderGot=getgender.getText();
        Assert.assertEquals(genderGot,gender);
        WebElement getmobile=driver.findElement(By.xpath("//td[contains(text(),'Mobile')]/following-sibling::td"));
        String mobileGot=getmobile.getText();
        Assert.assertEquals(mobileGot,mobile);
        System.out.println("Test case passed : Form submitted successfully when all the required fields are filled");
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
        driver.close();

        WebDriver driver5=new ChromeDriver();
        driver5.get("https://demoqa.com/automation-practice-form");
        driver5.manage().window().maximize();
        String firstname2="swetha",lastname2="d",gender4="Female",mobile4="9876543210",hobby="Music",email="swethad2021@gmail.com";
        WebElement firstName2 = driver5.findElement(By.xpath("//input[@id='firstName']"));
        firstName2.sendKeys(firstname2);

        Thread.sleep(2000);

        WebElement lastName2 = driver5.findElement(By.xpath("//input[@id='firstName']/parent::div/following-sibling::div/child::input[@id='lastName']"));
        lastName2.sendKeys(lastname2);
        Thread.sleep(2000);
        WebElement mail=driver5.findElement(By.xpath("//input[@placeholder='name@example.com']"));
        mail.sendKeys(email);
        Thread.sleep(2000);
        WebElement g4 = driver5.findElement(By.xpath("//div[@class='col-md-9 col-sm-12']/descendant::label[contains(text(),'"+gender4+"')]"));
        g4.click();
        Thread.sleep(2000);

        WebElement mobileNo4 = driver5.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
        mobileNo4.sendKeys(mobile4);
        Thread.sleep(2000);
        FluentWait<WebDriver> wait1=new FluentWait<>(driver5).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);
        WebDriverWait wait2=new WebDriverWait(driver5,Duration.ofSeconds(20));
        WebElement dob=wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='dateOfBirth']/descendant::input[@id='dateOfBirthInput']")));
        dob.click();
        WebElement month=driver5.findElement(By.xpath("//div[@class='react-datepicker__month-dropdown-container react-datepicker__month-dropdown-container--select']/child::select[@class='react-datepicker__month-select']"));
        month.click();
        Select selectmonth=new Select(month);
        selectmonth.selectByVisibleText("May");
        Thread.sleep(2000);
        WebElement year=driver5.findElement(By.xpath("//div[@class='react-datepicker__month-dropdown-container react-datepicker__month-dropdown-container--select']/following-sibling::div[@class='react-datepicker__year-dropdown-container react-datepicker__year-dropdown-container--select']/child::select[@class='react-datepicker__year-select']"));
        year.click();
        Select selectyear=new Select(year);
        selectyear.selectByVisibleText("2004");
        Thread.sleep(2000);
        WebElement date=driver5.findElement(By.xpath("//div[@class='react-datepicker__month']/descendant::div[contains(text(),'22')]"));
        date.click();
        Thread.sleep(3000);
        WebElement subjects=driver5.findElement(By.xpath("//div[@class='subjects-auto-complete__input']/child::input"));
        subjects.click();
        subjects.sendKeys("M");
        Thread.sleep(6000);
        Thread.sleep(2000);
        WebElement hobbies=driver5.findElement(By.xpath("//div[@class='custom-control custom-checkbox custom-control-inline']/child::label[contains(text(),'"+hobby+"')]"));
        hobbies.click();
        Thread.sleep(2000);
//        WebElement state=driver5.findElement(By.xpath(""));
        WebElement address=driver5.findElement(By.xpath("//textarea[@id='currentAddress']"));
        address.sendKeys("103, Kothari Nagar, Singanallur, Coimbatore");
        Thread.sleep(2000);


        WebElement submit4=driver5.findElement(By.xpath("//div[@class='text-right col-md-2 col-sm-12']/descendant::button[contains(text(),'Submit')]"));
        submit4.click();
        Thread.sleep(3000);
        WebElement submission4=driver5.findElement(By.xpath("//div[contains(text(),'Thanks for submitting the form')]"));
        Assert.assertTrue(submission4.isDisplayed()," Form is submitted Successfully");
        String fullname2=firstname2+" "+lastname2;
        WebElement name4= driver5.findElement(By.xpath("//td[contains(text(),'Student Name')]/following-sibling::td"));
        String namegot2=name4.getText();
        Assert.assertEquals(namegot2,fullname2);
        WebElement getgender2=driver5.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td"));
        String genderGot2=getgender2.getText();
        Assert.assertEquals(genderGot2,gender4);
        WebElement getmobile2=driver5.findElement(By.xpath("//td[contains(text(),'Mobile')]/following-sibling::td"));
        String mobileGot2=getmobile2.getText();
        Assert.assertEquals(mobileGot2,mobile4);
//        WebElement dob=driver5.findElement(By.xpath("//div[@id='dateOfBirth']/descendant::input[@id='dateOfBirthInput']"));


        System.out.println("Test case passed : Form submitted successfully when all the fields are filled");
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
        driver5.close();

        WebDriver driver4=new ChromeDriver();
        try {

            System.out.println("Positive case (All the required fields are filled but assertion failed)");
            driver4.navigate().to("https://demoqa.com/automation-practice-form");
            driver4.manage().window().maximize();
            String firstname3 = "swetha", lastname3 = "d", gender3 = "Female", mobile3 = "9876543210";
            WebElement firstName3 = driver4.findElement(By.xpath("//input[@id='firstName']"));
            firstName3.sendKeys(firstname3);

            Thread.sleep(2000);

            WebElement lastName3 = driver4.findElement(By.xpath("//input[@id='firstName']/parent::div/following-sibling::div/child::input[@id='lastName']"));
            lastName3.sendKeys(lastname3);
            Thread.sleep(2000);

            WebElement g3 = driver4.findElement(By.xpath("//div[@class='col-md-9 col-sm-12']/descendant::label[contains(text(),'" + gender3 + "')]"));
            g3.click();
            Thread.sleep(2000);

            WebElement mobileNo3 = driver4.findElement(By.xpath("//input[@placeholder='Mobile Number']"));
            mobileNo3.sendKeys(mobile3);
            Thread.sleep(2000);
            WebElement submit5 = driver4.findElement(By.xpath("//div[@class='text-right col-md-2 col-sm-12']/descendant::button[contains(text(),'Submit')]"));
            submit5.click();
            Thread.sleep(3000);

            WebElement submission1 = driver4.findElement(By.xpath("//div[contains(text(),'Thanks for submitting the form')]"));
            Assert.assertTrue(submission1.isDisplayed(), " Form is submitted Successfully");

            WebElement name1 = driver4.findElement(By.xpath("//td[contains(text(),'Student Name')]/following-sibling::td"));
            String namegot1 = name1.getText();
            Assert.assertTrue(namegot1.contains("Mx. "), "The name should contain honorifics !");
            WebElement getgender1 = driver4.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td"));
            String genderGot1 = getgender1.getText();
            Assert.assertEquals(genderGot1, gender3);
            WebElement getmobile1 = driver4.findElement(By.xpath("//td[contains(text(),'Mobile')]/following-sibling::td"));
            String mobileGot1 = getmobile1.getText();
            Assert.assertEquals(mobileGot1, mobile3);
            System.out.println("Test case passed : Assertion failed as the name field do not have honorifics");
            System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------------------\n");
        }
        finally {
            driver4.quit();
        }




    }
}