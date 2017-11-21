package com.nexflare.webkiosklibrary.Apis.Thapar.Cgpa;

/**
 * Created by Dheerain on 26-09-2017.
 */

public class CgpaReport {

    /*Pojo to get the  CgpaReport*/
        private int semesterIndex;
        private double gradePoints;
        private double courseCredit;
        private double earnedCredit;
        private double pointsSecuredSgpa;
        private double pointsSecuredCgpa;
        private double sgpa;
        private double cgpa;

        public int getSemesterIndex() {
            return semesterIndex;
        }

        public void setSemesterIndex(int semesterIndex) {
            this.semesterIndex = semesterIndex;
        }

        public double getGradePoints() {
            return gradePoints;
        }

        public void setGradePoints(double gradePoints) {
            this.gradePoints = gradePoints;
        }

        public double getCourseCredit() {
            return courseCredit;
        }

        public void setCourseCredit(double courseCredit) {
            this.courseCredit = courseCredit;
        }

        public double getEarnedCredit() {
            return earnedCredit;
        }

        public void setEarnedCredit(double earnedCredit) {
            this.earnedCredit = earnedCredit;
        }

        public double getPointsSecuredSgpa() {
            return pointsSecuredSgpa;
        }

        public void setPointsSecuredSgpa(double pointsSecuredSgpa) {
            this.pointsSecuredSgpa = pointsSecuredSgpa;
        }

        public double getPointsSecuredCgpa() {
            return pointsSecuredCgpa;
        }

        public void setPointsSecuredCgpa(double pointsSecuredCgpa) {
            this.pointsSecuredCgpa = pointsSecuredCgpa;
        }

        public double getSgpa() {
            return sgpa;
        }

        public void setSgpa(double sgpa) {
            this.sgpa = sgpa;
        }

        public double getCgpa() {
            return cgpa;
        }

        public void setCgpa(double cgpa) {
            this.cgpa = cgpa;
        }

        /*To convert the given Reasult into string to use the reasult , Basically making it into a json object */

        @Override
        public String toString() {
            return "CgpaReport{" +
                    "semesterIndex=" + semesterIndex +
                    ", gradePoints=" + gradePoints +
                    ", courseCredit=" + courseCredit +
                    ", earnedCredit=" + earnedCredit +
                    ", pointsSecuredSgpa=" + pointsSecuredSgpa +
                    ", pointsSecuredCgpa=" + pointsSecuredCgpa +
                    ", sgpa=" + sgpa +
                    ", cgpa=" + cgpa +
                    '}';
        }
    }

