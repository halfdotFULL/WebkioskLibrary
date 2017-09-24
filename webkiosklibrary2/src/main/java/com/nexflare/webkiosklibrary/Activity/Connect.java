package com.nexflare.webkiosklibrary.Activity;

import com.nexflare.webkiosklibrary.Utils.Constants;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Dheerain on 24-09-2017.
 */

public class Connect {


    public static Map<String, String> getCookiesForJaypee(String enrollmentNumber, String dateOfBirth, String password, String college) throws IOException {
        Connection.Response login = Jsoup.connect(Constants.BASE_URL_WEBKISOK)
                .method(Connection.Method.GET)
                .userAgent(Constants.AGENT_MOZILLA)
                .execute();

        Connection.Response studentPage = Jsoup.connect(Constants.BASE_URL_WEBKISOK_Login)
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



    Document documentThapar = Jsoup.connect(Constants.BASE_URL_THAPAR)
            .data("cookieexists", "true")
            .data("MemberCode", "15103350")
            .data("login", "Login")
            .cookies(loginForm.cookies())
            .post();

    public Connect() throws IOException {
    }


}
