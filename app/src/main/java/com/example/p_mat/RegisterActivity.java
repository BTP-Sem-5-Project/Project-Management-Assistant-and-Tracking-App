package com.example.p_mat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.p_mat.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private DatabaseReference myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDatabase = FirebaseDatabase.getInstance().getReference("users");
        String name="Vijay Joshi";
        String github="https://github.com/vijayjoshi16";
        String email="joshivijay324@gmail.com";
        String resume="google.com";
        String phone="999999999";
        String address="Mumbai";
        String qualification="BTECH CSE";
        int experience=4;
        List<String> skills = new ArrayList<String>();
        skills.add("Web");
        skills.add("Android");
        skills.add("ML");
        List<String> achievements = new ArrayList<String>();
        achievements.add("a1");
        achievements.add("a2");
        achievements.add("a3");
        String id = myDatabase.push().getKey();
        System.out.println(myDatabase.child(id));
        System.out.println(id);
        User user = new User(id,name,github,email,resume,phone,address,qualification,experience,skills,achievements);
        myDatabase.child(id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(RegisterActivity.this,"User registered successfully",Toast.LENGTH_SHORT).show();
                System.out.println("Suceess");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegisterActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                System.out.println(e);
            }
        });


    }
}