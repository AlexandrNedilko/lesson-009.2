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
       By clickSearch = By.xpath("//*[@id='searchbox']/button"); //search
       By formCart = By.xpath("//*[@id='list']/a"); //list
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

       mainPage.clickProceedToCheckout("//*[@id='cart_quantity_up_2_7_0_0']/span/i");// click Plus;

       WebElement newSummary = mainPage.findWebElement(By.xpath("//*[@id='total_product']")); // получаем : Total products	$54.00
      /* try {
           Thread.sleep(500);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }*/

       By total_product = By.xpath("//*[@id='total_product']"); //Total products=$54.00

       mainPage.appearElement(this.driver, newSummary);

       String actualResultProduct= mainPage.getSum(total_product/*, newSummary*/);
       //System.out.println("mainPage print "+actualResultProduct);
       String expectedResult = "$54.00";
       Assert.assertEquals("If test failed then total price was changed", expectedResult, actualResultProduct);

       By total_shipping = By.xpath("//*[@id='total_shipping']"); //Total shipping=$2.00
       String actualResultshipping= mainPage.getSum(total_shipping);
       //System.out.println(shipping);
       String expectedResult1 = "$2.00";
       Assert.assertEquals("If test failed then total price was changed", expectedResult1, actualResultshipping);

       By total = By.xpath("//*[@id='total_price_without_tax']"); //Total=$56.00
       String actualResulttotalAll= mainPage.getSum(total);
       //System.out.println(totalAll);
       String expectedResult2 = "$56.00";
       Assert.assertEquals("If test failed then total price was changed", expectedResult2, actualResulttotalAll);

       By tax = By.xpath("//*[@id='total_tax']"); //Tax=$0.00
       String actualResulttaxAll= mainPage.getSum(tax);
      //System.out.println(taxAll);
       String expectedResult3 = "$0.00";
       Assert.assertEquals("If test failed then total price was changed", expectedResult3, actualResulttaxAll);

       By finishTotal = By.xpath("//*[@id='total_price']"); //TOTAL=$56.00
       String actualResulttotalBig= mainPage.getSum(finishTotal );
       //System.out.println(totalBig);
       String expectedResult4 = "$56.00";
       Assert.assertEquals("If test failed then total price was changed", expectedResult4, actualResulttotalBig);

       By iconTrash = By.xpath("//*[@id='2_7_0_0']/i"); //icon-trash
       WebElement webElementTrash = mainPage.findWebElement(iconTrash);

       mainPage.click(webElementTrash, iconTrash);

       try {
           Thread.sleep(2000);
       } catch (InterruptedException e) {
       }

       By empty= By.cssSelector("#center_column p.alert-warning");
       boolean b = mainPage.isPresent(empty);
       //*[@id='center_column']/p[1]
       System.out.println(b);
       Assert.assertTrue(mainPage.isPresent(empty)); //Your shopping cart is empty.

/*       wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#center_column p.alert-warning")));
       Assert.assertTrue(chromeDriver.findElement(By.cssSelector("#center_column p.alert-warning")).getText().equalsIgnoreCase("Your shopping cart is empty."));*/
   }
}