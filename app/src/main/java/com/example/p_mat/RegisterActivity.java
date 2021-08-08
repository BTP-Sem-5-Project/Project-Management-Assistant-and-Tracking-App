package com.example.p_mat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
    private EditText Name,Phone,Email,Exp,Github,Skills,Qualification,LinkedIn;
    private RadioGroup available;
    private Button Register;
    private TextView goToSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDatabase = FirebaseDatabase.getInstance().getReference("users");

        Name = findViewById(R.id.name);
        Phone = findViewById(R.id.phone);
        Email = findViewById(R.id.email);
        Exp = findViewById(R.id.experience);
        Github = findViewById(R.id.GitHub);
        Skills = findViewById(R.id.skills);
        Qualification = findViewById(R.id.qualification);
        LinkedIn = findViewById(R.id.linkedin);
        Register = findViewById(R.id.register);
        available = findViewById(R.id.available);

        goToSignIn = findViewById(R.id.signIn);

        goToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,SignInActivity.class));
                finish();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = Name.getText().toString();
                String phone = Phone.getText().toString();
                String email = Email.getText().toString();
                String exp = Exp.getText().toString();
                String github = Github.getText().toString();
                String[] skills = Skills.getText().toString().split(",");
                String qualification = Qualification.getText().toString();
                String linkedin = LinkedIn.getText().toString();

                System.out.println(name);
                System.out.println(phone);
                System.out.println(email);
                System.out.println(exp);
                System.out.println(github);

                if(!name.equals("")){
                    if(!email.equals("")){
                        if(!phone.equals("")){
                            if(!github.equals("")){
                                if(!qualification.equals("")){
                                    if(!linkedin.equals("")){
                                        if(!exp.equals("")){
                                            int selectedRadio = available.getCheckedRadioButtonId();
                                            if(selectedRadio!=-1){
                                                List<String> skillsList = new ArrayList<String>();
                                                List<String> achievementList = new ArrayList<String>();
                                                for(String s:skills){
                                                    skillsList.add(s);
                                                }
                                                Boolean isAvailable = selectedRadio==R.id.radioAvailable?true:false;
                                                String id = myDatabase.push().getKey();
                                                User user = new User(id,name,github,email,phone,qualification,linkedin,Integer.parseInt(exp),skillsList,isAvailable);
                                                myDatabase.child(id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(RegisterActivity.this,"User registered successfully",Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(RegisterActivity.this,SignInActivity.class));
                                                        finish();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(RegisterActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }else{
                                                Toast.makeText(RegisterActivity.this,"Please select availability",Toast.LENGTH_SHORT).show();
                                            }
                                        }else{
                                            Toast.makeText(RegisterActivity.this,"Please enter experience",Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(RegisterActivity.this,"Please enter linkedin profile link",Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(RegisterActivity.this,"Please enter qualification",Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(RegisterActivity.this,"Please enter github id",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this,"Please enter phone no.",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this,"Please enter email",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this,"Please enter name",Toast.LENGTH_SHORT).show();
                }
            }
        });

//        String name="Vijay Joshi";
//        String github="https://github.com/vijayjoshi16";
//        String email="joshivijay324@gmail.com";
//        String resume="google.com";
//        String phone="999999999";
//        String address="Mumbai";
//        String qualification="BTECH CSE";
//        int experience=4;
//        List<String> skills = new ArrayList<String>();
//        skills.add("Web");
//        skills.add("Android");
//        skills.add("ML");
//        List<String> achievements = new ArrayList<String>();
//        achievements.add("a1");
//        achievements.add("a2");
//        achievements.add("a3");
//        String id = myDatabase.push().getKey();
//        System.out.println(myDatabase.child(id));
//        System.out.println(id);
//        User user = new User(id,name,github,email,resume,phone,address,qualification,experience,skills,achievements);
//        myDatabase.child(id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(RegisterActivity.this,"User registered successfully",Toast.LENGTH_SHORT).show();
//                System.out.println("Suceess");
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(RegisterActivity.this,"Something went wrong",Toast.LENGTH_SHORT).show();
//                System.out.println(e);
//            }
//        });


    }
}