package utils;

import com.github.javafaker.Faker;

public class FakeDataGenerator {

    public static String[] fakeCustomerData() {
        Faker faker = new Faker();
        return new String[]{
                faker.internet().safeEmailAddress(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().password(5, 10)
        };
    }
}
