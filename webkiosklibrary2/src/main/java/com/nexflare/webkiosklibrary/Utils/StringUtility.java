package com.nexflare.webkiosklibrary.Utils;

import android.text.TextUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Dheerain on 26-09-2017.
 */

public class StringUtility {

    public static String cleanString(String string) {
        try {
            return string.replace("&nbsp;", "").replace("\u00A0", "").trim();
        } catch (Exception e) {
            return string;
        }
    }


    public static String getSubjectName(String completeName) {
        try {
            return cleanString(completeName).substring(0, completeName.indexOf('('));
        } catch (Exception e) {
            return completeName;
        }
    }

    public static String getSubjectCode(String completeName) {
        try {
            return cleanString(completeName).substring(completeName.indexOf("(") + 1, completeName.indexOf(")"));
        } catch (Exception e) {
            return completeName;
        }
    }


    public static String getSubjectNameFromAttendance(String completeName) {
        try {
            List<String> splitted = Arrays.asList(completeName.split("-"));
            return cleanString(TextUtils.join(" ", splitted.subList(0, splitted.size() - 1)));
        } catch (Exception e) {
            return completeName;
        }
    }


    public static String getSubjectCodeFromAttendance(String completeName) {
        try {
            List<String> splitted = Arrays.asList(completeName.split("-"));
            return cleanString(splitted.get(splitted.size() - 1));
        } catch (Exception e) {
            return completeName;
        }
    }


    public static Integer convertStringAttendanceToInteger(String attendance) {
        try {
            return (int) Double.parseDouble(attendance);
        } catch (Exception e) {
            return null;
        }
    }


    public static int convertStringToIntegerForDetailAttendance(String serialNumber) {
        return Integer.parseInt(cleanString(serialNumber.replace(".", "")));
    }
}