package settings;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URI;

public class SelenoidDriverProvider implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        DesiredCapabilities desCapabilities = new DesiredCapabilities();
        desCapabilities.setBrowserName("firefox");
        //desCapabilities.setVersion("106.0");

        desCapabilities.setCapability("enableVNC", true);
        desCapabilities.setCapability("enableVideo", true);
        try {
            RemoteWebDriver driver = new RemoteWebDriver(
                    URI.create("http://localhost:4444/wd/hub").toURL(),
                    desCapabilities
            );
            driver.manage().window().setSize(new Dimension(1920, 1080));
            return driver;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}


