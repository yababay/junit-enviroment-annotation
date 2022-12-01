package tests;

import com.codeborne.selenide.*;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

//@Env(browser = Env.Browser.chrome, remote = true, port = 4444)
class WebTest extends AbstractTest{

    @BeforeEach
    void openBasePage(){
        Selenide.open("https://kz.siberianwellness.com/kz-ru/");
    }


    // @Env(browser = Env.Browser.chrome, remote = true, port = 4444)
    @Test
    @DisplayName("Проверка title главной страницы")
    void checkAboutPage() {
        Assertions.assertEquals(Selenide.title(), "Siberian Wellness (Сибирское здоровье) - официальный сайт и интернет-магазин компании в Казахстане");
        sleep(1000);
    }

    @Test
    @DisplayName("Проверка пункта меню красота")
    void testMenu() {
        ElementsCollection menus = $$x("//div[@id='menuBar']//li");
        menus.get(4).shouldHave(text("Красота")).click();
        sleep(1000);
    }

    @ValueSource(strings = {
            "Каталог",
            "Питание",
            "Спорт",
    })
     // @Env(browser = Env.Browser.chrome, remote = true, port = 4444)
    @ParameterizedTest(name = "Проверка пунктов меню {0}")
    void checkMenuItems(String name) {
        $("#menuBar").shouldHave(text(name))
                .shouldBe(Condition.visible);
    }

    @ValueSource(strings = {
            "О нас",
            "Наука и производство",
            "Клуб Привилегированных клиентов",
            "Контакты",
            "Магазины"
    })
    @ParameterizedTest(name = "Проверка наличия ссылок из футера {0}")
    void checkPromoSectionItems(String name) {
        $(".the-footer-bottom__middle").shouldHave(text(name)).shouldBe(Condition.visible);
    }

    @Test
    void checkPromoSection() {
        ElementsCollection promoSectionCategoryItems = $$(".product-card-c-minimal_break-word");
        List<String> textitems = promoSectionCategoryItems.stream().map(a -> a.getText()).collect(Collectors.toList());
        textitems.stream().forEach(System.out::println);
    }

    @Test
    @DisplayName("Проверка наличия бейджиков New на всех карточках товаров")
    @Owner("Голенев Василий")
    void checkFavoriteBadge() {
        ElementsCollection badges = $$(".product-card-c__left-item");
        SelenideElement newsButton = $("#menuBar").find(String.valueOf(text("Новинки")));
        sleep(3000);
        newsButton.click();
        sleep(5000);
        int badgesQuantity = badges.filter(Condition.visible).shouldHave(CollectionCondition.sizeGreaterThan(0)).size();
        int productCard = $$(".product-card-c__link ").filter(Condition.visible).size();
        Assertions.assertEquals(badgesQuantity, productCard, "ошибка, бейджики NEw присутствуют не на всех карточках");
        sleep(5000);
    }

}
