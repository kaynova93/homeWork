package org.ibs.testData;

import org.ibs.dataPage.PageElements;

public enum TestData {
    TEST_DATA_1("Груша", "Фрукт", "false", PageElements.SELECT_FRUIT),
    TEST_DATA_2("Свекла", "Овощ", "false", PageElements.SELECT_VEGETABLE),
    TEST_DATA_3("Синяя кукуруза", "Овощ", "true", PageElements.SELECT_VEGETABLE),
    TEST_DATA_4("Гуава", "Фрукт", "true", PageElements.SELECT_FRUIT);


    private String name;
    private String type;
    private String exotic;

    private PageElements pageElements;

    TestData(String name, String type, String exotic, PageElements pageElements) {
        this.name = name;
        this.type = type;
        this.exotic = exotic;
        this.pageElements = pageElements;
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

    public PageElements getPageObject() {
        return pageElements;
    }
}
