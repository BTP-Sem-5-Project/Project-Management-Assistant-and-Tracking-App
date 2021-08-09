package com.example.p_mat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PeopleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        String content = getIntent().getStringExtra("content");
        if(content.equals("organization")){
            String[] temprory = new String[4];
            temprory[0] = "Person1";
            temprory[1] = "Person2";
            temprory[2] = "Person3";
            temprory[3] = "Person4";
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.memberlist);
            MemberAdapter adapter = new MemberAdapter(temprory);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }
    }

}