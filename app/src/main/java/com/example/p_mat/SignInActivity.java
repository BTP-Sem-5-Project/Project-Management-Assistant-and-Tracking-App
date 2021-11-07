package com.example.p_mat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p_mat.Models.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class SignInActivity extends AppCompatActivity {
    private TextView tv;
    private EditText signInEmail,signInPassword;
    private Button signInButton;
    private DatabaseReference myDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        myDatabase = FirebaseDatabase.getInstance().getReference("users");

        signInButton = findViewById(R.id.signInButton);
        signInEmail = findViewById(R.id.signInEmail);
        signInPassword = findViewById(R.id.signInPassword);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(signInEmail.getText().toString().equals(""))){
                    if(!(signInPassword.getText().toString().equals(""))){
                        myDatabase.orderByChild("email").equalTo(signInEmail.getText().toString()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    for(DataSnapshot child: snapshot.getChildren()){
                                        User user = child.getValue(User.class);
                                        BCrypt.Result result = BCrypt.verifyer().verify(signInPassword.getText().toString().toCharArray(),user.getPassword());

                                        if(result.verified){
                                            Toast.makeText(SignInActivity.this,"Sign In Successful",Toast.LENGTH_SHORT).show();
                                            SharedPreferences sharedpreferences = getSharedPreferences("FIXED", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedpreferences.edit();
                                            editor.putString("EMAIL", signInEmail.getText().toString());
                                            editor.putString("ORG",user.getOrganization());
                                            editor.commit();
                                         //   Toast.makeText(SignInActivity.this,user.getOrganization(),Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(SignInActivity.this,Dashboard.class);
                                            intent.putExtra("USER_ID",user.getId());
                                            intent.putExtra("USER_EMAIL",user.getEmail());
                                            startActivity(intent);
                                        }else{
                                            Toast.makeText(SignInActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }else{
                                    Toast.makeText(SignInActivity.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }else{
                        Toast.makeText(SignInActivity.this,"Please enter password",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignInActivity.this,"Please enter email",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv = findViewById(R.id.goToRegister);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });



    }

    public void openDialog(){
        RegisterDialog  registerDialog = new RegisterDialog();
        registerDialog.show(getSupportFragmentManager(),"Register Dialog");
    }


}