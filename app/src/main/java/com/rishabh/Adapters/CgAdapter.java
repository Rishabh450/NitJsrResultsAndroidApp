package com.rishabh.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rishabh.SemResult;
import com.rishabh.nitjsrresults.Models.SemesterCgModel;
import com.rishabh.nitjsrresults.Models.SubjectModel;
import com.rishabh.nitjsrresults.R;
import com.rishabh.nitjsrresults.Utils.DataWrapper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CgAdapter extends RecyclerView.Adapter<CgAdapter.MyHolder> {
    private Context context;
    private List<SemesterCgModel> list;
    int min_sem = 1000;
    List<List<SubjectModel>> result = new ArrayList<>();

    public CgAdapter(Context context, List<SemesterCgModel> list, List<List<SubjectModel>> result) {
        this.context = context;
        this.list = list;
        this.result = result;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.semcgitem,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.status.setText("STATUS: "+list.get(position).status);
        holder.cgpa.setText("CGPA: "+list.get(position).cgpa);
        holder.sgpa.setText("SGPA: "+list.get(position).sgpa);
        holder.sem.setText("SEMESTER: "+list.get(position).semester);
        int semster = Integer.parseInt(list.get(position).semester);
        if(semster<min_sem)
            min_sem=semster;

        holder.view_sem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int semster = Integer.parseInt(list.get(position).semester);
                int diff = 0;
                if(min_sem !=1)
                    diff = min_sem -1;

               // Log.d("resultlist", String.valueOf(result.get(result.size() - semster)));
                List<SubjectModel> semResults = result.get(result.size() - semster + diff);
                Intent intent = new Intent(context,SemResult.class);
                intent.putExtra("list",new DataWrapper(semResults) );
                intent.putExtra("minsem",min_sem);


                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public LinearLayout addLayout;
        public TextView sem;
        public TextView cgpa;
        public TextView sgpa,status,view_sem;



        public MyHolder(@NonNull View itemView) {
            super(itemView);
            sem = itemView.findViewById(R.id.semno);
            cgpa = itemView.findViewById(R.id.cgpa);
            sgpa = itemView.findViewById(R.id.sgpa);
            status = itemView.findViewById(R.id.status);
            view_sem = itemView.findViewById(R.id.view_sem);
        }
    }

}
