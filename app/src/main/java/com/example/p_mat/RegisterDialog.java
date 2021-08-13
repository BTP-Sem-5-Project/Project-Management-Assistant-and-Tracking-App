package com.example.p_mat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class RegisterDialog extends AppCompatDialogFragment {
    private EditText editTextEmail;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);

        builder.setView(view).setTitle("Register").setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("proceed", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!editTextEmail.getText().toString().equals("")){
                    Intent intent = new Intent(getContext(),RegisterActivity.class);
                    intent.putExtra("EMAIL",editTextEmail.getText().toString());
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(),"Please enter an email",Toast.LENGTH_SHORT).show();
                }
            }
        });

        editTextEmail = view.findViewById(R.id.dialog_email);

        return  builder.create();
    }
}
