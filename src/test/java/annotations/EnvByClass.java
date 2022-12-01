package annotations;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Env(withProxy = true, remote = "http://localhost:4444/wd/hub")
public class EnvByClass {

    @Test
    void method1(){
        Assertions.assertTrue(Configuration.proxyEnabled);
        Assertions.assertEquals(Configuration.remote, "http://localhost:4444/wd/hub");
    }

    @Test
    void method2(){
        Assertions.assertTrue(Configuration.proxyEnabled);
        Assertions.assertEquals(Configuration.remote, "http://localhost:4444/wd/hub");
    }
}
