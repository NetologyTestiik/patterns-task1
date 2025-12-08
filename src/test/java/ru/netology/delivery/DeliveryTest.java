package ru.netology.delivery;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;
import ru.netology.delivery.data.RegistrationDto;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

class DeliveryTest {
    private RegistrationDto user;

    @BeforeAll
    static void setUpAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 15000;
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        user = DataGenerator.generateUser();
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        // Первая запись
        $("[data-test-id=city] input").setValue(user.getCity());
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate(3));
        $("[data-test-id=name] input").setValue(user.getName());
        $("[data-test-id=phone] input").setValue(user.getPhone());
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        
        $("[data-test-id=success-notification]")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Успешно!"));
                
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(3)));

        // Перепланирование
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(DataGenerator.generateDate(5));
        $(byText("Запланировать")).click();
        
        $("[data-test-id=replan-notification]")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Необходимо подтверждение"));
                
        $(byText("Перепланировать")).click();
        
        $("[data-test-id=success-notification]")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Успешно!"));
                
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(5)));
    }
}