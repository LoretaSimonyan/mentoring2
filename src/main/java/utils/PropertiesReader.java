package utils;

import lombok.Getter;
import lombok.SneakyThrows;

import java.io.FileReader;
import java.util.Properties;

@Getter
public class PropertiesReader {
    FileReader reader;
    private String userEmail;
    private String userPassword;
    private String otherUserEmail;
    private String userName;
    private String userLastName;
    private String baseURL;
    Properties properties;

    public PropertiesReader() {
        readUserProperties();
    }

    @SneakyThrows
    private void readUserProperties() {
        reader = new FileReader("src/main/resources/qa.properities");
        properties = new Properties();
        properties.load(reader);
        userEmail = properties.getProperty("USER_EMAIL");
        userPassword = properties.getProperty("USER_PASSWORD");
        otherUserEmail = properties.getProperty("OTHER_USER_EMAIL");
        userName = properties.getProperty("USER_NAME");
        userLastName = properties.getProperty("USER_LAST_NAME");
        baseURL = properties.getProperty("BASE_URL");
    }
}
