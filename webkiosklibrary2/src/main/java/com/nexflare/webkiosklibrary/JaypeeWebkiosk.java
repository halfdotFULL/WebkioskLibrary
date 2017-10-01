package com.nexflare.webkiosklibrary;

import com.nexflare.webkiosklibrary.Apis.Jiit.Attendance.WebkioskAttendance;
import com.nexflare.webkiosklibrary.Apis.Jiit.Cgpa.WebkioskCgpaReport;
import com.nexflare.webkiosklibrary.Apis.Jiit.Grades.WebkioskGrades;
import com.nexflare.webkiosklibrary.Apis.Jiit.Login.WebkioskLogin;
import com.nexflare.webkiosklibrary.Apis.Jiit.SubjectFaculty.WebKioskSubjectFaculty;

/**
 * Created by nexflare on 01/10/17.
 */

public class JaypeeWebkiosk {
    public static WebkioskLogin getLoginApi(){
        return new WebkioskLogin();
    }
    public static WebkioskCgpaReport getCgpaApi(){
        return new WebkioskCgpaReport();
    }
    public static WebkioskGrades getGradesApi(){
        return new WebkioskGrades();
    }
    public static WebkioskAttendance getAttendanceApi(){
        return new WebkioskAttendance();
    }
    public static WebKioskSubjectFaculty getSubjectFacultyApi(){
        return new WebKioskSubjectFaculty();
    }

}
