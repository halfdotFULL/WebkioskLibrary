package com.nexflare.webkiosklibrary.Apis.Jiit.Attendance;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nexflare on 26/09/17.
 */

public class AttendanceResult {
    ArrayList<Attendance> attendanceList;

    public AttendanceResult(ArrayList<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(ArrayList<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

}
