package com.nexflare.webkiosklibrary.Apis.Jiit.Cgpa;

import java.util.ArrayList;

/**
 * Created by Dheerain on 26-09-2017.
 */

public class CgpaReportResult {

    private ArrayList<CgpaReport> cgpaReports;

/*this class helps us to create a cgpa list , initialize here */
    public CgpaReportResult() {
        cgpaReports = new ArrayList<>();
    }
/*we get the list here*/
    public ArrayList<CgpaReport> getCgpaReports() {
        return cgpaReports;
    }
/*here we set the reasult we get when data crawled*/

    public void setCgpaReports(ArrayList<CgpaReport> cgpaReports) {
        this.cgpaReports = cgpaReports;
    }
}
