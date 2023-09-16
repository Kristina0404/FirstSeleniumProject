package org.ait.demo.fw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    String browser;
    WebDriver driver;
    UserHelper user;
    ProductHelper productHelp;
    HomePageHelper homePage;

    public ApplicationManager(String browser) {
        this.browser= browser;
    }


    public void init() {
        System.err.close();

        if(browser.equalsIgnoreCase("chrome")){
            driver= new ChromeDriver();
        } else if ( browser.equalsIgnoreCase("microsoftedge")){
            driver = new EdgeDriver();
        }
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        user= new UserHelper(driver);
        productHelp = new ProductHelper(driver);
        homePage = new HomePageHelper(driver);
    }

    public UserHelper getUser() {
        return user;
    }

    public ProductHelper getProductHelp() {
        return productHelp;
    }

    public HomePageHelper getHomePage() {
        return homePage;
    }

    public void stop() {
        driver.quit();
    }

}
