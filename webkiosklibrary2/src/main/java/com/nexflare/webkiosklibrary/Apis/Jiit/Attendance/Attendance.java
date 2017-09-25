package com.nexflare.webkiosklibrary.Apis.Jiit.Attendance;

/**
 * Created by nexflare on 26/09/17.
 */

public class Attendance {
    private String subjectName;
    private String subjectCode;
    private Integer lectureAttendace;
    private Integer tutorialAttendance;
    private Integer practicalAttendance;
    private Integer overallAttendance;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Integer getLectureAttendace() {
        return lectureAttendace;
    }

    public void setLectureAttendace(Integer lectureAttendace) {
        this.lectureAttendace = lectureAttendace;
    }

    public Integer getTutorialAttendance() {
        return tutorialAttendance;
    }

    public void setTutorialAttendance(Integer tutorialAttendance) {
        this.tutorialAttendance = tutorialAttendance;
    }

    public Integer getPracticalAttendance() {
        return practicalAttendance;
    }

    public void setPracticalAttendance(Integer practicalAttendance) {
        this.practicalAttendance = practicalAttendance;
    }

    public Integer getOverallAttendance() {
        return overallAttendance;
    }

    public void setOverallAttendance(Integer overallAttendance) {
        this.overallAttendance = overallAttendance;
    }
}
