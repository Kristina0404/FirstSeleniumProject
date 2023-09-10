package demo.web;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {

    @BeforeMethod
    public void ensurePrecondition(){

        if(!app.getHomePage().isHomeComponentPresent()){
            app.getHomePage().clickOnLogo();
        }
    }



    @Test
    public void isHomeComponentPresentTest() {

        Assert.assertTrue(app.getHomePage().isHomeComponentPresent());
    }

}
