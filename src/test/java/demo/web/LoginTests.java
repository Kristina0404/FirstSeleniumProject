package demo.web;


import org.ait.demo.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class LoginTests extends TestBase {

    @BeforeMethod

    public void ensurePrecondition() {
        if( !app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnLogOutButton();
        }
        app.getUser().clickOnLogInLink();
    }

    @Test
    public void LoginPositiveTest() {
        app.getUser().fillLoginForm(new User()
                .setEmail("barth2@gmail.com")
                .setPassword("Barth123$"));
        app.getUser().clickOnLogInButton();
        Assert.assertTrue(app.getUser().isLogOutButtonPresent());
    }
    @Test
    public void LoginPositiveTestWithScreenCast() throws IOException, AWTException {
        app.getUser().deleteScreenCast();
        app.getUser().startRecording();

        app.getUser().fillLoginForm(new User()
                .setEmail("barth2@gmail.com")
                .setPassword("Barth123$"));
        app.getUser().clickOnLogInButton();
        app.getUser().pause(3000);
        app.getUser().stopRecording();
    }
    @Test
    public void LoginNegativeWithoutEmailTest() {
        app.getUser().fillLoginForm(new User().setPassword("Barth123$"));
        app.getUser().clickOnLogInButton();
        Assert.assertTrue(app.getUser().isLoginLinkPresent());
    }


}
