package com.rishabh.nitjsrresults.Utils;

import com.rishabh.nitjsrresults.Models.SubjectModel;

import java.io.Serializable;
import java.util.List;

public class DataWrapper implements Serializable {

    private List<SubjectModel> parliaments;

    public DataWrapper(List<SubjectModel> data) {
        this.parliaments = data;
    }

    public List<SubjectModel> getParliaments() {
        return this.parliaments;
    }
}

