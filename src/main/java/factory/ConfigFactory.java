package factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFactory {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigFactory.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("application.properties não encontrado no classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar arquivo de configuração", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}

