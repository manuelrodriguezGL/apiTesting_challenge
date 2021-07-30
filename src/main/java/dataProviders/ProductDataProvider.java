package dataProviders;

import org.testng.annotations.DataProvider;
import utils.CommonUtils;
import utils.ExcelFileReader;
import utils.FakeDataGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

    @DataProvider(name = "ProductDescription")
    private Object[][] productDescriptionFaker() throws IOException, IndexOutOfBoundsException {
        ArrayList<String> propertiesArray =
                CommonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path",
                        "productDescription_excel_sheet")));

        return ExcelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
    }

    @DataProvider(name = "ProductName")
    private Object[][] productNameFaker() throws IOException, IndexOutOfBoundsException {
        int fakeAmount = Integer.parseInt(CommonUtils.getPropertyValue("productFakeAmount"));
        Object[][] result = new Object[fakeAmount][];
        for (int row = 0; row < fakeAmount; row++) {
            result[row] = FakeDataGenerator.fakeProductName();
        }
        return result;
    }

}
