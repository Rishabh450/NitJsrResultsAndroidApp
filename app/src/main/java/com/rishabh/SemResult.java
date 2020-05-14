package com.rishabh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.rishabh.nitjsrresults.R;

public class SemResult extends AppCompatActivity {
    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sem_result);
        tableLayout = findViewById(R.id.table);
        int rowNumber =10,columnNumber=7;
        for (int i=0; i < rowNumber; i++) {
            TableRow row = new TableRow(SemResult.this);
            for (int j=0; j < columnNumber; j++) {
                int value = 1;
                TextView tv = new TextView(SemResult.this);
                tv.setText(String.valueOf(value));
                row.addView(tv);
            }
            tableLayout.addView(row);
        }
    }
}
