package com.rishabh.nitjsrresults;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rishabh.nitjsrresults.Utils.SharedPrefferenceManager;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {
    EditText roll_number;
    Button login;
    LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        setContentView(R.layout.activity_main);
        init();
        String log = SharedPrefferenceManager.getInstance(MainActivity.this).getString(SharedPrefferenceManager.Key.LOGIN_STATUS);
        if(log !=null&&!log.equals(""))
        {
            Intent intent =new Intent(this,Details.class);
            intent.putExtra("roll",log);
            startActivity(intent);
            finish();
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!roll_number.getText().toString().equals(""))
                {
                    Intent intent = new Intent(MainActivity.this,Details.class);
                    intent.putExtra("roll", roll_number.getText().toString());
                    startActivity(intent);
                    finish();
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
        root = findViewById(R.id.root);

        root.getBackground().setColorFilter(0xff555555, PorterDuff.Mode.MULTIPLY );
    }
}
