package dataProviders;

import org.testng.annotations.DataProvider;
import utils.CommonUtils;
import utils.FakeDataGenerator;

import java.io.IOException;

public class ProductDataProvider {
    @DataProvider(name = "ProductFaker")
    private Object[][] productDataFaker() throws IOException, IndexOutOfBoundsException {
        int fakeAmount = Integer.parseInt(CommonUtils.getPropertyValue("productFakeAmount"));
        Object[][] result = new Object[fakeAmount][];
        for (int row = 0; row < fakeAmount; row++) {
            result[row] = FakeDataGenerator.fakeProductData();
        }
        return result;
    }
}
