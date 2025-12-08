package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("ru"));

    private DataGenerator() {
    }

    public static String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        String[] cities = {
                "Москва", "Санкт-Петербург", "Казань", "Екатеринбург", "Новосибирск",
                "Краснодар", "Ростов-на-Дону", "Самара", "Уфа", "Владивосток"
        };
        return faker.options().option(cities);
    }

    public static String generateName() {
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone() {
        return "+7" + faker.numerify("##########");
    }

    public static RegistrationDto generateUser() {
        return new RegistrationDto(
                generateCity(),
                generateName(),
                generatePhone()
        );
    }
}