package demo.web;

import org.ait.demo.models.Registration;
import org.ait.demo.models.User;
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

    @DataProvider
    public Iterator<Object[]> newUser(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Christel21", "Hacker","hacker21@gmail.com","Hacker123$"});
        list.add(new Object[]{"Christel22", "Hacker","hacker22@gmail.com","Hacker123$"});
        list.add(new Object[]{"Christel23", "Hacker","hacker23@gmail.com","Hacker123$"});

        return list.iterator();
    }

    @Test(dataProvider = "newUser")
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

    @DataProvider
    public Iterator<Object[]> newUserWithCSVFile() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(
                "src/test/resources/users.csv"));
        String line = reader.readLine();
        while(line!=null) {
            String[] split = line.split(",");
            list.add(new Object[]{
                    new Registration().setName(split[0])
                            .setSurname(split[1])
                            .setEmail(split[2])
                            .setPassword(split[3])
            });
            line = reader.readLine();
        }

        return list.iterator();
    }
    @Test(dataProvider = "newUserWithCSVFile")
    public void addUserPositiveTesFromDateProviderWithCSV(Registration reg) {
        app.getUser().fillRegistrationsForm(reg);
        Assert.assertTrue(app.getProductHelp().isResultPresent());
       app.getUser().clickOnLogOutButton();

    }

}
