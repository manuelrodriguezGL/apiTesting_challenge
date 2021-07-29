package utils;

import com.github.javafaker.Faker;

public class FakeDataGenerator {

    private static Faker faker = new Faker();

    public static String[] fakeCustomerData() {
        return new String[]{
                faker.internet().safeEmailAddress(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().password(5, 10)
        };
    }

    public static String[] fakeProductData() {
        return new String[]{
                faker.commerce().productName(),
                faker.code().asin(),
                faker.lorem().sentence(100)
        };
    }
}
