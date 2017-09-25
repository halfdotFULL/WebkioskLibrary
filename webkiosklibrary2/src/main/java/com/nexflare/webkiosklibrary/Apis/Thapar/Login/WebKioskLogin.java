package com.nexflare.webkiosklibrary.Apis.Thapar.Login;

import android.os.Handler;
import android.os.Looper;

import com.nexflare.webkiosklibrary.Activity.Cookies;
import com.nexflare.webkiosklibrary.Interface.ResultCallback;
import com.nexflare.webkiosklibrary.Interface.WebkioskContract;
import com.nexflare.webkiosklibrary.Utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.Map;

/**
 * Created by Aakarshak on 26-09-2017.
 */

public class WebKioskLogin implements WebkioskContract<LoginResult>{

    private ResultCallback<LoginResult> mCallBback;
    private Handler mResultHandler;

    public WebKioskLogin(){
        mResultHandler = new Handler(Looper.getMainLooper());
    }

    /*
    * Login in into www.webkiosk.thapar.edu
    * Get the cookies and hit the https://webkiosk.thapar.edu/StudentFiles/StudentPage.jsp url
    * to check if the credentials provided are right or not
    * */
    public WebKioskLogin login(final String enrollmentNumber, final String dateOfBirth, final String password, final String college){
        //Different thread

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();

                try{
                    //Get Cookies from site.
                    Map<String , String> cookies = Cookies.getCookiesForThapar(enrollmentNumber, password);

                    //Login into webkiosk

                    Document document = Jsoup.connect("https://webkiosk.jiit.ac.in/StudentFiles/StudentPage.jsp")
                                            .cookies(cookies)
                                            .userAgent(Constants.AGENT_MOZILLA)
                                            .execute().parse();

                    //Create new login result
                    final LoginResult loginResult = new LoginResult();

                    //check for desired string in the returned web page

                    if(document.toString().contains("FrameLeftStudent.jsp")){
                        loginResult.setValidCredentials(true);
                    }
                    else{
                        loginResult.setValidCredentials(false);
                    }

                    mResultHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(mCallBback != null){
                                mCallBback.onResult(loginResult);
                            }
                        }
                    });

                }catch (final Exception e){
                    e.printStackTrace();

                    mResultHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(mCallBback != null){
                                mCallBback.onError(e);
                            }
                        }
                    });
                }
            }
        };
        thread.start();

        return this;
    }
    @Override
    public void addResultCallback(ResultCallback<LoginResult> resultCallback) {
        this.mCallBback = resultCallback;
    }

    @Override
    public void removeCallback() {
        this.mCallBback = null;
    }
}
