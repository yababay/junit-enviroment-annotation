package annotations;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ExtendWith(EnvAnnotationProcessor.class)
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Env {

    Browser browser() default Browser.CHROME;
    String remote() default "";
    boolean withProxy() default false;

    enum Browser {
        CHROME, FIREFOX
    }
}
