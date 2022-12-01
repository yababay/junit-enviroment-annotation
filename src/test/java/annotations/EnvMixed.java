package annotations;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.SAME_THREAD)
@Env(withProxy = true, remote = "http://localhost:4444/wd/hub")
public class EnvMixed {

    @Test
    void method1(){
        Assertions.assertTrue(Configuration.proxyEnabled);
        Assertions.assertEquals("http://localhost:4444/wd/hub", Configuration.remote);
    }

    @Test
    @Env
    void method2(){
        Assertions.assertTrue(!Configuration.proxyEnabled);
        Assertions.assertNull(Configuration.remote);
    }

    @Test
    @Env(remote = "http://localhost:5555/wd/hub")
    void method3(){
        //Assertions.assertTrue(!Configuration.proxyEnabled);
        Assertions.assertEquals("http://localhost:5555/wd/hub", Configuration.remote);
    }
}
