package AdminServer;

import java.io.Serializable;

public class Admins implements Serializable {
    private String admin_id;
    private String admin_firstname;
    private String admin_lastname;
    private String admin_login;
    private String admin_password;
    private String admin_email;

    public Admins(){

    }

    public Admins( String admin_firstname, String admin_lastname, String admin_login, String admin_password, String admin_email) {

        this.admin_firstname = admin_firstname;
        this.admin_lastname = admin_lastname;
        this.admin_login = admin_login;
        this.admin_password = admin_password;
        this.admin_email = admin_email;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_firstname() {
        return admin_firstname;
    }

    public void setAdmin_firstname(String admin_firstname) {
        this.admin_firstname = admin_firstname;
    }

    public String getAdmin_lastname() {
        return admin_lastname;
    }

    public void setAdmin_lastname(String admin_lastname) {
        this.admin_lastname = admin_lastname;
    }

    public String getAdmin_login() {
        return admin_login;
    }

    public void setAdmin_login(String admin_login) {
        this.admin_login = admin_login;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }
}
