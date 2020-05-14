package com.rishabh.nitjsrresults;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.rishabh.Adapters.CgAdapter;
import com.rishabh.nitjsrresults.Models.CgModel;
import com.rishabh.nitjsrresults.Models.Roll;
import com.rishabh.nitjsrresults.Models.SemesterCgModel;
import com.rishabh.nitjsrresults.Models.StudentProfile;
import com.rishabh.nitjsrresults.Utils.APIClient;
import com.rishabh.nitjsrresults.Utils.APIInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Details extends AppCompatActivity {
    APIInterface apiInterface;
    String roll;
    TextView name, roll_no, branch, rank;
    List<SemesterCgModel> cg = new ArrayList<>() ;
    RecyclerView semres;
    CgAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        intialiseRetrofit();
        init();
        roll = getIntent().getStringExtra("roll");
        Log.d("rolll",roll+" ");
        getProfileData(roll);
        setRecyclerView();
        get_CGPA_list(roll);
    }
    private void intialiseRetrofit() {
        apiInterface = APIClient.getApiClient().create(APIInterface.class);
    }
    private void setRecyclerView() {
        semres.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CgAdapter(this,cg);
        semres.setAdapter(mAdapter);

    }
    public void init()
    {
        name = findViewById(R.id.name);
        roll_no = findViewById(R.id.roll);
        branch = findViewById(R.id.branch);
        rank = findViewById(R.id.rank);
        semres = findViewById(R.id.semres);
    }
    public void showProfile(StudentProfile profile)
    {
        name.setText(profile.getName());
        roll_no.setText(profile.getRoll());
        branch.setText(profile.getBranch());
        rank.setText(profile.getRank());
    }

    private void get_CGPA_list(String roll)
    {
        Call<List<SemesterCgModel>> getReportsModelCall = apiInterface.getAllSemCg(new Roll(roll.trim()));
        getReportsModelCall.enqueue(new Callback<List<SemesterCgModel>>() {

            @Override
            public void onResponse(Call<List<SemesterCgModel> > call, Response<List<SemesterCgModel>> response) {

                cg.addAll(response.body());
                Log.d("recycleer","addedd");
                mAdapter.notifyDataSetChanged();





//                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<SemesterCgModel>> call, Throwable t) {
                Log.d("failedhey", String.valueOf(t));

            }


        });


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
