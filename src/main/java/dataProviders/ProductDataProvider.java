package dataProviders;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * In general terms, this class produces fake random product data, using Faker library
 */
public class ProductDataProvider extends BaseDataProvider {
    @DataProvider(name = "ProductFaker")
    private Object[][] productDataFaker() throws IOException, IndexOutOfBoundsException {
        int fakeAmount = Integer.parseInt(commonUtils.getPropertyValue("productFakeAmount"));
        Object[][] result = new Object[fakeAmount][];
        for (int row = 0; row < fakeAmount; row++) {
            result[row] = fakeDataGenerator.fakeProductData();
        }
        return result;
    }

    @DataProvider(name = "ProductDescription")
    private Object[][] productDescriptionFaker() throws IOException, IndexOutOfBoundsException {
        ArrayList<String> propertiesArray =
                commonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path",
                        "productDescription_excel_sheet")));

        return excelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
    }

    @DataProvider(name = "ProductName")
    private Object[][] productNameFaker() throws IOException, IndexOutOfBoundsException {
        int fakeAmount = Integer.parseInt(commonUtils.getPropertyValue("productFakeAmount"));
        Object[][] result = new Object[fakeAmount][];
        for (int row = 0; row < fakeAmount; row++) {
            result[row] = fakeDataGenerator.fakeProductName();
        }
        return result;
    }

}
