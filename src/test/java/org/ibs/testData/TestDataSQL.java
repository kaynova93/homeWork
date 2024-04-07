package org.ibs.testData;

public enum TestDataSQL {
    TEST_DATA_1("Груша", "Фрукт", 0),
    TEST_DATA_2("Свекла", "Овощ", 0),
    TEST_DATA_3("Синяя кукуруза", "Овощ", 1),
    TEST_DATA_4("Гуава", "Фрукт", 1);



    private String name;
    private String type;
    private int exotic;

    TestDataSQL(String name, String type, int exotic) {
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

    public int getExotic() {
        return exotic;
    }
}
