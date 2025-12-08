# Patterns Task 2: Internet Bank Test Mode

![Java CI with Gradle](https://github.com/NetologyTestiik/patterns-task2_2/workflows/Java%20CI%20with%20Gradle/badge.svg)

## Описание
Автотесты для интернет-банка с тестовым режимом создания пользователей через API.

## Технологии
- Java 11
- JUnit 5
- Rest-assured (API тесты)
- Selenide (UI тесты)
- Lombok
- Faker

## Запуск
1. Запустите приложение:
\\\ash
cd artifacts
java -jar app-ibank.jar -P:profile=test
\\\

2. В другом терминале:
\\\ash
./gradlew test
\\\

## Время выполнения
- Ручное тестирование (минут): 90
- Автоматизация (минут): 240
