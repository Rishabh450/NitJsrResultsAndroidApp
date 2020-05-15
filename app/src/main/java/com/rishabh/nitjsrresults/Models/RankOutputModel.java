package com.rishabh.nitjsrresults.Models;

import com.rishabh.nitjsrresults.Utils.Utilities;

public class RankOutputModel {
    String img,marks,name,rank;

    public RankOutputModel(String img, String marks, String name, String rank) {
        this.img = Utilities.HOST_URL+ img;
        this.marks = marks;
        this.name = name;
        this.rank = rank;
    }
}
