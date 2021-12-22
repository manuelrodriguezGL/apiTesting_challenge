package dataProviders;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomerDataProvider extends BaseDataProvider {
    @DataProvider(name = "CustomerExcel")
    public Object[][] customerData() throws IOException {
        ArrayList<String> propertiesArray =
                commonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path", "customer_excel_sheet")));

        return excelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
    }

    @DataProvider(name = "BillingAddress")
    private Object[][] billingAddressData() throws IOException {
        ArrayList<String> propertiesArray =
                commonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path", "billing_excel_sheet")));

        return excelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
    }

    @DataProvider(name = "ShippingAddress")
    private Object[][] shippingAddressData() throws IOException {
        ArrayList<String> propertiesArray =
                commonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path", "shipping_excel_sheet")));

        return excelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
    }

    /**
     * This data provider makes use of Faker library, to generate random data
     *
     * @return A multi dimensional array with fake random customer data
     * @throws IOException
     * @throws IndexOutOfBoundsException
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
