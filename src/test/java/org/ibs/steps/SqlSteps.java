package org.ibs.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import org.junit.jupiter.api.Assertions;

import java.sql.*;
import java.util.List;

import static org.ibs.testData.TestDataReqSQL.*;

public class SqlSteps {

    Connection connection;

    @И("Подключение к базе данных")
    public void connect() throws SQLException {
        connection = DriverManager
                .getConnection("jdbc:h2:tcp://localhost:9092/mem:testdb",
                        "user", "pass");
    }

    @И("Отключаем соединение с БД")
    public void close() throws SQLException {
        connection.close();
    }


    @Допустим("Получить данные в БД")
    public void testing() throws SQLException {
        Statement statement = connection.createStatement();
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

    @И("Добавить запись в таблцу {string} {string} {int}")
    public void insertProductTable(String name, String type, int exotic) throws SQLException {
        PreparedStatement prInsert = connection.prepareStatement(insertProduct);
        prInsert.setString(1, name);
        prInsert.setString(2, type);
        prInsert.setInt(3, exotic);
        prInsert.execute();
    }

    @И("Проверить, что последняя запись имеет данные {string} {string} {int} и удалить эту запись")
    public void checkLastRecordCorrect(String name, String type, int exotic) throws SQLException {
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

    @И("Добавить продукт в базу данных :")
    public void insetProduct(DataTable dataTable) throws SQLException {
        List<List<String>> table = dataTable.asLists(String.class);
        PreparedStatement insert =
                connection.prepareStatement(insertProduct);
        insert.setString(1, table.get(0).get(0));
        insert.setString(2, table.get(0).get(1));
        insert.setInt(3, Integer.parseInt(table.get(0).get(2)));
        insert.execute();
    }

    @И("Проверить, что полученный список не пустой")
    public void resNotEmpty(ResultSet resultSet) throws SQLException {
        Assertions.assertTrue(!resultSet.wasNull());
    }

}
