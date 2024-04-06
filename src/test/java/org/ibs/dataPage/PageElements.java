package org.ibs.dataPage;

public enum PageElements {
    BTN_ADD("//button[@data-target = '#editModal']"),
    FIELD_TYPE("//select"),
    SELECT_FRUIT("//option[@value='FRUIT']"),
    SELECT_VEGETABLE("//option[@value='VEGETABLE']"),
    MODAL_TITLE("editModalLabel"),
    BTN_SAVE("save"),
    INPUT_FIELD_NAME("//input[@class='form-control']"),
    TITLE_FIELD_NAME("//label[.='Наименование']"),
    TITLE_FIELD_TYPE("//label[.='Тип']"),
    TITLE_FIELD_EXOTIC("//label[.='Экзотический']"),
    CHECKBOX_EXOTIC("exotic")
    ;

    private String selector ;

    PageElements(String selector) {
        this.selector = selector;
    }

    public String getSelector() {
        return selector;
    }
}
