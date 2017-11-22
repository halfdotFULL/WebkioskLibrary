package com.nexflare.webkiosklibrary.Apis.Thapar.Grades;

import android.os.Handler;
import android.os.Looper;

import com.nexflare.webkiosklibrary.Activity.Cookies;
import com.nexflare.webkiosklibrary.Apis.Jiit.Grades.Grades;
import com.nexflare.webkiosklibrary.Apis.Jiit.Grades.GradesResult;
import com.nexflare.webkiosklibrary.Exceptions.InvalidCredentialsException;
import com.nexflare.webkiosklibrary.Interface.ResultCallback;
import com.nexflare.webkiosklibrary.Interface.WebkioskContract;
import com.nexflare.webkiosklibrary.Utils.Constants;
import com.nexflare.webkiosklibrary.Utils.StringUtility;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;


/**
 * Created by nexflare on 28/09/17.
 */

public class WebkioskGrades implements WebkioskContract<GradesResult>{
    private Handler mResultHandler;
    ResultCallback<GradesResult> resultCallback;
    String url="https://webkiosk.thapar.edu/StudentFiles/Exam/StudentEventGradesView.jsp";

    public WebkioskGrades() {
        mResultHandler=new Handler(Looper.getMainLooper());
    }

    public WebkioskGrades getGrades(final String enrollmentNumber, final String password, final String dateOfBirth, final String college, final String examCode){
        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    url=url+ Constants.URL_QUERY_PARAM+examCode;
                    Map<String,String> cookies= Cookies.getCookiesForJaypee(enrollmentNumber,dateOfBirth,password,college);

                    Document document= Jsoup.connect(url)
                            .cookies(cookies)
                            .execute()
                            .parse();
                    //This is the final result to be returned to user
                    final GradesResult gradesResult=new GradesResult();
                    if (document.body().toString().toLowerCase().contains("session timeout")) {
                        //Throw invalid credentials exception
                        throw new InvalidCredentialsException();
                    }
                    else{
                        //
                        Elements elements = document.body()
                                .getElementsByTag("table")
                                .get(2)
                                .getElementsByTag("tbody")
                                .get(0)
                                .children();

                        //Iterate and fetch the values
                        for (Element element : elements) {
                            Elements subElements = element.children();
                            com.nexflare.webkiosklibrary.Apis.Jiit.Grades.Grades examGrade = new Grades();
                            examGrade.setSubjectName(StringUtility.getSubjectName(subElements.get(1).text()));
                            examGrade.setSubjectCode(StringUtility.getSubjectCode(subElements.get(1).text()));
                            examGrade.setExamCode(StringUtility.cleanString(subElements.get(2).text()));
                            examGrade.setExamGrade(StringUtility.cleanString(subElements.get(3).text()));
                            gradesResult.getGradesList().add(examGrade);
                        }

                    }
                    mResultHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            resultCallback.onResult(gradesResult);
                        }
                    });
                } catch (final Exception e) {
                    e.printStackTrace();
                    mResultHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            resultCallback.onError(e);
                        }
                    });
                }
            }
        };
        thread.start();

        return this;
    }

    @Override
    public void addResultCallback(ResultCallback<GradesResult> resultCallback) {
        this.resultCallback=resultCallback;
    }

    @Override
    public void removeCallback() {
        this.resultCallback=null;
    }
}
