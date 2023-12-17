package school_management.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;

import school_management.utilities.Driver;

import java.time.Duration;

public class Hooks {

    @Before ("@UI")
    public static void before_ui(){
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        Driver.getDriver().manage().window().maximize();
    }

    @After("@UI")
    public void tearDown()  {
        Driver.closeDriver();
    }
}
