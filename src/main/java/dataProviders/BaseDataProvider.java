package dataProviders;

import utils.CommonUtils;
import utils.ExcelFileReader;
import utils.FakeDataGenerator;

/**
 * Generic class for data providers.
 * It contains declarations of utility classes that are used along children data providers.
 */
public class BaseDataProvider {
    protected final CommonUtils commonUtils = new CommonUtils();
    protected final ExcelFileReader excelFileReader = new ExcelFileReader();
    protected final FakeDataGenerator fakeDataGenerator = new FakeDataGenerator();
}
