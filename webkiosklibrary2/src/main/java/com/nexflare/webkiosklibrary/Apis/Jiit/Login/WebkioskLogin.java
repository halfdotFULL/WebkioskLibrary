package com.nexflare.webkiosklibrary.Apis.Jiit.Login;

import android.os.Handler;
import android.os.Looper;

import com.nexflare.webkiosklibrary.Activity.Cookies;
import com.nexflare.webkiosklibrary.Apis.Jiit.WebkioskCredentials;
import com.nexflare.webkiosklibrary.Interface.ResultCallback;
import com.nexflare.webkiosklibrary.Interface.WebkioskContract;
import com.nexflare.webkiosklibrary.Utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * Created by shubhamagarwal on 26/09/17.
 */


public class WebkioskLogin implements WebkioskContract<LoginResult> {
    private ResultCallback<LoginResult> mCallback;
    private Handler mResultHandler;

    public WebkioskLogin() {
        mResultHandler = new Handler(Looper.getMainLooper());
    }

    public WebkioskLogin login(final String enrollmentNumber, final String dateOfBirth, final String password, final String college) {

        Thread thread = new Thread() {

            @Override
            public void run() {
                super.run();

                try {
                    Map<String, String> cookies = Cookies.getCookiesForJaypee(enrollmentNumber, dateOfBirth , password, college);
                    Document document = Jsoup.connect("https;//webkiosk.jiit.ac.in/StudentFiles/StudentPage.jsp")
                            .cookies(cookies)
                            .userAgent(Constants.AGENT_MOZILLA)
                            .execute().parse();

                    final LoginResult loginResult = new LoginResult();


                    if (document.toString().contains("FrameLeftStudent.jsp")) {

                        loginResult.setValidCredentials(true);
                    } else {
                        loginResult.setValidCredentials(false);
                    }

                    mResultHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mCallback != null) {
                                mCallback.onResult(loginResult);
                            }
                        }
                    });

                } catch (final Exception e) {
                    e.printStackTrace();

                    //Check if callback is provided
                    mResultHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (mCallback != null) {
                                mCallback.onError(e);
                            }
                        }
                    });
                }
            }
        };
        thread.start();


        return this;
    }

    public WebkioskLogin login(WebkioskCredentials credentials) {
        login(credentials.getEnrollmentNumber(), credentials.getDateOfBirth(), credentials.getPassword(), credentials.getCollege());
        return this;
    }


    @Override
    public void addResultCallback(ResultCallback<LoginResult> callback) {
        this.mCallback = callback;
    }


    @Override
    public void removeCallback() {
        this.mCallback = null;
    }
}
