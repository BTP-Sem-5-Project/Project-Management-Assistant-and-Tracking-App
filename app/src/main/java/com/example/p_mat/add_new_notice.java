package com.example.p_mat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

import com.example.p_mat.Models.NoticeHelper;
import com.example.p_mat.Models.TodoHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class add_new_notice extends AppCompatActivity {
    TextView textView;
    String[] whomToShow = new String[] {"Default"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_notice);
        SharedPreferences sharedPreferences = this.getSharedPreferences("FIXED", Context.MODE_PRIVATE);
        String myproject = sharedPreferences.getString("ORG", "DEFAULT");
        whomToShow[0] = myproject;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, whomToShow);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteNoticeItem);
        textView.setAdapter(adapter);

        EditText noticeAddTitle = (EditText) findViewById(R.id.noticeAddTitle);
        EditText noticeAddDescription = (EditText) findViewById(R.id.noticeAddDescription);
        Button noticeAddSubmit = (Button) findViewById(R.id.noticeAddSubmit);

        noticeAddSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                DatabaseReference reference = rootNode.getReference("notice");

                String Title = noticeAddTitle.getText().toString(); // Not more than 25 characters
                String Description = noticeAddDescription.getText().toString(); // Not more than 60 characters
                String AssignedProject = myproject;
                if(!Title.equals("")){
                    if(!Description.equals("")){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
                        String currentDate= sdf.format(new Date());
                        String currentTime = sdf2.format(new Date());
                        NoticeHelper noticeHelper = new NoticeHelper(currentDate, currentTime, Title, Description, AssignedProject);
                        String id = reference.push().getKey();
                        reference.child(id).setValue(noticeHelper);
                        Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(add_new_notice.this, Dashboard.class));
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Something is Missing", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Something is Missing", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}