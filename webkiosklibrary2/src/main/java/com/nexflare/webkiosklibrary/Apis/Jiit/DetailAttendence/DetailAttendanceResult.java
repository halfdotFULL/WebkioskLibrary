package com.nexflare.webkiosklibrary.Apis.Jiit.DetailAttendence;

import java.util.ArrayList;

/**
 * Created by Dheerain on 27-09-2017.
 */

public class DetailAttendanceResult {

    private ArrayList<DetailAttendance> detailAttendances;

    public DetailAttendanceResult() {
        detailAttendances = new ArrayList<>();
    }

    public ArrayList<DetailAttendance> getDetailAttendences() {
        return detailAttendances;
    }

    public void setDetailAttendences(ArrayList<DetailAttendance> detailAttendances) {
        this.detailAttendances = detailAttendances;
    }
}
