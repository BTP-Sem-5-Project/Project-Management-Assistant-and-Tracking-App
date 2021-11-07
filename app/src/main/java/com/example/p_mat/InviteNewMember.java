package com.example.p_mat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p_mat.Models.InvitationHelper;
import com.example.p_mat.Models.TodoHelper;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InviteNewMember extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_new_member);

        Button invite = findViewById(R.id.invite);
        EditText email = findViewById(R.id.email);

        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString();
                if(mail.equals("")){
                    Toast.makeText(getApplicationContext(), "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                    DatabaseReference reference = rootNode.getReference("invitation");
                    SharedPreferences sharedPreferences = getSharedPreferences("FIXED", MODE_PRIVATE);
                    InvitationHelper invitationHelper = new InvitationHelper(sharedPreferences.getString("ORG", "DEFAULT"),mail);
                    String id = reference.push().getKey();
                    reference.child(id).setValue(invitationHelper);
                    Toast.makeText(getApplicationContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}