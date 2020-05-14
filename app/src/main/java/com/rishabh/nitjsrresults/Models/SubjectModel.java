package com.rishabh.nitjsrresults.Models;

import java.io.Serializable;

public class SubjectModel implements Serializable {
   public String assignement, code,end_sem,grade,internals,mid_sem,name,quiz,total;

    public SubjectModel(String assignment, String code, String end_sem, String grade, String internals, String mid_sem, String name, String quiz, String total) {
        this.assignement = assignment;
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
