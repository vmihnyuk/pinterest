package framework.elements;

import framework.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class ComboBox extends BaseElement{

    private Select selectItem;

    public ComboBox(final By locator) {
        super(locator);
    }
    public ComboBox(final By locator, String name) {
        super(locator, name);
    }

    public void selectItemByText(String text){
        findSelect();
        selectItem.selectByVisibleText(text);
    }

    public void selectItemByValue(String value) {
        findSelect();
        selectItem.selectByValue(value);
    }

    public void selectItemByIndex(int index){
        findSelect();
        selectItem.selectByIndex(index);
    }

    public int getSize(){
        findSelect();
        return selectItem.getOptions().size();
    }

    private void findSelect(){
        selectItem = new Select(browser.getDriver().findElement(locator));
        Waiter.waitUntilClickable(browser.getDriver(), locator);
    }

    public String getCurrentValue(){
        findSelect();
        return selectItem.getFirstSelectedOption().getText();
    }

    public String getTextByIndex(int index){
        findSelect();
        return selectItem.getOptions().get(index).getText();
    }


}
