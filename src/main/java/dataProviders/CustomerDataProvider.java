package dataProviders;

import org.testng.annotations.DataProvider;
import utils.CommonUtils;
import utils.ExcelFileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CustomerDataProvider {
    @DataProvider(name = "Customer")
    public static Object[][] customerData() throws IOException {
        ArrayList<String> propertiesArray =
                CommonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path", "customer_excel_sheet")));

        return ExcelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
    }

    private Object[][] billingAddressData() throws IOException{
        ArrayList<String> propertiesArray =
                CommonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path", "billing_excel_sheet")));

        return ExcelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
    }

    private Object[][] shippingAddressData() throws IOException{
        ArrayList<String> propertiesArray =
                CommonUtils.getPropertiesArray(new ArrayList<>(Arrays.asList("customer_excel_path", "shipping_excel_sheet")));

        return ExcelFileReader.readFile(propertiesArray.get(0), propertiesArray.get(1));
    }
}
