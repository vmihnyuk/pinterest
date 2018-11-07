package pinterest.tests;

import framework.BaseEntity;
import framework.utils.ExcelReader;
import org.testng.annotations.DataProvider;

public class BasePinterestTest extends BaseEntity {

    @DataProvider(name="ValidBoardName")
    public Object[][] dataValidBoardName() {
        return getData("ValidBoardName");
    }

    @DataProvider(name="InvalidBoardName")
    public Object[][] dataInvalidBoardName() {
        return getData("InvalidBoardName");
    }

    @DataProvider(name="TwoSameBoards")
    public Object[][] dataTwoSameBoards() {
        return getData("TwoSameBoards");
    }

    private Object[][] getData(String sheet){
        Object[][] arrayObject = ExcelReader.getExcelData("dataTest.xls",sheet);
        return arrayObject;
    }
}
