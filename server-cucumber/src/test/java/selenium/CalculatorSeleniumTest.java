package selenium;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculatorSeleniumTest {

    private static WebDriver driver;
    private static WebDriverWait driverWait;

    @BeforeClass
    public static void setup() {
        String path = CalculatorSeleniumTest.class.getClassLoader()
                .getResource("driver/geckodriver.exe")
                .getPath();
        System.setProperty("webdriver.gecko.driver", path);
        driver = new FirefoxDriver();
        driverWait = new WebDriverWait(driver, 10);
        driver.get("http://localhost:4200");
    }

    @Test
    public void testTitle() {
        assertThat(driver.getTitle()).isEqualTo("TechnoWave");
    }

    @Test
    public void testColorButton() {
        String color = driver.findElement(By.tagName("button")).getCssValue("background-color");
        assertThat(color).isEqualTo("rgb(52, 58, 64)");
    }

    @Test
    public void testH1Text() {
        String text = driver.findElement(By.xpath("/html/body/app-root/app-calculator/h1")).getText();
        assertThat(text).isEqualTo("Calculator");
    }

    @Test
    public void testCompute() {
        driver.findElement(By.id("x")).sendKeys("11");
        driver.findElement(By.id("y")).sendKeys("31");
        driver.findElement(By.className("btn")).click();
        driverWait.until(d -> d.findElement(By.id("z")).getAttribute("value").equals("42"));
    }

    @AfterClass
    public static void close() {
        driver.close();
    }
}
