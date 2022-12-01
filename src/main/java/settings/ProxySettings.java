package settings;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

public class ProxySettings {
    /**
     * Turns proxy on / off
     * When toggling (on <-> off) happens, browser is closed
     *
     * @param proxyEnabled true - turn on, false - turn off
     */
    public static void useProxy(boolean proxyEnabled) {
        if (Configuration.proxyEnabled != proxyEnabled) {
            Selenide.closeWebDriver();
        }
        Configuration.proxyEnabled = proxyEnabled;
    }
}
