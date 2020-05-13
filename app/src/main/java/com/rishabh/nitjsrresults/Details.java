package com.rishabh.nitjsrresults;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rishabh.nitjsrresults.Models.Roll;
import com.rishabh.nitjsrresults.Models.StudentProfile;
import com.rishabh.nitjsrresults.Utils.APIClient;
import com.rishabh.nitjsrresults.Utils.APIInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Details extends AppCompatActivity {
    APIInterface apiInterface;
    String roll;
    TextView name, roll_no, branch, rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        intialiseRetrofit();
        init();
        roll = getIntent().getStringExtra("roll");
        Log.d("rolll",roll+" ");
        getProfileData(roll);
    }
    private void intialiseRetrofit() {
        apiInterface = APIClient.getApiClient().create(APIInterface.class);
    }
    public void init()
    {
        name = findViewById(R.id.name);
        roll_no = findViewById(R.id.roll);
        branch = findViewById(R.id.branch);
        rank = findViewById(R.id.rank);
    }
    public void showProfile(StudentProfile profile)
    {
        name.setText(profile.getName());
        roll_no.setText(profile.getRoll());
        branch.setText(profile.getBranch());
        rank.setText(profile.getRank());
    }

    private void getProfileData(String roll)
    {

        Call<StudentProfile> getReportsModelCall = apiInterface.getProfile(new Roll(roll.trim()));
        getReportsModelCall.enqueue(new Callback<StudentProfile>() {

            @Override
            public void onResponse(Call<StudentProfile > call, Response<StudentProfile> response) {
                Log.d("respbodyy",response.body().getRank());
               StudentProfile studentProfile = new StudentProfile(response.body().name, response.body().roll,response.body().branch,response.body().rank);
               showProfile(studentProfile);


//                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<StudentProfile> call, Throwable t) {
                Log.d("failedhey", String.valueOf(t));

            }


        });

    }
}
