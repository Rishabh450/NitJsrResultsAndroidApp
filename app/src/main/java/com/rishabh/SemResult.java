package com.rishabh;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.rishabh.nitjsrresults.Models.SubjectModel;
import com.rishabh.nitjsrresults.R;
import com.rishabh.nitjsrresults.Utils.DataWrapper;

import java.util.ArrayList;
import java.util.List;

public class SemResult extends AppCompatActivity {
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_sem_result);
        DataWrapper dw = (DataWrapper) getIntent().getSerializableExtra("list");
        List<SubjectModel> list = dw.getParliaments();
        tableLayout = findViewById(R.id.table);
        TableRow row = new TableRow(SemResult.this);

        String[] attr = {"Subject Code","Subject","Mid-Sem","Internals","Assignment","Quiz Avg","End-Sem","Total","Grade"};
        for(int i =0;i<attr.length;i++) {
            TextView tv = new TextView(SemResult.this);
            tv.setText(attr[i]);
            tv.setTextColor(getResources().getColor(R.color.black_100));
            tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
            tv.setPadding(10,10,10,10);

            tv.setBackgroundResource(R.drawable.textborder);

            row.addView(tv);
        }
        tableLayout.addView(row);
        List<List<String>> listNon = new ArrayList<>();
        for(int i = 0;i<list.size();i++)
        {
            List<String> sub = new ArrayList<>();
            sub.add(list.get(i).code);
            sub.add(list.get(i).name);
            sub.add(list.get(i).mid_sem);
            sub.add(list.get(i).internals);
            sub.add(list.get(i).assignement);
            sub.add(list.get(i).quiz);
            sub.add(list.get(i).end_sem);
            sub.add(list.get(i).total);
            sub.add(list.get(i).grade);

            listNon.add(sub);


        }
        for(int i =0;i <listNon.size();i++)
        {
            TableRow row1 = new TableRow(SemResult.this);
            List<String > sub = listNon.get(i);
            for( int j =0;j<sub.size();j++)
            {

                TextView tv2 = new TextView(SemResult.this);
                tv2.setText(String.valueOf(sub.get(j)));
                tv2.setTextColor(getResources().getColor(R.color.black_100));
                tv2.setPadding(10,10,10,10);
                tv2.setBackgroundResource(R.drawable.textborder);
                row1.addView(tv2);
            }
            tableLayout.addView(row1);
        }

    }
}
