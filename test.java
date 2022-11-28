package dev.selenium.hello;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class test {
    public static void main(String[] args) {
	    System.setProperty("webdriver.chrome.driver","./drivers/selenium-chrome-driver-4.6.0.jar");
        WebDriver driver = new ChromeDriver();

        driver.get("https://selenium.dev");

        driver.quit();
    }
}
