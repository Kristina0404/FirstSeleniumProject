package demo.web;

import org.ait.demo.models.Registration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTest extends TestBase {


    @BeforeMethod

    public void ensurePrecondition() {
        if (!app.getUser().isLinkRegisterPresent()) {
            app.getUser().clickOnLogInLink();
        }
        app.getUser().clickOnLinkRegisterButton();

    }

   @Test
    public void newUserRegistrationPositiveTest() {
        app.getUser().fillRegistrationsForm(new Registration()
                .setName("Elena")
                .setSurname("Petrova")
                .setEmail("elenap@gmail.com")
                .setPassword("Petrova123$"));

        Assert.assertTrue(app.getProductHelp().isResultPresent());

    }

}
