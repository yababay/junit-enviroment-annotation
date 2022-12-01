package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.proxy.SelenideProxyServer;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.net.NetworkUtils;
import settings.Highlighter;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.addListener;
import static com.codeborne.selenide.WebDriverRunner.getSelenideProxy;
import static settings.Environment.local;
import static settings.Environment.selenoid;
import static settings.ProxySettings.useProxy;

abstract class AbstractTest {

    @BeforeAll
    static void setUpAll() {
        addListener(new Highlighter());
        switch (selenoid) {
            case local:
                //WebDriverManager.chromedriver().setup();
                WebDriverManager.firefoxdriver().setup();
                Configuration.browserSize = "1920x1080";
                break;
            case selenoid:
                //Configuration.browser = "settings.SelenoidDriverProvider";
                Configuration.browser = "firefox";
        }

    }

    @AfterEach
    void close() {
        Selenide.closeWebDriver();
    }

   static void setUpProxy() {
        useProxy(true);
        Configuration.proxyHost = new NetworkUtils().getIp4NonLoopbackAddressOfThisMachine().getHostAddress();
       // Configuration.browserCapabilities = setUpAll();

        open("");
        SelenideProxyServer selenideProxy = getSelenideProxy();

        selenideProxy.addRequestFilter("proxy-usages.request", (request, contents, messageInfo) -> {
                    request.headers().add("X-Test", "a7B$");
                    return null;
                }
        );
    }

}

