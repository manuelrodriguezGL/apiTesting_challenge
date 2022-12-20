package dataProviders;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Data provider implementation for Product Endpoint.
 */
public class ProductDataProvider extends BaseDataProvider {
    /**
     * This data provider makes use of Faker library, to generate random data.
     * The amount of fake products is defined on properties file.
     * @return A two dimension array with fake random product data
     * @throws IOException In case there's an error reading properties file.
     * @throws IndexOutOfBoundsException In case there's an overflow of the returned fake data array.
     */
    @DataProvider(name = "ProductFaker")
    private Object[][] productDataFaker() throws IOException, IndexOutOfBoundsException {
        int fakeAmount = Integer.parseInt(commonUtils.getPropertyValue("productFakeAmount"));
        Object[][] result = new Object[fakeAmount][];
        for (int row = 0; row < fakeAmount; row++) {
            result[row] = fakeDataGenerator.fakeProductData();
        }
        return result;
    }

    /**
     * Gets product description based on an Excel, located at a path defined on properties file.
     * @return A two dimension collection of product description data.
     * @throws IOException In case there's an error reading the Excel file.
     * @throws IndexOutOfBoundsException In case there's an overflow of the returned data array.
     */
    @DataProvider(name = "ProductDescription")
    private Object[][] productDescriptionData() throws IOException, IndexOutOfBoundsException {
        ArrayList<String> propertiesArray =
                commonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path",
                        "productDescription_excel_sheet")));

        return excelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
    }

    /**
     * This data provider makes use of Faker library, to generate random data.
     * The amount of fake product names is defined on properties file.
     * @return A two dimension array with fake random product names data
     * @throws IOException In case there's an error reading properties file.
     * @throws IndexOutOfBoundsException In case there's an overflow of the returned fake data array.
     */
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
