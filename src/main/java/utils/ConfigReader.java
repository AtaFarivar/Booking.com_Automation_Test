package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigReader {
        private static Properties properties;

        // Static block → executes once when the class is loaded
        static {
            try {
                String path = "src/main/resources/Config.properties";
                FileInputStream inputStream = new FileInputStream(path);
                properties = new Properties();
                properties.load(inputStream);
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("❌ Failed to load Config.properties file!", e);
            }
        }

        // Method to read any key
        public static String get(String key) {
            String value = properties.getProperty(key);
            if (value == null || value.isEmpty()) {
                throw new RuntimeException("⚠️ Property '" + key + "' not found in Config.properties!");
            }
            return value.trim();
        }
    }

