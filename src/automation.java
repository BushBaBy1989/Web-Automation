import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class automation {
    WebDriver _driver;
    String HomeUrl = "https://supersport.com/search";

    @BeforeTest
    public void TestFixtureSetUp()
    {
        _driver = new ChromeDriver();
    }

    @AfterTest
    public void TestFixtureTearDown()
    {
        _driver.quit();
    }

    @Test
    public void TestSearchFieldEmptyOnFirstLoad() throws InterruptedException {
        _driver.get(HomeUrl);
        var searchFieldElement = _driver.findElement(By.id("srch-term"));
        Assert.assertEquals("", searchFieldElement.getAttribute("value"));
    }

    @Test
    public void TestSearchTermPassedThrough() throws InterruptedException {
        _driver.get(HomeUrl);
        var searchFieldElement = _driver.findElement(By.id("srch-term"));
        searchFieldElement.sendKeys("Cricket");
        var searchFieldButton = _driver.findElement(By.className("input-group-btn"));
        searchFieldButton.submit();
        Thread.sleep(500);
        var newsLabelElement = _driver.findElement(By.id("srch-term"));
        Assert.assertEquals("Cricket", newsLabelElement.getAttribute("value"));
    }

    @Test
    public void TestSearchTermBringsUpResults() throws InterruptedException {
        _driver.get(HomeUrl);
        var searchFieldElement = _driver.findElement(By.id("srch-term"));
        searchFieldElement.sendKeys("Cricket");
        var searchFieldButton = _driver.findElement(By.className("input-group-btn"));
        searchFieldButton.submit();
        Thread.sleep(500);
        var newsLabelElement = _driver.findElement(By.xpath("//*[@id=\"news-listing\"]/div[1]/div/span"));
        Assert.assertEquals("News", newsLabelElement.getText());
    }
}
