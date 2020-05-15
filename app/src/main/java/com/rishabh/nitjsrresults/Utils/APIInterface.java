package com.rishabh.nitjsrresults.Utils;

import com.rishabh.nitjsrresults.Models.CgModel;
import com.rishabh.nitjsrresults.Models.RankModel;
import com.rishabh.nitjsrresults.Models.RankOutputModel;
import com.rishabh.nitjsrresults.Models.Roll;
import com.rishabh.nitjsrresults.Models.SemesterCgModel;
import com.rishabh.nitjsrresults.Models.SemesterModel;
import com.rishabh.nitjsrresults.Models.StudentProfile;
import com.rishabh.nitjsrresults.Models.SubjectModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIInterface {
    @Headers("Content-Type: application/json")
    @POST("/api/profile")
    Call<StudentProfile> getProfile(@Body Roll roll);

    @Headers("Content-Type: application/json")
    @POST("/api/cgpa")
    Call<List<SemesterCgModel>> getAllSemCg(@Body Roll roll);

    @Headers("Content-Type: application/json")
    @POST("/api/results")
    Call<List<List<SubjectModel>>> getResultsAllSemester(@Body Roll roll);

    @Headers("Content-Type: application/json")
    @POST("/api/results")
    Call<List<List<RankOutputModel>>> getRankList(@Body RankModel rankModel);




}
