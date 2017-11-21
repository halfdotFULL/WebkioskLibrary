package com.nexflare.webkiosklibrary.Apis.Jiit.Attendance;

import android.os.Handler;
import android.os.Looper;

import com.nexflare.webkiosklibrary.Activity.Cookies;
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
 * Created by nexflare on 26/09/17.
 */

public class WebkioskAttendance implements WebkioskContract<AttendanceResult> {

    private ResultCallback<AttendanceResult> resultCallback;
    private static String URL = "https://webkiosk.jiit.ac.in/StudentFiles/Academic/StudentAttendanceList.jsp";
    private Handler mResultHandler;
    public WebkioskAttendance(){
        mResultHandler=new Handler(Looper.getMainLooper());
    }
    public WebkioskAttendance getAttendance(final String enrollmentNumber, final String dateOfBirth,final String password, final String college){
        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    //Get the cookies from Webkiosk's website
                    Map<String, String> cookies = Cookies.getCookiesForJaypee(enrollmentNumber, dateOfBirth, password,college);

                    //Login into webkiosk using the cookies
                    Document document = Jsoup.connect(URL)
                            .cookies(cookies)
                            .userAgent(Constants.AGENT_MOZILLA)
                            .execute().parse();

                    //Create new subject result
                    final AttendanceResult attendanceResult = new AttendanceResult();

                    //Check if the returned web page contains the string "Signin Action"
                    //if yes then login was unsuccessful
                    if (document.body().toString().toLowerCase().contains("session timeout")) {
                        //Throw invalid credentials exception
                        throw new InvalidCredentialsException();
                    } else {
                        //Traverse the html tree to fetch the content
                        Elements elements = document.body()
                                .getElementsByTag("table")
                                .get(2)
                                .getElementsByTag("tbody")
                                .get(0)
                                .children();

                        //Iterate and fetch the attendance values
                        for (Element element : elements) {
                            Elements subElements = element.children();

                            Attendance attendance = new Attendance();
                            attendance.setSubjectName(StringUtility.getSubjectNameFromAttendance(subElements.get(1).text()));
                            attendance.setSubjectCode(StringUtility.getSubjectCodeFromAttendance(subElements.get(1).text()));

                            attendance.setOverallAttendance(StringUtility.convertStringAttendanceToInteger(subElements.get(2).text()));
                            attendance.setLectureAttendace(StringUtility.convertStringAttendanceToInteger(subElements.get(3).text()));
                            attendance.setTutorialAttendance(StringUtility.convertStringAttendanceToInteger(subElements.get(4).text()));
                            attendance.setPracticalAttendance(StringUtility.convertStringAttendanceToInteger(subElements.get(5).text()));

                            //Set the link for detailed attendance
                            attendance.setDetailAttendanceUrl(Constants.INITIAL_URL_JIIT_ACADEMIC + subElements.get(2).getElementsByTag("a").attr("href"));

                            //Check if the lecture, tutorial and overall attendance is null i.e. it is a practical subject
                            if (attendance.getOverallAttendance() == null
                                    && attendance.getLectureAttendace() == null
                                    && attendance.getTutorialAttendance() == null) {
                                attendance.setOverallAttendance(attendance.getPracticalAttendance());

                                //Check if the url is not null
                                String detailAttendanceUrl = StringUtility.cleanString(subElements.get(5).getElementsByTag("a").attr("href"));

                                if (detailAttendanceUrl != null && detailAttendanceUrl.length() != 0) {
                                    attendance.setDetailAttendanceUrl(Constants.INITIAL_URL_JIIT_ACADEMIC + detailAttendanceUrl);
                                } else {
                                    attendance.setDetailAttendanceUrl(null);
                                }
                            }

                            attendanceResult.getAttendanceList().add(attendance);
                        }
                    }

                    //Check if user has provided a callback
                    mResultHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (resultCallback != null) {
                                resultCallback.onResult(attendanceResult);
                            }
                        }
                    });

                }
                catch (Exception e){

                }
            }
        };
        return this;
    }
    @Override
    public void addResultCallback(ResultCallback<AttendanceResult> resultCallback) {
        this.resultCallback=resultCallback;
    }

    @Override
    public void removeCallback() {
        this.resultCallback=null;
    }
}
