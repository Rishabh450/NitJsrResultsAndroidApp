package com.rishabh.nitjsrresults.Utils;

import com.rishabh.nitjsrresults.Models.Roll;
import com.rishabh.nitjsrresults.Models.StudentProfile;

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




}
