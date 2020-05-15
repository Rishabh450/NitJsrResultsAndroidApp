package com.rishabh.nitjsrresults.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rishabh.Adapters.CgAdapter;
import com.rishabh.nitjsrresults.Details;
import com.rishabh.nitjsrresults.MainActivity;
import com.rishabh.nitjsrresults.Models.Roll;
import com.rishabh.nitjsrresults.Models.SemesterCgModel;
import com.rishabh.nitjsrresults.Models.StudentProfile;
import com.rishabh.nitjsrresults.Models.SubjectModel;
import com.rishabh.nitjsrresults.R;
import com.rishabh.nitjsrresults.Utils.APIClient;
import com.rishabh.nitjsrresults.Utils.APIInterface;
import com.rishabh.nitjsrresults.Utils.SharedPrefferenceManager;

import java.util.ArrayList;
import java.util.List;

import static com.rishabh.nitjsrresults.Details.cg;
import static com.rishabh.nitjsrresults.Utils.Utilities.createAlertDialog;


public class HomePage extends Fragment {
    APIInterface apiInterface;
    String roll;
    TextView name, roll_no, branch, rank;

    List<List<SubjectModel>> result = new ArrayList<>();
    RecyclerView semres;
    CgAdapter mAdapter;
    ImageView stud_image;
    int minsem = 10000;
    String roll2;
    ImageView signout;


/*
    HomePage (String roll,String roll2)
    {
        this.roll =roll;
        this.roll2 =roll2;
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for getContext() fragment
        View v =inflater.inflate(R.layout.fragment_home_page, container, false);
        intialiseRetrofit();
        init(v);
        roll = Details.roll;
        roll2 =roll;

        Log.d("rolll",roll+" ");
        getProfileData(roll);
        setRecyclerView();
        get_CGPA_list(roll);
        getResult(roll);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog =  createAlertDialog(getContext(), "LOGOUT", "Do you want to logout?", "No", "Yes");
                dialog.setCancelable(false);
                dialog.show();
                dialog.findViewById(R.id.dialog_cancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.findViewById(R.id.dialog_continue).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        SharedPrefferenceManager.getInstance(getContext()).put(SharedPrefferenceManager.Key.LOGIN_STATUS, "");
                        startActivity(new Intent(getContext(), MainActivity.class));
                        getActivity(). finish();


                    }
                });

            }
        });
        
        return v;
    }
    private void intialiseRetrofit() {
        apiInterface = APIClient.getApiClient().create(APIInterface.class);
    }
    private void setRecyclerView() {
        semres.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CgAdapter(getContext(),cg,result);
        semres.setAdapter(mAdapter);

    }
    
    public void init(View view)
    {
        signout =view. findViewById(R.id.signout);
        stud_image =view. findViewById(R.id.student_image);
        name = view. findViewById(R.id.name);
        roll_no = view. findViewById(R.id.roll);
        branch = view. findViewById(R.id.branch);
        rank = view. findViewById(R.id.rank);
        semres = view. findViewById(R.id.semres);
    }

    public void getResult(String roll){
        Call<List<List<SubjectModel>>> getReportsModelCall = apiInterface.getResultsAllSemester(new Roll(roll.trim()));
        getReportsModelCall.enqueue(new Callback<List<List<SubjectModel>> >() {

            @Override
            public void onResponse(Call<List<List<SubjectModel>> > call, Response<List<List<SubjectModel>>> response) {

                result.addAll(response.body());
                Log.d("recycleer","addedd result");
                mAdapter.notifyDataSetChanged();






//                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<List<SubjectModel>> > call, Throwable t) {
                Log.d("failedhey", String.valueOf(t));

            }


        });
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

    public void showProfile(StudentProfile profile)
    {
        Log.d("imageurll","https://nilekrator.pythonanywhere.com"+profile.img);
        Glide.with(getContext())


                .load("https://nilekrator.pythonanywhere.com"+profile.img)
                .into(stud_image);
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
                if(response.body()==null)
                {
                    Toast.makeText(getContext(),"Invalid Roll Number",Toast.LENGTH_SHORT).show();
                    SharedPrefferenceManager.getInstance(getContext()).put(SharedPrefferenceManager.Key.LOGIN_STATUS, "");
                    getActivity().finish();
                }
                else {
                    SharedPrefferenceManager.getInstance(getContext()).put(SharedPrefferenceManager.Key.LOGIN_STATUS, roll2);
                    String log = SharedPrefferenceManager.getInstance(getContext()).getString(SharedPrefferenceManager.Key.LOGIN_STATUS);
                    Log.d("sharedpreflog",log+" ");
                    StudentProfile studentProfile = new StudentProfile(response.body().name, response.body().roll, response.body().branch, response.body().rank, response.body().img);
                    showProfile(studentProfile);
                }


//                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<StudentProfile> call, Throwable t) {
                Log.d("failedhey", String.valueOf(t));

            }


        });

    }
}
