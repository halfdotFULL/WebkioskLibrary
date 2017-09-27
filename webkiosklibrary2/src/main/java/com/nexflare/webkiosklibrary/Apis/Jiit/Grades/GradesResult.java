package com.nexflare.webkiosklibrary.Apis.Jiit.Grades;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nexflare on 28/09/17.
 */

public class GradesResult {
    List<Grades> gradesList;

    public GradesResult() {
        this.gradesList = new ArrayList<>();
    }

    public List<Grades> getGradesList() {
        return gradesList;
    }

    public void setGradesList(List<Grades> gradesList) {
        this.gradesList = gradesList;
    }
}
