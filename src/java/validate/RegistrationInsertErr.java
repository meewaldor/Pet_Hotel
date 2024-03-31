/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validate;

/**
 *
 * @author Admin
 */
public class RegistrationInsertErr {
     private String emailIsEmpty;
    private String emailIsExisted;
    private String passwordIsEmpty;
    private String firstNameIsEmpty;
    private String lastNameIsEmpty;
    private String phoneIsEmpty;
    private String phoneNotValid;
    private String confirmPasswordNotValid;

    public RegistrationInsertErr() {
    }

    public RegistrationInsertErr(String emailIsEmpty, String emailIsExisted, String passwordIsEmpty, String firstNameIsEmpty, String lastNameIsEmpty, String phoneIsEmpty, String phoneNotValid, String confirmPasswordNotValid) {
        this.emailIsEmpty = emailIsEmpty;
        this.emailIsExisted = emailIsExisted;
        this.passwordIsEmpty = passwordIsEmpty;
        this.firstNameIsEmpty = firstNameIsEmpty;
        this.lastNameIsEmpty = lastNameIsEmpty;
        this.phoneIsEmpty = phoneIsEmpty;
        this.phoneNotValid = phoneNotValid;
        this.confirmPasswordNotValid = confirmPasswordNotValid;
    }

    public String getEmailIsEmpty() {
        return emailIsEmpty;
    }

    public void setEmailIsEmpty(String emailIsEmpty) {
        this.emailIsEmpty = emailIsEmpty;
    }

    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }

    public String getPasswordIsEmpty() {
        return passwordIsEmpty;
    }

    public void setPasswordIsEmpty(String passwordIsEmpty) {
        this.passwordIsEmpty = passwordIsEmpty;
    }

    public String getFirstNameIsEmpty() {
        return firstNameIsEmpty;
    }

    public void setFirstNameIsEmpty(String firstNameIsEmpty) {
        this.firstNameIsEmpty = firstNameIsEmpty;
    }

    public String getLastNameIsEmpty() {
        return lastNameIsEmpty;
    }

    public void setLastNameIsEmpty(String lastNameIsEmpty) {
        this.lastNameIsEmpty = lastNameIsEmpty;
    }

    public String getPhoneIsEmpty() {
        return phoneIsEmpty;
    }

    public void setPhoneIsEmpty(String phoneIsEmpty) {
        this.phoneIsEmpty = phoneIsEmpty;
    }

    public String getConfirmPasswordNotValid() {
        return confirmPasswordNotValid;
    }

    public void setConfirmPasswordNotValid(String confirmPasswordNotValid) {
        this.confirmPasswordNotValid = confirmPasswordNotValid;
    }

    public String getPhoneNotValid() {
        return phoneNotValid;
    }

    public void setPhoneNotValid(String phoneNotValid) {
        this.phoneNotValid = phoneNotValid;
    }

}
