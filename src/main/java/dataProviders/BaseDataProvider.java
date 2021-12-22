package dataProviders;

import utils.CommonUtils;
import utils.ExcelFileReader;
import utils.FakeDataGenerator;

public class BaseDataProvider {
    protected final CommonUtils commonUtils = new CommonUtils();
    protected final ExcelFileReader excelFileReader = new ExcelFileReader();
    protected final FakeDataGenerator fakeDataGenerator = new FakeDataGenerator();
}
