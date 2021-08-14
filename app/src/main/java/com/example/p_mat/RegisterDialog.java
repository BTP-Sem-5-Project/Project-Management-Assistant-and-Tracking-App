package com.example.p_mat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.p_mat.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class RegisterDialog extends AppCompatDialogFragment {
    private EditText editTextEmail;
    private DatabaseReference myDatabase;

    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        myDatabase = FirebaseDatabase.getInstance().getReference("users");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);

        builder.setView(view).setTitle("Register").setPositiveButton("proceed", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!editTextEmail.getText().toString().equals("")){
                    myDatabase.orderByChild("email").equalTo(editTextEmail.getText().toString()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                showMessage("User with that email already exists");
                            }else{
                                String regEmail = editTextEmail.getText().toString();
                                //GENERATING RANDOM OTP
                                Random rnd = new Random();
                                int number = rnd.nextInt(999999);
                                String otp = String.format("%06d",number);
                                String message = "Welcome to P-MAT \nThe OTP for your account registration is " + otp;
                                String subject = "OTP for Account Registration";
                                sendEmail(regEmail,subject,message);

                                Intent intent = new Intent(context,RegisterActivity.class);
                                intent.putExtra("EMAIL",regEmail);
                                intent.putExtra("OTP",otp);
                                context.startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
//                    Intent intent = new Intent(getContext(),RegisterActivity.class);
//                    intent.putExtra("EMAIL",editTextEmail.getText().toString());
//                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(),"Please enter an email",Toast.LENGTH_SHORT).show();
                }
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        editTextEmail = view.findViewById(R.id.dialog_email);

        return  builder.create();
    }

    public void showMessage(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

    public void sendEmail(String email, String subject, String message){
        JavaMailAPI javaMailAPI = new JavaMailAPI(context,email,subject,message);
        javaMailAPI.execute();
    }

}
