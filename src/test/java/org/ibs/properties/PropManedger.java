package org.ibs.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class PropManedger {

    public static HashMap<String, String> propertyMap = new HashMap<>();

    static  {
        Properties properties = new Properties();
        try (InputStream input = PropManedger.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
            properties.forEach((key, value) -> propertyMap.put((String) key, (String) value));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private String getProp(String propertyName) {
//        FileInputStream propertyFile;
//        Properties property = new Properties();
//
//        try {
//            propertyFile = new FileInputStream("src/main/resources/config.properties");
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            property.load(propertyFile);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return property.getProperty(propertyName);
//    }



}
