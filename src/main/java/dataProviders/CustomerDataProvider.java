package dataProviders;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Data provider implementation for Customer Endpoint.
 */
public class CustomerDataProvider extends BaseDataProvider {
    /**
     * Gets customer data based on an Excel, located at a path defined on properties file.
     * @return A two dimension collection of customer data.
     * @throws IOException In case there's an error reading the Excel file.
     */
    @DataProvider(name = "CustomerExcel")
    public Object[][] customerData() throws IOException {
        ArrayList<String> propertiesArray =
                commonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path", "customer_excel_sheet")));

        return excelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
    }

    /**
     * Gets billing address data based on an Excel, located at a path defined on properties file.
     * @return A two dimension collection of billing address data.
     * @throws IOException In case there's an error reading the Excel file.
     */
    @DataProvider(name = "BillingAddress")
    private Object[][] billingAddressData() throws IOException {
        ArrayList<String> propertiesArray =
                commonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path", "billing_excel_sheet")));

        return excelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
    }

    /**
     * Gets shipping address based on an Excel, located at a path defined on properties file.
     * @return A two dimension collection of shipping address data.
     * @throws IOException In case there's an error reading the Excel file.
     */
    @DataProvider(name = "ShippingAddress")
    private Object[][] shippingAddressData() throws IOException {
        ArrayList<String> propertiesArray =
                commonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path", "shipping_excel_sheet")));

        return excelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
    }

    /**
     * This data provider makes use of Faker library, to generate random data.
     * The amount of fake users is defined on properties file.
     * @return A two dimension array with fake random customer data
     * @throws IOException In case there's an error reading properties file.
     * @throws IndexOutOfBoundsException In case there's an overflow of the returned fake data array.
     */
    @DataProvider(name = "CustomerFaker")
    private Object[][] customerDataFaker() throws IOException, IndexOutOfBoundsException {
        int fakeAmount = Integer.parseInt(commonUtils.getPropertyValue("customerFakeAmount"));
        Object[][] result = new Object[fakeAmount][];
        for (int row = 0; row < fakeAmount; row++) {
            result[row] = fakeDataGenerator.fakeCustomerData();
        }
        return result;
    }
}
