package com.nexflare.webkiosklibrary.Exceptions;

/**
 * Created by Dheerain on 27-09-2017.
 */

public class InvalidCredentialsException extends Exception {

    /*this class is there to throw the error exceptions to the various
    errors that may occur at the time of login */

    @Override
    public String getMessage() {
        return "Invalid Credentials Provided. Please check your enrollment number, date of birth and password.";
    }

    @Override
    public String toString() {
        return "Invalid Credentials Provided";
    }
}