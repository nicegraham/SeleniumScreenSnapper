package org.seleniumscreensnapper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

public class LocationFinder {
    private LocationFinder() {
    }

    public static org.openqa.selenium.Point getLocation(WebElement element) {
        org.openqa.selenium.Point location;

        if (isFirefox(element) || isiOs(element)) {
            location = element.getLocation();
        } else {
            try {
                Coordinates coordinates = ((RemoteWebElement) element).getCoordinates();
                location = coordinates.getLocationOnScreen();
            } catch (Exception e) {
                location = element.getLocation();
            }
        }
        return location;
    }

    private static boolean isRemoteWebElement(WebElement element) {
        return RemoteWebElement.class.isInstance(element);
    }

    private static RemoteWebDriver getRemoteWebDriver(WebElement element) {
        return ((RemoteWebDriver) ((RemoteWebElement) element).getWrappedDriver());
    }

    private static boolean isChrome(WebElement element) {
        return getRemoteWebDriver(element).getCapabilities().getBrowserName().contains("chrome");
    }

    private static boolean isFirefox(WebElement element) {
        return getRemoteWebDriver(element).getCapabilities().getBrowserName().toLowerCase().contains("firefox");
    }

    private static boolean isAndroid(WebElement element) {
        return getRemoteWebDriver(element).getCapabilities().getBrowserName().contains("android");
    }

    private static boolean isIphone(WebElement element) {
        return getRemoteWebDriver(element).getCapabilities().getBrowserName().toLowerCase().contains("iphone");
    }

    private static boolean isIpad(WebElement element) {
        return getRemoteWebDriver(element).getCapabilities().getBrowserName().toLowerCase().contains("ipad");
    }

    private static boolean isiOs(WebElement element) {
        return isIpad(element) || isIphone(element);
    }

    public static boolean isMobile(WebElement element) {
        return isAndroid(element) || isiOs(element);
    }
}