package com.rishabh.nitjsrresults.Models;

import java.util.ArrayList;
import java.util.List;

public class CgModel {
   public List<SemesterCgModel> semesterCgModels = new ArrayList<>();

    public CgModel(List<SemesterCgModel> semesterCgModels) {
        this.semesterCgModels = semesterCgModels;
    }

    public List<SemesterCgModel> getSemesterCgModels() {
        return semesterCgModels;
    }

    public void setSemesterCgModels(List<SemesterCgModel> semesterCgModels) {
        this.semesterCgModels = semesterCgModels;
    }
}
