package com.rishabh.nitjsrresults.Models;

public class SubjectModel {
   public String assignment, code,end_sem,grade,internals,mid_sem,name,quiz,total;

    public SubjectModel(String assignment, String code, String end_sem, String grade, String internals, String mid_sem, String name, String quiz, String total) {
        this.assignment = assignment;
        this.code = code;
        this.end_sem = end_sem;
        this.grade = grade;
        this.internals = internals;
        this.mid_sem = mid_sem;
        this.name = name;
        this.quiz = quiz;
        this.total = total;
    }
}

/*
"assignement": "0.0",
        "code": "ME1201",
        "end_sem": "30.0",
        "grade": "D",
        "internals": "20.0",
        "mid_sem": "6.0",
        "name": "Engineering Mechanics",
        "quiz": "0.0",
        "total": "56.0"*/
