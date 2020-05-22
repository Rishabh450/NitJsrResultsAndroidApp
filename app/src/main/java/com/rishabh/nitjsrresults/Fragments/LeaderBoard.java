package com.rishabh.nitjsrresults.Fragments;

import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rishabh.SemResult;
import com.rishabh.nitjsrresults.Details;
import com.rishabh.nitjsrresults.Models.RankModel;
import com.rishabh.nitjsrresults.Models.RankOutputModel;
import com.rishabh.nitjsrresults.Models.Roll;
import com.rishabh.nitjsrresults.Models.SubjectModel;
import com.rishabh.nitjsrresults.R;
import com.rishabh.nitjsrresults.Utils.APIClient;
import com.rishabh.nitjsrresults.Utils.APIInterface;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static com.rishabh.nitjsrresults.Details.bott;


import static com.rishabh.nitjsrresults.Details.roll;
import static com.rishabh.nitjsrresults.Fragments.HomePage.ide;


public class LeaderBoard extends Fragment {

    TableLayout tableLayout;
    APIInterface apiInterface;
    public static List<RankOutputModel> rankOutputModelList = new ArrayList<RankOutputModel>();
    String[] method = {"sgpa","cgpa"};
    ArrayList<String> semester = new ArrayList<>();
    Button getrank;
    Spinner methodspin,spinner2;
    ProgressBar progressBar;
    double  total,count;
    Typeface face;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_leader_board, container, false);
        tableLayout = view.findViewById(R.id.ranktable);
        getrank = view.findViewById(R.id.getrank);
        progressBar = view.findViewById(R.id.progress);
        progressBar.setScaleY(2.5f);
         face = ResourcesCompat.getFont(getContext(), R.font.poppins_light);


        methodspin = (Spinner) view. findViewById(R.id.method);
        intialiseRetrofit();
        for(int i = 0; i< Details. cg.size();i++)
        {
            semester.add(Details.cg.get(i).semester) ;
            Log.d("swmesr",semester.get(i));
        }

// Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getContext(),   R.layout.spinner , method);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner); // The drop down view
        methodspin .setAdapter(spinnerArrayAdapter);
         spinner2 = (Spinner) view. findViewById(R.id.semester);

        methodspin.getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);
        spinner2 .getBackground().setColorFilter(getResources().getColor(R.color.red), PorterDuff.Mode.SRC_ATOP);

// Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(getContext(),   R.layout.spinner , semester);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner); // The drop down view
        spinner2.setAdapter(spinnerArrayAdapter2);
        getRank();
        getrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bott.setVisibility(View.GONE);

                progressBar.setVisibility(View.VISIBLE);
                tableLayout.removeAllViews();
                tableLayout.removeAllViewsInLayout();
                rankOutputModelList.clear();
                getRank();
            }
        });


        return view;



    }
    private void getRank()
    {
        getRankData(roll,spinner2.getSelectedItem().toString(),methodspin .getSelectedItem().toString());

    }
    private void intialiseRetrofit() {
        apiInterface = APIClient.getApiClient().create(APIInterface.class);
    }
    public void getRankData(String rollnumber,String semester,String method)
    {
        Log.d("calldettt",rollnumber+" "+method + " "+semester);
        Call<List<RankOutputModel>> getReportsModelCall = apiInterface.getRankList(new RankModel(rollnumber,semester,method));
        getReportsModelCall.enqueue(new Callback<List<RankOutputModel>>() {

            @Override
            public void onResponse(Call<List<RankOutputModel> > call, Response<List<RankOutputModel>> response) {
                rankOutputModelList.addAll(response.body());
                bott.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

                TableRow row = new TableRow(getContext());

                String[] attr = {"Rank","Photo","Name","Score"};
                for(int i =0;i<attr.length;i++) {
                    TextView tv = new TextView(getContext());
                    tv.setText(attr[i]);
                    tv.setTextSize(18);

                    tv.setTypeface(face);

                    tv.setTextColor(getResources().getColor(R.color.black_100));
                    try {
                        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    tv.setPadding(20,20,20,20);

                    tv.setBackgroundResource(R.drawable.textborder);

                    row.addView(tv);
                }
                tableLayout.addView(row);

                for(int i =0;i < rankOutputModelList.size();i++)
                {
                    TableRow row1 = new TableRow(getContext());
                    RankOutputModel rank= new RankOutputModel(rankOutputModelList.get(i).img,rankOutputModelList.get(i).marks,rankOutputModelList.get(i).name,rankOutputModelList.get(i).rank) ;
                    TextView tv2 = new TextView(getContext());

                    total = total + Double.parseDouble(rank.marks.trim());
                    count++;
                    tv2.setText(rank.rank);
                    tv2.setTextSize(18);

                    tv2.setTypeface(face);
                    if(rank.name.equals(ide)) {
                        tv2.setTextColor(getResources().getColor(R.color.white));
                        tv2.setBackgroundResource(R.drawable.textborder2);
                    }
                    else {
                        tv2.setBackgroundResource(R.drawable.textborder);
                        tv2.setTextColor(getResources().getColor(R.color.black_100));
                    }
                    tv2.setPadding(20,20,20,20);

                    row1.addView(tv2);
                    ImageView imageView = new ImageView(getContext());




                    Glide.with(getContext())


                            .load(rank.img)
                            .fitCenter()
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .placeholder(R.drawable.ic_account_box_black_50dp)
                            .skipMemoryCache(true)
                            .into(imageView);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);

                    row1.addView(imageView);
                    TextView tv3 = new TextView(getContext());
                    tv3.setText(rank.name);

                    tv3.setTypeface(face);

                    if(rank.name.equals(ide)) {
                        tv3.setTextColor(getResources().getColor(R.color.white));
                        tv3.setBackgroundResource(R.drawable.textborder2);
                    }
                    else {
                        tv3.setBackgroundResource(R.drawable.textborder);
                        tv3.setTextColor(getResources().getColor(R.color.black_100));
                    }
                    tv3.setTextSize(18);

                    tv3.setPadding(20,20,20,20);

                    row1.addView(tv3);
                    TextView tv4 = new TextView(getContext());
                    tv4.setText(rank.marks);
                    tv4.setTextSize(18);

                    tv4.setTypeface(face);
                    if(rank.name.equals(ide)) {
                        tv4.setTextColor(getResources().getColor(R.color.white));
                        tv4.setBackgroundResource(R.drawable.textborder2);
                    }
                    else {
                        tv4.setBackgroundResource(R.drawable.textborder);
                        tv4.setTextColor(getResources().getColor(R.color.black_100));
                    }
                    tv4.setPadding(20,20,20,20);

                    row1.addView(tv4);

                    tableLayout.addView(row1);
                }
                TableRow avg = new TableRow(getContext());
                TextView tv = new TextView(getContext());
                tv.setText(String.valueOf("Avg Score"));
                tv.setTextSize(18);

                tv.setTypeface(face);
                tv.setTextColor(getResources().getColor(R.color.white));
                tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
                tv.setPadding(20,20,20,20);

                tv.setBackgroundResource(R.drawable.textborder2);

                avg.addView(tv);
                Double aver = total/count;
                Double truncatedDouble = BigDecimal.valueOf(aver)
                        .setScale(3, RoundingMode.HALF_UP)
                        .doubleValue();
                TextView tv1 = new TextView(getContext());
                tv1.setText(String.valueOf(truncatedDouble) );
                tv1.setTextSize(18);

                tv1.setTypeface(face);
                tv1.setTextColor(getResources().getColor(R.color.white));
                tv1.setTypeface(tv.getTypeface(), Typeface.BOLD);
                tv1.setPadding(20,20,20,20);

                tv1.setBackgroundResource(R.drawable.textborder2);
                avg.addView(tv1);
                tableLayout.addView(avg,0);






//                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<RankOutputModel> > call, Throwable t) {
                Log.d("failedhey", String.valueOf(t));

            }


        });    }
}
