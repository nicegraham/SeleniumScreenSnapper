package org.seleniumscreensnapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class Demo {

    @Test
    public void ScreenSnapperDemo() {
        WebDriver driver = new FirefoxDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        SeleniumScreenSnapper screenSnapper = new SeleniumScreenSnapper(driver);

        driver.get("http://www.google.com");
        WebElement searchbox = driver.findElement(By.name("q"));

        screenSnapper.screenshot(searchbox);

        searchbox.sendKeys("cheese");

        WebElement searchButton = wait.until(visibilityOfElementLocated(By.name("btnG")));

        searchButton.click();

        wait.until(visibilityOfElementLocated(By.className("navend")));

        List<WebElement> results = driver.findElements(By.className("g"));

        screenSnapper.screenshot(results);

        driver.quit();
    }
}