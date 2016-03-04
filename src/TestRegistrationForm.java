import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Karolis on 2016-03-03.
 */
public class TestRegistrationForm {

    private final static WebDriver driver = new FirefoxDriver();

    @BeforeClass
    public static void setUpClass() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDownClass() {
        driver.quit();
    }

    @Test
    public void testCase() throws InterruptedException {

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.goToPage();

        registrationPage.fillName("Karolis");
        registrationPage.fillSurname("Barauskas");
        registrationPage.fillTelephone("860000000");
        registrationPage.selectBank(2);
        registrationPage.fillDate("2222-12-22");
        registrationPage.selectTime(2);
        registrationPage.selectSubject(2);

        registrationPage.submit();

        Assert.assertTrue(registrationPage.checkIfMatches(registrationPage.findForm(), "2222-12-22\n08:00:00"));

        WebElement deleteReg = driver.findElement(By.xpath("//button[contains(@ng-click, 'delete( dataHistory.registrationID )')]"));
        deleteReg.click();

        driver.quit();
    }
}
