package demo.web;

import org.ait.demo.models.Registration;
import org.ait.demo.models.User;
import org.ait.demo.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
    @Test(dataProvider = "newUser",dataProviderClass= DataProviders.class )
    public void addUserPositiveTesFromDateProvider(
            String name, String surname,String email, String password) {
        app.getUser().fillRegistrationsForm(new Registration()
                .setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setPassword(password));

        Assert.assertTrue(app.getProductHelp().isResultPresent());
       app.getUser().clickOnLogOutButton();

    }
    @Test(dataProvider = "newUserWithCSVFile",dataProviderClass= DataProviders.class)
    public void addUserPositiveTesFromDateProviderWithCSV(Registration reg) {
        app.getUser().fillRegistrationsForm(reg);
        Assert.assertTrue(app.getProductHelp().isResultPresent());
       app.getUser().clickOnLogOutButton();

    }

}
