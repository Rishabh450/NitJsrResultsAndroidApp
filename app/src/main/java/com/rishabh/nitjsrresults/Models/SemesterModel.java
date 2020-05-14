package com.rishabh.nitjsrresults.Models;

import java.util.ArrayList;
import java.util.List;

public class SemesterModel {
   public List<SubjectModel> semesterModel = new ArrayList<>();

    public SemesterModel(List<SubjectModel> semesterModel) {
        this.semesterModel = semesterModel;
    }
}
