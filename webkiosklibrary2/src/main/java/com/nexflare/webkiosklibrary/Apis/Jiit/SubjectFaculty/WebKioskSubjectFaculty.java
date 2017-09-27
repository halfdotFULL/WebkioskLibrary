package com.nexflare.webkiosklibrary.Apis.Jiit.SubjectFaculty;

import android.os.Handler;
import android.os.Looper;

import com.nexflare.webkiosklibrary.Activity.Cookies;
import com.nexflare.webkiosklibrary.Exceptions.InvalidCredentialsException;
import com.nexflare.webkiosklibrary.Interface.ResultCallback;
import com.nexflare.webkiosklibrary.Interface.WebkioskContract;
import com.nexflare.webkiosklibrary.Utils.Constants;
import com.nexflare.webkiosklibrary.Utils.StringUtility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Comment;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;

/**
 * Created by Aakarshak on 27-09-2017.
 */

public class WebKioskSubjectFaculty  implements WebkioskContract<SubjectFacultyResult>{

    private ResultCallback<SubjectFacultyResult> mCallback;
    private Handler mResultHandler;
    public static final String URL = "https://webkiosk.jiit.ac.in/StudentFiles/Academic/StudSubjectFaculty.jsp";

    public WebKioskSubjectFaculty(){
        mResultHandler = new Handler(Looper.getMainLooper());
    }

    private WebKioskSubjectFaculty getSubjectFaculty(final String enrollmentNumber,final String dateOfBirth,final String password,final String college){

        final Thread thread = new Thread(){

            @Override
            public void run() {
                super.run();

                try{

                    Map<String,String> cookies = Cookies.getCookiesForJaypee(enrollmentNumber,dateOfBirth,password,college);

                    Document document = Jsoup
                                        .connect(URL)
                                        .cookies(cookies)
                                        .userAgent(Constants.AGENT_MOZILLA)
                                        .execute().parse();

                    final SubjectFacultyResult subjectFacultyResult = new SubjectFacultyResult();

                    if(document.body().toString().contains("session timeout")){

                        throw new InvalidCredentialsException();
                    }else{

                        Elements elements = document
                                .body()
                                .getElementsByTag("table")
                                .get(2).getElementsByTag("tbody")
                                .get(0).children();

                        //Traverse the list of subjects and parse them, while also
                        //cleaning up the text and removing occurrences of '&nbsp;'
                        //and remove ant occurrences of '\u00A0'
                        for (int x = 1; x < elements.size(); x++) {
                            //Create new SubjectFaculty object
                            SubjectFaculty subjectFaculty = new SubjectFaculty();

                            //Get the sub elements of each row
                            Elements subElements = elements.get(x).getElementsByTag("td");
                            subjectFaculty.setSubjectName(StringUtility.getSubjectName(subElements.get(1).text()));
                            subjectFaculty.setSubjectCode(StringUtility.getSubjectCode(subElements.get(1).text()));
                            subjectFaculty.setLectureFaculty(StringUtility.cleanString(subElements.get(2).text()));
                            subjectFaculty.setTutorialFaculty(StringUtility.cleanString(subElements.get(3).text()));
                            subjectFaculty.setPracticalFaculty(StringUtility.cleanString(subElements.get(4).text()));

                            //Add to subject faculty result
                            subjectFacultyResult.getSubjectFaculties().add(subjectFaculty);
                        }
                    }

                    //Check if user has provided  a call back
                    if(mCallback!=null){
                        mResultHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mCallback.onResult(subjectFacultyResult);
                            }
                        });
                    }



                }catch (final Exception e){
                    e.printStackTrace();

                    if(mCallback != null){
                        mResultHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mCallback.onError(e);
                            }
                        });
                    }
                }
            }
        };
        thread.start();

        return this;
    }
    @Override
    public void addResultCallback(ResultCallback<SubjectFacultyResult> resultCallback) {

    }

    @Override
    public void removeCallback() {

    }
}
