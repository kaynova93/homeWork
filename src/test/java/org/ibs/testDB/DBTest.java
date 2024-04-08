package org.ibs.testDB;

import org.ibs.testData.TestDataSQL;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.sql.*;

import static org.ibs.testDB.BaseTestSQL.*;
import static org.ibs.testData.TestDataSQL.TEST_DATA_1;
import static org.ibs.testData.TestDataReqSQL.*;

public class DBTest {


    @ParameterizedTest
    @EnumSource(TestDataSQL.class)
    public void insetProduct(TestDataSQL testData) throws SQLException {
        Connection connection = initDB();
        PreparedStatement insert =
                connection.prepareStatement(insertProduct);
        insert.setString(1, testData.getName());
        insert.setString(2, testData.getType());
        insert.setInt(3, testData.getExotic());
        insert.execute();

        ResultSet res = connection.prepareStatement(allTable).executeQuery();
        res.last();

        Assertions.assertEquals(testData.getName(), res.getString("FOOD_NAME"));
        Assertions.assertEquals(testData.getType(), res.getString("FOOD_TYPE"));
        Assertions.assertEquals(testData.getExotic(), res.getInt("FOOD_EXOTIC"));
    }

    @Test
    public void getAllProduct() throws SQLException {

        Statement statement = initDB().createStatement();
        ResultSet resultSet = statement.executeQuery(allTable);

        while (resultSet.next()) {
            System.out.printf("%d, %s, %s, %d\n",
                    resultSet.getInt("FOOD_ID"),
                    resultSet.getString("FOOD_NAME"),
                    resultSet.getString("FOOD_TYPE"),
                    resultSet.getInt("FOOD_EXOTIC"));
        }
        Assertions.assertTrue(!resultSet.wasNull());
    }

    @Test
    public void deleteProduct() throws SQLException {
        Connection connection = initDB();
        PreparedStatement prInsert = connection.prepareStatement(insertProduct);
        prInsert.setString(1, TEST_DATA_1.getName());
        prInsert.setString(2, TEST_DATA_1.getType());
        prInsert.setInt(3, TEST_DATA_1.getExotic());
        prInsert.execute();
        Statement statement = connection.createStatement();
        ResultSet resBefore = statement.executeQuery(allTable);
        resBefore.last();
        int id = resBefore.getInt("FOOD_ID");
        PreparedStatement pr = connection.prepareStatement(deleteProduct);
        pr.setInt(1, id);
        pr.execute();
        ResultSet resAfter = statement.executeQuery(allTable);
        resAfter.last();
        Assertions.assertTrue(resAfter.getInt("FOOD_ID") != id);

    }
}
