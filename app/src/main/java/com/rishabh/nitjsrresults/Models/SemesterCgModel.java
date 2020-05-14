package com.rishabh.nitjsrresults.Models;

public class SemesterCgModel {
    public String cgpa, sgpa, semester, status;

    public SemesterCgModel(String cgpa, String sgpa, String semester, String status) {
        this.cgpa = cgpa;
        this.sgpa = sgpa;
        this.semester = semester;
        this.status = status;
    }

    public String getCgpa() {
        return cgpa;
    }

    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getSgpa() {
        return sgpa;
    }

    public void setSgpa(String sgpa) {
        this.sgpa = sgpa;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
