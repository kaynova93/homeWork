package org.ibs.testData;

public class TestDataReqSQL {

    public static final String allTable = "SELECT * FROM FOOD;";

    public static final String insertProduct =
            "INSERT INTO FOOD(FOOD_NAME, FOOD_TYPE , FOOD_EXOTIC) VALUES( ?, ? , ?);";

    public static final String deleteProduct =
            "DELETE FROM FOOD WHERE FOOD_ID = ?";

}
