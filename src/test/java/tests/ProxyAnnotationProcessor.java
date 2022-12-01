package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.proxy.SelenideProxyServer;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.net.NetworkUtils;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getSelenideProxy;
import static settings.ProxySettings.useProxy;

public class ProxyAnnotationProcessor implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        GetProxy getProxy =   extensionContext.getRequiredTestClass().getAnnotation(GetProxy.class);
        if (getProxy != null){
            if (getProxy.proxyIsOn()) {

                useProxy(true);
            }
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
}
