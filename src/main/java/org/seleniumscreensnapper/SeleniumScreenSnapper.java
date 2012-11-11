package org.seleniumscreensnapper;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeleniumScreenSnapper {
    private final int numberOfRetries = 2;
    private final int timeToWait = 1000;

    private final String screenshotFolder = "/Java/temp";
    private final String thumbnailFolder = "/Java/temp/thumbnails";
    private WebDriver driver;
    private Dimension elementSize;
    private Screenshotter screenshotter;
    private Thumbnailer thumbnailer;


    public SeleniumScreenSnapper(WebDriver driver) {
        this.driver = driver;
        screenshotter = new Screenshotter(driver, screenshotFolder);
        thumbnailer = new Thumbnailer(thumbnailFolder);
    }

    public void screenshot(WebElement element) {
        screenshot(new ArrayList<WebElement>(Arrays.asList(new WebElement[]{element})));
    }

    public void screenshot(List<WebElement> elements) {
        File overlayedFile = screenshotter.screenshotAndHighlight(elements);
        for (WebElement element : elements) {
            thumbnailer.createAndSaveThumbnail(overlayedFile, element);
        }
    }
}