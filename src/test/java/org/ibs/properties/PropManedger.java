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



}
