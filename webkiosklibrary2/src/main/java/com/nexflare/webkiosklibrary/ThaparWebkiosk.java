package com.nexflare.webkiosklibrary;

import com.nexflare.webkiosklibrary.Apis.Thapar.Login.WebKioskLogin;

/**
 * Created by Aakarshak on 02-10-2017.
 */

public class ThaparWebkiosk {
    public static WebKioskLogin getLoginApi(){
        return new WebKioskLogin();
    }
}
