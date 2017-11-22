package com.nexflare.webkiosklibrary.Apis.Thapar.Grades;

import com.nexflare.webkiosklibrary.Apis.Jiit.Grades.Grades;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nexflare on 28/09/17.
 */

public class GradesResult {
    List<com.nexflare.webkiosklibrary.Apis.Jiit.Grades.Grades> gradesList;

    public GradesResult() {
        this.gradesList = new ArrayList<>();
    }

    public List<com.nexflare.webkiosklibrary.Apis.Jiit.Grades.Grades> getGradesList() {
        return gradesList;
    }

    public void setGradesList(List<Grades> gradesList) {
        this.gradesList = gradesList;
    }
}
