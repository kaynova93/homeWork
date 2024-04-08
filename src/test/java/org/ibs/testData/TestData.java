package org.ibs.testData;


public enum TestData {

    TEST_DATA_1("Груша", "Фрукт", "false"),
    TEST_DATA_2("Свекла", "Овощ", "false"),
    TEST_DATA_3("Синяя кукуруза", "Овощ", "true"),
    TEST_DATA_4("Гуава", "Фрукт", "true");

    private String name;
    private String type;
    private String exotic;

    TestData(String name, String type, String exotic) {
        this.name = name;
        this.type = type;
        this.exotic = exotic;
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
}
