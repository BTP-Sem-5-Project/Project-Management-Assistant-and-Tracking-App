package com.example.p_mat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p_mat.Models.NoticeHelper;
import com.example.p_mat.Models.TodoHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_new_notice extends AppCompatActivity {
    TextView textView;
    private  final  String[] whomToShow = new String[] {
            "All","Personal", "Organization Name", "Member 1", "Member 2"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_notice);
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
                String AssignedProject = "id123";
                if(!Title.equals("")){
                    if(!Description.equals("")){
                        NoticeHelper noticeHelper = new NoticeHelper("27112001", "0320", Title, Description, AssignedProject);
                        String id = reference.push().getKey();
                        reference.child(id).setValue(noticeHelper);
                        Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
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