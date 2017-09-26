package com.nexflare.webkiosklibrary.Apis.Jiit.Cgpa;

import android.os.Handler;
import android.os.Looper;

import com.nexflare.webkiosklibrary.Activity.Cookies;
import com.nexflare.webkiosklibrary.Apis.Jiit.WebkioskCredentials;
import com.nexflare.webkiosklibrary.Interface.ResultCallback;
import com.nexflare.webkiosklibrary.Interface.WebkioskContract;
import com.nexflare.webkiosklibrary.Utils.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;

/**
 * Created by Dheerain on 26-09-2017.
 */

public class WebkioskCgpaReport implements WebkioskContract<CgpaReportResult>{

        private ResultCallback<CgpaReportResult> mCallback;
        private Handler mResultHandler;
        private static String URL = "https://webkiosk.jiit.ac.in/StudentFiles/Exam/StudCGPAReport.jsp";

        public WebkioskCgpaReport() {
            mResultHandler = new Handler(Looper.getMainLooper());
            /*as thread normally doesn't have a looper inbuilt
            * it finishes after the task is over
            * to recieve the call back we need to impliment the looper for the message que to be implimented*/
        }
            /*we first need to validate the user to gett the cgpa and spa ,
            *so here we use cookies to get the user loged in once and then use
            * the cookies to get hte user verified every time */

        public WebkioskCgpaReport getCgpaReport(final String enrollmentNumber, final String dateOfBirth, final String password,final String college, final String url){

            /*running on another thread because crawling is a blocking event*/

            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    try{

                        /*get cookies here after login*/
                        Map<String, String> cookies = Cookies.getCookiesForJaypee(enrollmentNumber, dateOfBirth, password,college);

                        Document document = Jsoup.connect(url)
                                .cookies(cookies)
                                .userAgent(Constants.AGENT_MOZILLA)
                                .execute().parse();
                        /*get the html page here in the document */

                        final CgpaReportResult cgpaReportResult = new CgpaReportResult();
                        /*check if cookies went stale fo  that the user can be validated again*/
                        if (document.body().toString().toLowerCase().contains("session timeout")) {
                            throw new InvalidCredentialsException();
                        } else {
                            Elements elements = document.body()
                                    .getElementsByTag("table")
                                    .get(2).getElementsByTag("tbody")
                                    .get(0).children();
                            /*get the table which contains the content here by analyzing the DOM*/

                            for (Element element : elements) {
                                Elements subElements = element.children();
                                /*traverse element by element to get the data*/
                                CgpaReport cgpaReport = new CgpaReport();
                                cgpaReport.setSemesterIndex(Integer.parseInt(StringUtility.cleanString(subElements.get(0).text())));
                                cgpaReport.setGradePoints(Double.parseDouble(StringUtility.cleanString(subElements.get(1).text())));
                                cgpaReport.setCourseCredit(Double.parseDouble(StringUtility.cleanString(subElements.get(2).text())));
                                cgpaReport.setEarnedCredit(Double.parseDouble(StringUtility.cleanString(subElements.get(3).text())));
                                cgpaReport.setPointsSecuredSgpa(Double.parseDouble(StringUtility.cleanString(subElements.get(4).text())));
                                cgpaReport.setPointsSecuredCgpa(Double.parseDouble(StringUtility.cleanString(subElements.get(5).text())));
                                cgpaReport.setSgpa(Double.parseDouble(StringUtility.cleanString(subElements.get(6).text())));
                                cgpaReport.setCgpa(Double.parseDouble(StringUtility.cleanString(subElements.get(7).text())));

                                cgpaReportResult.getCgpaReports().add(cgpaReport);

                                /*creating the result arraylist*/
                            }

                        }

                        /*check if user has provided the callbak for the result of the api called using the library*/
                        mResultHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (mCallback != null) {
                                    mCallback.onResult(cgpaReportResult);
                                }
                            }
                        });

                    } catch (final Exception e) {
                        e.printStackTrace();

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


    public WebkioskCgpaReport getCgpaReport(WebkioskCredentials credentials) {
        getCgpaReport(credentials.getEnrollmentNumber(), credentials.getDateOfBirth(), credentials.getPassword(),credentials.getCollege(), URL);
        return this;
    }

    @Override
    public void addResultCallback(ResultCallback<CgpaReportResult> resultCallback) {
        this.mCallback = resultCallback;

    }


    @Override
    public void removeCallback() {
        this.mCallback = null;
    }
}