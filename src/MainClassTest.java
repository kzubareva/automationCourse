import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class MainClassTest {

    private AppiumDriver driver;


    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","8.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\kseniya.zubareva\\Desktop\\automationCourse\\apks\\org.wikipedia_50359_apps.evozi.com.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    MainClass mainClassObject = new MainClass();

    @Test
    public void testGetLocalNumber()
    {
        int a = this.mainClassObject.getLocalNumber();
        Assert.assertTrue("Number isn't 14", a == 14);
    }

    @Test

    public void testGetClassNumber()
    {
        int b = this.mainClassObject.getClassNumber();
        Assert.assertTrue("Number is < 45", b > 45);
    }

    @Test
    public void testGetClassString()
    {
        String stringToCheck = this.mainClassObject.getClassString();
        String stringToCompare = "hello";

        boolean result = stringToCheck.regionMatches(true, 0, stringToCompare, 0, stringToCompare.length());
        Assert.assertTrue("The words 'Hello' and 'hello' are missing", result);
    }



}
