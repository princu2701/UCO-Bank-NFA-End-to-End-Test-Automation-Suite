package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Propertyreader {
    private static Properties properties;

        static {
            try {
                FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/ConfigFiles/data.properties");
                properties = new Properties();
                properties.load(file);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static String get(String key) {
            return properties.getProperty(key);
        }

}
