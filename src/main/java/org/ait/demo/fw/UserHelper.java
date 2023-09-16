package org.ait.demo.fw;

import com.google.common.io.Files;
import org.ait.demo.fw.HelperBase;
import org.ait.demo.models.Registration;
import org.ait.demo.models.User;
import org.ait.demo.utils.Recorder;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.awt.*;

import java.io.File;
import java.io.IOException;


import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class UserHelper extends HelperBase {

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnLinkRegisterButton() {
        click(By.xpath("//a[text()= 'Register']"));
    }

    public void clickOnLogInLink() {
        click(By.xpath("//a[@href='/login']"));
    }

    public void clickOnLogOutButton() {
        click(By.xpath("//a[text()='Log out']"));
    }

    public boolean isLoginLinkPresent() {
        return isElementPresent(By.xpath("//a[contains(text(),'Log in')]"));
    }

    public void clickOnLogInButton() {
        click(By.xpath("//input[@class='button-1 login-button']"));
    }

    public void fillLoginForm(User user) {
        type(By.xpath("//input[@id='Email']"), user.getEmail());
        type(By.xpath("//input[@id='Password']"), user.getPassword());
    }
    public void fillLoginFormForScreenCast(User user) {
        type(By.xpath("//input[@id='Email']"), user.getEmail());
        pause(500);
        type(By.xpath("//input[@id='Password']"), user.getPassword());
        pause(500);
    }



    public boolean isLogOutButtonPresent() {
        return isElementPresent(By.xpath("//a[text()='Log out']"));
    }

    public boolean isLinkRegisterPresent() {
        return isElementPresent(By.xpath("//a[contains(text(),'Register')]"));
    }

    public void fillRegistrationsForm(Registration registration) {
        click(By.xpath("//input[@id='gender-female']"));
        //first name
        type(By.xpath("//input[@id='FirstName']"), registration.getName());
        //last name
        type(By.xpath("//input[@id='LastName']"), registration.getSurname());
        // email
        fillLoginForm(new User().setEmail(registration.getEmail()).setPassword(registration.getPassword()));
        //confirm password
        type(By.xpath("//input[@id='ConfirmPassword']"), registration.getPassword());
        // registration button
        clickOnRegisterButton();
    }

    public void clickOnRegisterButton() {
        click(By.xpath("//input[@id='register-button']"));
    }

    public String takeScreenshot()  {
       File tmp=  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
       File screenshot = new File("screenshots/screen"
               + System.currentTimeMillis()/1000 + ".png");
        try {
            Files.copy(tmp,screenshot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return screenshot.getAbsolutePath();
    }
    private ScreenRecorder screenRecorder;

    public void startRecording() throws IOException, AWTException {
        File file = new File("record");

        Dimension screeSize= Toolkit.getDefaultToolkit().getScreenSize();
        int width = screeSize.width;
        int height = screeSize.height;

        Rectangle captureSize = new Rectangle(0,0,width,height);

        GraphicsConfiguration gc= GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();


            screenRecorder = new Recorder(gc,captureSize,
                    new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_MJPG,
                            CompressorNameKey, ENCODING_AVI_MJPG, DepthKey, 24, FrameRateKey,
                            Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
                            "black", FrameRateKey, Rational.valueOf(30)),
                    null,file,"MyVideo");
            screenRecorder.start();
    }
    public void stopRecording() throws IOException {
        screenRecorder.stop();
    }
}
