package org.seleniumscreensnapper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Demo {

    @Test
    public void ScreenSnapperDemo() {
        WebDriver driver = new FirefoxDriver();
        SeleniumScreenSnapper screenSnapper = new SeleniumScreenSnapper(driver);

        driver.get("http://www.google.com");
        WebElement searchbox = driver.findElement(By.name("q"));

        screenSnapper.screenshot(searchbox);

        driver.quit();
    }
}