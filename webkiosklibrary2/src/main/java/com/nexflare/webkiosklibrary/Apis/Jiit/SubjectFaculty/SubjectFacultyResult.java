package com.nexflare.webkiosklibrary.Apis.Jiit.SubjectFaculty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aakarshak on 27-09-2017.
 */

public class SubjectFacultyResult {
    private List<SubjectFaculty> subjectFaculties;

    public SubjectFacultyResult() {
        subjectFaculties = new ArrayList<>();
    }

    public List<SubjectFaculty> getSubjectFaculties() {
        return subjectFaculties;
    }

    public void setSubjectFaculties(List<SubjectFaculty> subjectFaculties) {
        this.subjectFaculties = subjectFaculties;
    }
}
