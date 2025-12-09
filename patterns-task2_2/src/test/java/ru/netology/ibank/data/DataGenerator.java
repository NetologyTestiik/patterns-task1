package ru.netology.ibank.data;

import com.github.javafaker.Faker;
import java.util.Locale;

public class DataGenerator {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataGenerator() {
    }

    public static String generateLogin() {
        return faker.name().username();
    }

    public static String generatePassword() {
        return faker.internet().password();
    }

    public static ApiUser generateApiUser(String status) {
        return new ApiUser(
                generateLogin(),
                generatePassword(),
                status
        );
    }


    public static ApiUser generateActiveApiUser() {
        return generateApiUser("active");
    }

    public static ApiUser generateBlockedApiUser() {
        return generateApiUser("blocked");
    }
}
