package com.example.p_mat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.p_mat.Models.Organization;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProject extends AppCompatActivity {
    public String organiaztionId = "-MlQdYJXearEpd-Xevoc";
    public String[] devIds = {"-MgaYcSwDdHV4AbpnzY2","-MhA7NGsfCzWTbiVXd6R","-Mh-Ep1NfX-GEzX1MuUx","-MkRUn5p2QS11NmWx_Ev"};
    public String[] devEmails = {"nalinagrawal333@gmail.com","developer.vijayjoshi@gmail.com","joshivijay324@gmail.com","preritkrjha@gmail.com"};
    public String[] devNames = {"Nalin Agrawal","Vijay Joshi","Anonymous","Prerit"};
    private DatabaseReference myDatabase;
    private AutoCompleteTextView selectDev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        myDatabase = FirebaseDatabase.getInstance().getReference("users");
        selectDev = findViewById(R.id.autoCompleteSelectDev);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.select_dev_dropdown,devNames);
        selectDev.setAdapter(adapter);




    }
}