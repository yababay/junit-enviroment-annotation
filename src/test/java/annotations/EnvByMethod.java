package annotations;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.SAME_THREAD)
public class EnvByMethod {

    @Test
    @Env(withProxy = true, remote = "http://localhost:4444/wd/hub")
    void checkProxyAndRemote(){
        Assertions.assertTrue(Configuration.proxyEnabled);
        Assertions.assertEquals(Configuration.remote, "http://localhost:4444/wd/hub");
    }

    @Test
    @Env
    void checkNoProxyAndRemote(){
        Assertions.assertTrue(!Configuration.proxyEnabled);
        Assertions.assertNull(Configuration.remote);
    }
}
