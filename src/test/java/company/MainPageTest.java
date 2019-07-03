package company;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class MainPageTest {

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe"); //--whitelist-ip %*
        this.driver = new ChromeDriver();
        this.driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        this.mainPage = new MainPage(this.driver);
    }

   @Test
    public void  SearcheAndCart() {
       By formSearch = By.xpath("//*[@id='search_query_top']");
       By clickSearch = By.xpath("//*[@id='searchbox']/button");
       By formCart = By.xpath("//*[@id='list']/a");
       WebElement webElement= mainPage.openMainPage().enterSomeBody_andClick(formSearch, "Blouse",   clickSearch, formCart);
       webElement.click();

       By addCart = By.xpath("//*[@id='center_column']/ul/li/div/div/div[3]/div/div[2]/a[1]/span");
       By proceed = By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span");

          mainPage.click(webElement,addCart);
       WebElement webElement1 = mainPage.findWebElement(proceed);
       webElement1.click();
       try {
           Thread.sleep(500);
       } catch (InterruptedException e) {
       }

       mainPage.clickProceedToCheckout("//*[@id='cart_quantity_up_2_7_0_0']/span/i");// addBlouse);

       By total_product = By.xpath("//*[@id='total_product']"); //Total products=$54.00
       String products= mainPage.getSum(total_product);
       System.out.println(products);

       By total_shipping = By.xpath("//*[@id='total_shipping']"); //Total shipping=$2.00
       String shipping= mainPage.getSum(total_shipping);
       System.out.println(shipping);

       By total = By.xpath("//*[@id='total_price_without_tax']"); //Total=$56.00
       String totalAll= mainPage.getSum(total);
       System.out.println(totalAll);

       By tax = By.xpath("//*[@id='total_tax']"); //Tax=$0.00
       String taxAll= mainPage.getSum(tax);
       System.out.println(taxAll);

       By finishTotal = By.xpath("//*[@id='total_price']"); //TOTAL=$56.00
       String totalBig= mainPage.getSum(finishTotal);
       System.out.println(totalBig);

       By iconTrash = By.xpath("//*[@id='2_7_0_0']/i"); //icon-trash
       WebElement webElementTrash = mainPage.findWebElement(iconTrash);

       mainPage.click(webElementTrash, iconTrash);

       By empty= By.xpath("//*[@id='center_column']/p");
       Assert.assertTrue(mainPage.isPresent(empty));
   }
}