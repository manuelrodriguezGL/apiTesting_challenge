package utils;

import com.github.javafaker.Faker;

public class FakeDataGenerator {

    private Faker faker = new Faker();

    public String[] fakeCustomerData() {
        return new String[]{
                faker.internet().safeEmailAddress(),
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.internet().password(5, 10)
        };
    }

    public String[] fakeProductData() {
        return new String[]{
                faker.commerce().productName(),
                faker.code().asin(),
                faker.lorem().sentence(100)
        };
    }

    public String[] fakeProductName() {
        return new String[]{
                faker.random().nextInt(11, 21).toString(),
                faker.commerce().productName()
        };
    }
}
