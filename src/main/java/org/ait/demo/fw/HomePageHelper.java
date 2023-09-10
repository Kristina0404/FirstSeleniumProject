package org.ait.demo.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageHelper extends HelperBase {
    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    public boolean isHomeComponentPresent() {
        return driver.findElements(
                By.xpath("//a[@href='https://www.tricentis.com/speed/']")).size() > 0;
    }

    public void clickOnLogo() {
        click(By.xpath("//img[@alt='Tricentis Demo Web Shop']"));
    }

    public boolean isPageTitlePresent() {
        return isElementPresent(By.xpath("//div[@class = 'page-title']"));
    }
}
