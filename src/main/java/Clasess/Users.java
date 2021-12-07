package Clasess;

import java.io.Serializable;

public class Users implements Serializable {
    private String user_id;
    private String user_firstname;
    private String user_lastname;
    private String user_login;
    private String user_password;
    private String user_email;
    private String user_gender;
    private String user_location;

    public Users() {

    }

    @Override
    public String toString() {
        return "Users{" +
                "user_id='" + user_id + '\'' +
                ", user_firstname='" + user_firstname + '\'' +
                ", user_lastname='" + user_lastname + '\'' +
                ", user_login='" + user_login + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_gender='" + user_gender + '\'' +
                ", user_location='" + user_location + '\'' +
                '}';
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_firstname() {
        return user_firstname;
    }

    public void setUser_firstname(String user_firstname) {
        this.user_firstname = user_firstname;
    }

    public String getUser_lastname() {
        return user_lastname;
    }

    public void setUser_lastname(String user_lastname) {
        this.user_lastname = user_lastname;
    }

    public String getUser_login() {
        return user_login;
    }

    public void setUser_login(String user_login) {
        this.user_login = user_login;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(String user_gender) {
        this.user_gender = user_gender;
    }

    public String getUser_location() {
        return user_location;
    }

    public void setUser_location(String user_location) {
        this.user_location = user_location;
    }

    public Users(String user_firstname, String user_lastname, String user_login, String user_password, String user_email, String user_gender, String user_location) {
        this.user_firstname = user_firstname;
        this.user_lastname = user_lastname;
        this.user_login = user_login;
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_gender = user_gender;
        this.user_location = user_location;
    }
}
