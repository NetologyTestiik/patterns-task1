# Patterns Task 1: Delivery Card Order

![Java CI with Gradle](https://github.com/NetologyTestiik/patterns-task1/workflows/Java%20CI%20with%20Gradle/badge.svg)

## Описание проекта
Автотесты для функции заказа доставки карты с возможностью перепланирования даты.

## Технологии
- Java 11
- JUnit 5
- Selenide 6.19.1 (совместимость с Java 11)
- Faker
- Lombok
- GitHub Actions

## Особенности реализации
- Используются data-классы (RegistrationDto)
- Генерация тестовых данных через Faker
- Паттерн Page Object (неявно через Selenide селекторы)
- Утилитный класс DataGenerator

## Запуск тестов локально
1. Запустите приложение:
```bash
cd artifacts
java -jar app-replan-delivery.jar