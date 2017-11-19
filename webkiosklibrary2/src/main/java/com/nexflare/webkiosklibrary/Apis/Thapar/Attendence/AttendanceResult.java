package com.nexflare.webkiosklibrary.Apis.Thapar.Login.Attendence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nexflare on 26/09/17.
 */

public class AttendanceResult {
    ArrayList<Attendance> attendanceList;

    public AttendanceResult() {
        this.attendanceList = new ArrayList<>();
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(ArrayList<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

}
