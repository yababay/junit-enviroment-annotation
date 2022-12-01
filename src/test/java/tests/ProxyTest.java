package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.proxy.SelenideProxyServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.net.NetworkUtils;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getSelenideProxy;
import static tests.AbstractTest.setUpProxy;


//@GetProxy(proxyIsOn = true)
public class ProxyTest {

    @Test
    void checkProxy() {
        Configuration.proxyHost = new NetworkUtils().getIp4NonLoopbackAddressOfThisMachine().getHostAddress();
        open("");
        SelenideProxyServer selenideProxy = getSelenideProxy();
        selenideProxy.addRequestFilter("proxy-usages.request", (request, contents, messageInfo) -> {
                    request.headers().add("X-Test", "a7B$");
                    return null;
                }
        );
        open("http://localhost:8081/header");
        $("#from-http-header").shouldHave(text("no such header"));
        sleep(3000);
    }
}
