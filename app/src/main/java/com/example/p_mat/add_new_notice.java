package com.example.p_mat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class add_new_notice extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_notice);
    }
    public void firstButton(View v){

        textView=findViewById(R.id.to_TextView);
        textView.setText("to Organization name");
    }
    public void secondButton(View v){

        textView=findViewById(R.id.to_TextView);
        textView.setText("to Project name");
    }
}