package ru.netology.ibank;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import ru.netology.ibank.data.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class AuthTest {
    private ApiUser activeUser;
    private ApiUser blockedUser;

    @BeforeAll
    static void setUpAll() {
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void setUp() {
        // Генерируем тестовых пользователей
        activeUser = DataGenerator.generateActiveApiUser();
        blockedUser = DataGenerator.generateBlockedApiUser();
        
        // Создаём пользователей через API
        ApiHelper.createUser(activeUser);
        ApiHelper.createUser(blockedUser);
    }

    @Test
    @DisplayName("Успешная авторизация активного пользователя")
    void shouldSuccessfulLoginWithActiveUser() {
        open("http://localhost:9999");
        
        $("[data-test-id=login] input").setValue(activeUser.getLogin());
        $("[data-test-id=password] input").setValue(activeUser.getPassword());
        $("[data-test-id=action-login]").click();
        
        // Проверяем успешный вход
        $("h2").shouldHave(text("Личный кабинет"));
    }

    @Test
    @DisplayName("Ошибка авторизации заблокированного пользователя")
    void shouldBlockedUserLoginError() {
        open("http://localhost:9999");
        
        $("[data-test-id=login] input").setValue(blockedUser.getLogin());
        $("[data-test-id=password] input").setValue(blockedUser.getPassword());
        $("[data-test-id=action-login]").click();
        
        // Проверяем ошибку
        $("[data-test-id=error-notification]")
                .shouldBe(visible)
                .shouldHave(text("Ошибка! Пользователь заблокирован"));
    }

    @Test
    @DisplayName("Ошибка при неверном логине")
    void shouldErrorWithInvalidLogin() {
        open("http://localhost:9999");
        
        $("[data-test-id=login] input").setValue("invalid_login");
        $("[data-test-id=password] input").setValue(activeUser.getPassword());
        $("[data-test-id=action-login]").click();
        
        $("[data-test-id=error-notification]")
                .shouldBe(visible)
                .shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    @DisplayName("Ошибка при неверном пароле")
    void shouldErrorWithInvalidPassword() {
        open("http://localhost:9999");
        
        $("[data-test-id=login] input").setValue(activeUser.getLogin());
        $("[data-test-id=password] input").setValue("invalid_password");
        $("[data-test-id=action-login]").click();
        
        $("[data-test-id=error-notification]")
                .shouldBe(visible)
                .shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @AfterEach
    void tearDown() {
        closeWebDriver();
    }
}