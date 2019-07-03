package company;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private static final String MAIN_PAGE_URL = "http://automationpractice.com";
    private WebDriver driver;

    private By tshirtsTab = By.xpath("//*[@id='block_top_menu']/ul/li[3]/a");
    private By sizeLcheckbox = By.id("layered_id_attribute_group_3");

    private By singIn = By.xpath("//*[@id='header']/div[2]/div/div/nav/div[1]/a");
    private By singIn1 = By.xpath("//*[@id='columns']/div[1]/span[2]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public MainPage openMainPage() {
        this.driver.navigate().to(MAIN_PAGE_URL);
        return this;
    }



    public WebElement enterSomeBody_andClick(By path, String someone, By click, By firstForm) {
        WebElement webElement = this.driver.findElement(path);
        webElement.sendKeys(someone);
        webElement.findElement(click).click();
        return this.driver.findElement(firstForm);
    }

    public void click(WebElement webElement, By firstForm) {
        webElement.findElement(firstForm).click();
    }

    public void fillForm(WebElement webElement, By form, String field) {

        /*try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }*/

        webElement.findElement(form).click();
        webElement.findElement(form).clear();
        /*try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
        }*/
        webElement.findElement(form).sendKeys(field);
    }

    public boolean isPresent(By isRegister) {
        return this.driver.findElement(isRegister).isEnabled();
    }

    public WebElement findWebElement(By firstForm) {
        return this.driver.findElement(firstForm);
    }

    public void clickProceedToCheckout(String checkout) {
        new WebDriverWait(driver, 10).
                until(ExpectedConditions.visibilityOfElementLocated(By.xpath(checkout))).click();

    }
    public String getSum(By myTotal){
        WebElement myAllTotal= this.driver.findElement(myTotal);
        return myAllTotal.getText();

    }

}