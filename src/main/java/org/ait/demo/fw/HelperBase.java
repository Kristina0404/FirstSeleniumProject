package org.ait.demo.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.NoSuchElementException;

public class HelperBase {

    WebDriver driver;


    public HelperBase(WebDriver driver) {
        this.driver=driver;
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        if(text!= null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }
    public void deleteScreenCast(){
        File directory= new File("record");
        File[] files = directory.listFiles();
        for(File f: files){
            f.delete();
        }
    }
}
