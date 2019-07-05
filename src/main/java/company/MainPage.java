/*Home work 9.2
        1. Открыть главную страницу automationpractice.com
        2. В поле поиска ввести “Blouse” и выполнить поиск
        3. Переключится на режим просмотра ‘List”
        4. Добавить товар в корзину
        5. В секции Summary увеличить количество товаров на 1
        6. Проверить что значения Total для товара , Total products, Total
        shipping , Total всех товаров , Tax и TOTAL общий отображается
        корректно
        7. Удалить товар из корзины
        8. Проверить что корзина пустая*/

package company;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private static final String MAIN_PAGE_URL = "http://automationpractice.com";
    private WebDriver driver;

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

    // Если же элемент все таки отсутствует на странице и
    // нужно дождаться не только его появления, но и того, чтобы он стал видимым
    public static void appearElement(WebDriver driver, final WebElement element){
        Wait wait = new WebDriverWait(driver, 25)
                .ignoring(NoSuchElementException.class);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                //System.out.println("apply wait "+element.getText() );
                return element.getText().equals("$54.00");
            }
            public String toString() {      return null;            }
        });
    }
}