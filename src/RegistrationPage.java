import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by Karolis on 2016-03-04.
 */
public class RegistrationPage {
    private final WebDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public void goToPage() throws InterruptedException {
        driver.get("http://swedbank-us1bteam.rhcloud.com/?email=admin%40admin.lt&password=admin#/");

        wait.until(ExpectedConditions.elementToBeClickable(By.className("buttonLogin")));

        WebElement email = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.className("buttonLogin"));

        email.sendKeys("admin@admin.lt");
        password.sendKeys("admin");
        loginBtn.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'buttonRegister')]")));

        WebElement navigate = driver.findElement(By.xpath("//div[contains(@class, 'buttonRegister')]"));
        navigate.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@ng-click, 'submit(registrationForm.$valid)')]")));
        Thread.sleep(1000);
    }

    public void fillName(String name) {
        WebElement regName = driver.findElement(By.id("name"));

        regName.clear();
        regName.sendKeys(name);
    }

    public void fillSurname(String surname) {
        WebElement regSurname = driver.findElement(By.id("surname"));

        regSurname.clear();
        regSurname.sendKeys(surname);
    }

    public void fillTelephone(String telephone) {
        WebElement regTel = driver.findElement(By.id("tel"));

        regTel.clear();
        regTel.sendKeys(telephone);
    }

    public void selectBank(int option) {
        Select regBank = new Select(driver.findElement(By.id("bank")));

        regBank.selectByIndex(option);
    }

    public void fillDate(String date) {
        WebElement regDate = driver.findElement(By.id("date"));

        regDate.clear();
        regDate.sendKeys(date);
    }

    public void selectTime(int option) {
        Select regTime = new Select(driver.findElement(By.id("time")));

        regTime.selectByIndex(option);
    }

    public void selectSubject(int option) {
        Select regSubject = new Select(driver.findElement(By.id("subject")));

        regSubject.selectByIndex(option);
    }

    public void submit() {
        WebElement send = driver.findElement(By.xpath("//button[contains(@type, 'submit')]"));

        send.click();
    }

    public WebElement findForm() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'buttonMyReg')]")));

        WebElement navigate = driver.findElement(By.xpath("//div[contains(@class, 'buttonMyReg')]"));
        navigate.click();

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@ng-click, 'view( dataHistory.registrationID )')]")));

        Thread.sleep(1000);

        List<WebElement> forms = driver.findElements(By.xpath("//tr[@ng-repeat='dataHistory in dataHistory | filter:q as results']"));
        return forms.get(forms.size()-1);
    }

    public boolean checkIfMatches(WebElement element, String text) {
        return element.getText().contains(text);
    }
}
