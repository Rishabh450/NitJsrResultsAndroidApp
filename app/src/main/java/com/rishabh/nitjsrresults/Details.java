package com.rishabh.nitjsrresults;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rishabh.Adapters.CgAdapter;
import com.rishabh.nitjsrresults.Models.CgModel;
import com.rishabh.nitjsrresults.Models.Roll;
import com.rishabh.nitjsrresults.Models.SemesterCgModel;
import com.rishabh.nitjsrresults.Models.SemesterModel;
import com.rishabh.nitjsrresults.Models.StudentProfile;
import com.rishabh.nitjsrresults.Models.SubjectModel;
import com.rishabh.nitjsrresults.Utils.APIClient;
import com.rishabh.nitjsrresults.Utils.APIInterface;
import com.rishabh.nitjsrresults.Utils.SharedPrefferenceManager;

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
    List<List<SubjectModel>> result = new ArrayList<>();
    RecyclerView semres;
    CgAdapter mAdapter;
    ImageView stud_image;
    int minsem = 10000;
    String roll2;
    ImageView signout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        setContentView(R.layout.activity_details);
        intialiseRetrofit();
        init();
        roll = getIntent().getStringExtra("roll");
        roll2 =roll;

        Log.d("rolll",roll+" ");
        getProfileData(roll);
        setRecyclerView();
        get_CGPA_list(roll);
        getResult(roll);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = createAlertDialog(Details.this, "LOGOUT", "Do you want to logout?", "No", "Yes");
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
                        SharedPrefferenceManager.getInstance(Details.this).put(SharedPrefferenceManager.Key.LOGIN_STATUS, "");
                         startActivity(new Intent(Details.this,MainActivity.class));
                         finish();


                    }
                });

            }
        });
    }
    private void intialiseRetrofit() {
        apiInterface = APIClient.getApiClient().create(APIInterface.class);
    }
    private void setRecyclerView() {
        semres.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CgAdapter(this,cg,result);
        semres.setAdapter(mAdapter);

    }
    public void init()
    {
        signout =findViewById(R.id.signout);
        stud_image = findViewById(R.id.student_image);
        name = findViewById(R.id.name);
        roll_no = findViewById(R.id.roll);
        branch = findViewById(R.id.branch);
        rank = findViewById(R.id.rank);
        semres = findViewById(R.id.semres);
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

    public void showProfile(StudentProfile profile)
    {
        Log.d("imageurll","https://nilekrator.pythonanywhere.com"+profile.img);
        Glide.with(Details.this)


                .load("https://nilekrator.pythonanywhere.com"+profile.img)
                .into(stud_image);
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
                if(response.body()==null)
                {
                    Toast.makeText(Details.this,"Invalid Roll Number",Toast.LENGTH_SHORT).show();
                    SharedPrefferenceManager.getInstance(Details.this).put(SharedPrefferenceManager.Key.LOGIN_STATUS, "");
                    finish();
                }
                else {
                    SharedPrefferenceManager.getInstance(Details.this).put(SharedPrefferenceManager.Key.LOGIN_STATUS, roll2);
                    String log = SharedPrefferenceManager.getInstance(Details.this).getString(SharedPrefferenceManager.Key.LOGIN_STATUS);
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
    public static Dialog createAlertDialog(Context context,String head,String msg,String b1,String b2){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_alert);
        TextView headtv = dialog.findViewById(R.id.dialog_head);
        TextView msgtv = dialog.findViewById(R.id.dialog_msg);
        Button cancelbtn = dialog.findViewById(R.id.dialog_cancel);
        Button contbtn = dialog.findViewById(R.id.dialog_continue);
        if(b1.compareTo("")==0)
            cancelbtn.setVisibility(View.GONE);
        headtv.setText(head);
        msgtv.setText(msg);
        cancelbtn.setText(b1);
        contbtn.setText(b2);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
        dialog.show();
        return dialog;
    }

}
