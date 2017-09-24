package com.nexflare.webkiosklibrary.Activity;

import com.nexflare.webkiosklibrary.Utils.Constants;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Dheerain on 24-09-2017.
 */

public class Cookies {


    public static Map<String, String> getCookiesForJaypee(String enrollmentNumber, String dateOfBirth, String password, String college) throws IOException {
        Connection.Response login = Jsoup.connect(Constants.BASE_URL_JIIT)
                .method(Connection.Method.GET)
                .userAgent(Constants.AGENT_MOZILLA)
                .execute();

        Connection.Response studentPage = Jsoup.connect(Constants.BASE_URL_JIIT_LOGIN)
                .data("txtInst", "Institute")
                .data("InstCode", college)
                .data("txtuType", "Member Type")
                .data("UserType", "S")
                .data("txtCode", "Enrollment No")
                .data("MemberCode", enrollmentNumber)
                .data("DOB", "DOB")
                .data("DATE1", dateOfBirth)
                .data("txtPin", "Password/Pin")
                .data("Password", password)
                .data("BTNSubmit", "Submit")
                .cookies(login.cookies())
                .execute();

        return studentPage.cookies();
    }



    public static Map<String, String> getCookiesForThapar(String enrollmentNumber, String password) throws IOException {
        Connection.Response login = Jsoup.connect(Constants.BASE_URL_THAPAR)
                .method(Connection.Method.GET)
                .userAgent(Constants.AGENT_MOZILLA)
                .execute();

        Connection.Response studentPage = Jsoup.connect(Constants.BASE_URL_THAPAR_LOGIN)
                .data("txtuType", "Member Type")
                .data("UserType", "S")
                .data("txtCode", "Enrollment No")
                .data("MemberCode", enrollmentNumber)
                .data("txtPin", "Password/Pin")
                .data("Password", password)
                .data("BTNSubmit", "Submit")
                .cookies(login.cookies())
                .execute();

        return studentPage.cookies();
    }

    public Cookies() throws IOException {
    }


}
