#Patterns Task 2: Internet Bank Test Mode

![Java CI with Gradle](https://github.com/NetologyTestiik/patterns-task2_2/workflows/Java%20CI%20with%20Gradle/badge.svg)

##Описание проекта
Автотесты для интернет-банка с тестовым режимом создания пользователей через API. Реализованы согласно требованиям задания «2.3. Patterns» курса Нетологии.

##Требования выполнения
- [x] Использование тестового режима приложения (`-P:profile=test`)
- [x] API для создания пользователей через Rest-assured
- [x] UI тесты через Selenide
- [x] Data-классы с Lombok
- [x] Генерация данных через Faker
- [x] Разные репозитории для задач
- [x] CI/CD через GitHub Actions

##Технологии
- **Java 11**
- **JUnit 5** - фреймворк для тестирования
- **Rest-assured 5.5.0** - для API тестов
- **Selenide 6.19.1** - для UI тестов
- **Faker** - генерация тестовых данных
- **Lombok** - аннотации для сокращения кода
- **Gradle** - система сборки

##Архитектура
```
src/test/java/ru/netology/ibank/
├── data/
│ ├── ApiUser.java # Data-класс для API запросов
│ ├── UiUser.java # Data-класс для UI тестов
│ ├── DataGenerator.java # Генератор тестовых данных
│ └── ApiHelper.java # Хелпер для API вызовов
└── AuthTest.java # Основной тестовый класс
