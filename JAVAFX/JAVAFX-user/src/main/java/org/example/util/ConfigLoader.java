package org.example.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream in = ConfigLoader.class.getResourceAsStream("/application.properties")) {
            if (in != null) {
                properties.load(in);
            } else {
                System.err.println("application.properties not found on classpath. "
                        + "Copy application.properties.example to application.properties and fill in your values.");
            }
        } catch (IOException e) {
            System.err.println("Failed to load application.properties: " + e.getMessage());
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null || value.isBlank()) {
            value = System.getenv(key);
        }
        if (value == null || value.isBlank()) {
            value = System.getenv(key.toUpperCase().replace('.', '_'));
        }
        return value;
    }
}
