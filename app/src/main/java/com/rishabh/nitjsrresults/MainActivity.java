package com.rishabh.nitjsrresults;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {
    EditText roll_number;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!roll_number.getText().toString().equals(""))
                {
                    Intent intent = new Intent(MainActivity.this,Details.class);
                    intent.putExtra("roll", roll_number.getText().toString());
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this,"Enter Registration Number",Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void init()
    {
        roll_number = findViewById(R.id.roll_edit_text);
        login = findViewById(R.id.login);
        roll_number.getBackground().setAlpha(60);
    }
}
