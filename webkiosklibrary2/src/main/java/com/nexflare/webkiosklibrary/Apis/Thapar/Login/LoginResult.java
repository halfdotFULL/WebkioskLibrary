package com.nexflare.webkiosklibrary.Apis.Thapar.Login;

/**
 * Created by Aakarshak on 26-09-2017.
 */

public class LoginResult {
    private boolean validCredentials;

    public boolean isValidCredentials(){
        return validCredentials;
    }
    public void setValidCredentials(boolean validCredentials) {
        this.validCredentials = validCredentials;
    }
}
