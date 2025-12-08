package ru.netology.delivery;

import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessfulFormSubmission() {
        Faker faker = new Faker();
        
        String city = "Москва";
        String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String name = faker.name().fullName();
        String phone = faker.phoneNumber().phoneNumber();
        
        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(date);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        
        $("[data-test-id=success-notification] .notification__content")
            .shouldHave(Condition.text("Встреча успешно запланирована на " + date), Duration.ofSeconds(15));
    }

    @Test
    void shouldRescheduleMeeting() {
        Faker faker = new Faker();
        
        String city = "Санкт-Петербург";
        String firstDate = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String secondDate = LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String name = faker.name().fullName();
        String phone = faker.phoneNumber().phoneNumber();
        
        $("[data-test-id=city] input").setValue(city);
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(firstDate);
        $("[data-test-id=name] input").setValue(name);
        $("[data-test-id=phone] input").setValue(phone);
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        
        $("[data-test-id=success-notification] .notification__content")
            .shouldHave(Condition.text("Встреча успешно запланирована на " + firstDate), Duration.ofSeconds(15));
        
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
        $("[data-test-id=date] input").setValue(secondDate);
        $$("button").find(exactText("Запланировать")).click();
        
        $("[data-test-id=replan-notification] .notification__content")
            .shouldHave(Condition.text("У вас уже запланирована встреча на другую дату. Перепланировать?"), Duration.ofSeconds(15));
        
        $$("button").find(exactText("Перепланировать")).click();
        
        $("[data-test-id=success-notification] .notification__content")
            .shouldHave(Condition.text("Встреча успешно запланирована на " + secondDate), Duration.ofSeconds(15));
    }
}