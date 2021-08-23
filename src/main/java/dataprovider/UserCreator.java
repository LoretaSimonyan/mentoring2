package dataprovider;

import model.User;
import utils.PropertiesReader;

public class UserCreator {
    static final private PropertiesReader propertiesReader = new PropertiesReader();

    public static User MAIN_USER(){
             return   new User.UserBuilder()
                .userEmail(propertiesReader.getUserEmail())
                .password(propertiesReader.getUserPassword())
                .firstName(propertiesReader.getUserName())
                .lastName(propertiesReader.getUserLastName())
                .build();
    }
}
