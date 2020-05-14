package com.rishabh.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rishabh.nitjsrresults.Models.SemesterCgModel;
import com.rishabh.nitjsrresults.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CgAdapter extends RecyclerView.Adapter<CgAdapter.MyHolder> {
    private Context context;
    private List<SemesterCgModel> list;

    public CgAdapter(Context context, List<SemesterCgModel> list) {
        this.context = context;
        this.list = list;
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

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public LinearLayout addLayout;
        public TextView sem;
        public TextView cgpa;
        public TextView sgpa,status;



        public MyHolder(@NonNull View itemView) {
            super(itemView);
            sem = itemView.findViewById(R.id.semno);
            cgpa = itemView.findViewById(R.id.cgpa);
            sgpa = itemView.findViewById(R.id.sgpa);
            status = itemView.findViewById(R.id.status);
        }
    }

}
