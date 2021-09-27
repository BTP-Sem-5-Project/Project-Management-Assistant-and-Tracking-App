package com.example.p_mat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

public class ProjectPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_page);

        TextView organizationName = (TextView) findViewById(R.id.projectName);
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();

        String ProjectName = getIntent().getStringExtra("projectName");
        TextView projectName = findViewById(R.id.projectName);
        projectName.setText(ProjectName);

        String ProjectDescription = getIntent().getStringExtra("projectDescription");
        TextView projectDescription = findViewById(R.id.projectdescription);
        projectDescription.setText(ProjectDescription);

        Button peopleButton = (Button) findViewById(R.id.peoplebutton);
        peopleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickPeopleButton(ProjectName);
            }
        });

    }
    public void onClickPeopleButton(String ProjectName){
        Intent intent = new Intent(ProjectPage.this,PeopleActivity.class);
        intent.putExtra("content", ProjectName);
        startActivity(intent);
    }
}