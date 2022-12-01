package annotations;

import org.junit.jupiter.api.extension.*;

import com.codeborne.selenide.Configuration;

public class EnvAnnotationProcessor implements BeforeAllCallback, BeforeEachCallback, AfterAllCallback, AfterEachCallback {

    public static final ExtensionContext.Namespace envSpace = ExtensionContext.Namespace.create(EnvAnnotationProcessor.class);

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        Env env = context.getRequiredTestClass().getAnnotation(Env.class);
        context.getStore(envSpace).put("env", env);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        context.getStore(envSpace).remove("env");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Env env = fromClassOrFromMethod(context);
        if(env == null) return;
        // Capabilities options = env.browser() == Env.Browser.FIREFOX ? new FirefoxOptions() : new ChromeOptions();
        String remote = env.remote();
        Configuration.proxyEnabled = env.withProxy();
        Configuration.remote = remote.isEmpty() ? null : remote;
    }

    private Env fromClassOrFromMethod(ExtensionContext context){
        Env methodEnv = context.getRequiredTestMethod().getAnnotation(Env.class);
        if(methodEnv != null) return methodEnv;
        return context.getStore(envSpace).get("env", Env.class);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        //context.getRequiredTestMethod().getAnnotation(Env.class).
    }
}
