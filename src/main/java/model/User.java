package model;

public class User {

    private  String firstName;
    private  String lastName;
    private  String userEmail;
    private  String userPassword;

    public User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.userEmail = builder.userEmail;
        this.userPassword = builder.password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public String getUserPassword(){
        return userPassword;
    }

    @Override
    public String toString() {
        return "User: " + this.firstName + ", " + this.lastName + ", " +this.userEmail;
    }
    public static class UserBuilder {
        private  String firstName;
        private  String lastName;
        private String userEmail;
        private String password;

        public UserBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder userEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }
        public User build() {
            return new User(this);
        }

    }
}
