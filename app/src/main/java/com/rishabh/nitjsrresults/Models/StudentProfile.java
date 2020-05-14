package com.rishabh.nitjsrresults.Models;

public class StudentProfile {
   public String name ;
   public String roll;
   public String branch;
   public String rank;
   public String img;

    public StudentProfile(String name, String roll, String branch, String rank,String img) {
        this.name = name;
        this.roll = roll;
        this.branch = branch;
        this.rank = rank;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
