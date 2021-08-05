package dataProviders;

import org.testng.annotations.DataProvider;
import utils.CommonUtils;
import utils.ExcelFileReader;
import utils.FakeDataGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomerDataProvider {
    @DataProvider(name = "CustomerExcel")
    public static Object[][] customerData() throws IOException {
        ArrayList<String> propertiesArray =
                CommonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path", "customer_excel_sheet")));

        return ExcelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
    }

    @DataProvider(name = "BillingAddress")
    private Object[][] billingAddressData() throws IOException {
        ArrayList<String> propertiesArray =
                CommonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path", "billing_excel_sheet")));

        return ExcelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
    }

    @DataProvider(name = "ShippingAddress")
    private Object[][] shippingAddressData() throws IOException {
        ArrayList<String> propertiesArray =
                CommonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path", "shipping_excel_sheet")));

        return ExcelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
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
        int fakeAmount = Integer.parseInt(CommonUtils.getPropertyValue("customerFakeAmount"));
        Object[][] result = new Object[fakeAmount][];
        for (int row = 0; row < fakeAmount; row++) {
            result[row] = FakeDataGenerator.fakeCustomerData();
        }
        return result;
    }
}
