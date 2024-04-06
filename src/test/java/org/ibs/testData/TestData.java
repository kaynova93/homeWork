package org.ibs.testData;

import org.ibs.dataPage.PageObject;

public enum TestData {
    TEST_DATA_1("Груша", "Фрукт", "false", PageObject.SELECT_FRUIT),
    TEST_DATA_2("Свекла", "Овощ", "false", PageObject.SELECT_VEGETABLE),
    TEST_DATA_3("Синяя кукуруза", "Овощ", "true",PageObject.SELECT_VEGETABLE),
    TEST_DATA_4("Гуава", "Фрукт", "true",PageObject.SELECT_FRUIT);


    private String name;
    private String type;
    private String exotic;

    private PageObject pageObject;

    TestData(String name, String type, String exotic,PageObject pageObject) {
        this.name = name;
        this.type = type;
        this.exotic = exotic;
        this.pageObject = pageObject;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getExotic() {
        return exotic;
    }

    public PageObject getPageObject() {
        return pageObject;
    }
}
