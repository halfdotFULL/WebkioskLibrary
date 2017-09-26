package com.nexflare.webkiosklibrary.Apis.Jiit;

/**
 * Created by Dheerain on 26-09-2017.
 */

public class WebkioskCredentials {

    private String enrollmentNumber;
    private String dateOfBirth;
    private String password;
    private String college;

    public WebkioskCredentials(String enrollmentNumber, String dateOfBirth, String password, String college) {
        this.enrollmentNumber = enrollmentNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.college = college;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }


    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}