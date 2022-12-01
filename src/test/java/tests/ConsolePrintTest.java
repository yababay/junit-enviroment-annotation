package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class ConsolePrintTest extends AbstractTest {

    @BeforeAll
    static void printSecondBeforeAll(){
        System.out.println("print second before");
    }

    @Test
    void first() {
        sleep(4000);
        System.out.println("первый тест");
    }

    @Test
    void second() {
        sleep(4000);
        System.out.println("второй тест");
    }

    @Test
    void third() {
        sleep(4000);
        System.out.println("третий тест");
    }

    @Test
    void fourth() {
        sleep(4000);
        System.out.println("четвёртый тест");
    }

    @Test
    void fifth(){
        sleep(4000);
        System.out.println("fifth test");
    }

    @Test
    void sixth (){
        sleep( 4000);
        System.out.println("the sixth test");
    }
}
