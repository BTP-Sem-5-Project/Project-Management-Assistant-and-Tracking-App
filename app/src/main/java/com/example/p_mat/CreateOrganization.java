package com.example.p_mat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p_mat.Models.CEO;
import com.example.p_mat.Models.Developer;
import com.example.p_mat.Models.HR;
import com.example.p_mat.Models.Organization;

import com.example.p_mat.Models.Project;
import com.example.p_mat.Models.ProjectManager;
import com.example.p_mat.Models.PublicNotice;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CreateOrganization extends AppCompatActivity {
    private EditText Name;
    private Button Create;
    private DatabaseReference myDatabase;
    boolean block = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_organization);

        myDatabase = FirebaseDatabase.getInstance().getReference("organizations");

        Name = findViewById(R.id.org_name);
        Create = findViewById(R.id.create_org);

        String email = "developer.vijayjoshi@gmail.com";
        String ceoName = "Vijay Joshi";
        String userId = "-MhA7NGsfCzWTbiVXd6R";

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String OrgName = Name.getText().toString();
                if(!TextUtils.isEmpty(OrgName)){
                    myDatabase.orderByChild("name").equalTo(OrgName).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(!snapshot.exists()){
                                block = true;
                                List<CEO> ceo = new ArrayList<CEO>();;
                                CEO newCeo = new CEO(ceoName,userId,email);
                                ceo.add(newCeo);
                                List<HR> hr = new ArrayList<HR>();
                                HR newHr = new HR("","","");
                                hr.add(newHr);
                                List<ProjectManager> projectManagers = new ArrayList<ProjectManager>();
                                ProjectManager pm = new ProjectManager("Nalin Agrawal","-MgaYcSwDdHV4AbpnzY2","nalinagrawal333@gmail.com");
                                projectManagers.add(pm);
                                pm = new ProjectManager("Vijay Joshi","-MhA7NGsfCzWTbiVXd6R","developer.vijayjoshi@gmail.com");
                                projectManagers.add(pm);
                                pm = new ProjectManager("Prerit","-MkRUn5p2QS11NmWx_Ev","preritkrjha@gmail.com");
                                projectManagers.add(pm);
                                List<Developer> developers = new ArrayList<Developer>();
                                Developer newDev = new Developer("Nalin Agrawal","-MgaYcSwDdHV4AbpnzY2","nalinagrawal333@gmail.com");
                                developers.add(newDev);
                                newDev = new Developer("Vijay Joshi","-MhA7NGsfCzWTbiVXd6R","developer.vijayjoshi@gmail.com");
                                developers.add(newDev);
                                newDev = new Developer("Anonymous","-Mh-Ep1NfX-GEzX1MuUx","joshivijay324@gmail.com");
                                developers.add(newDev);
                                newDev = new Developer("Prerit","-MkRUn5p2QS11NmWx_Ev","preritkrjha@gmail.com");
                                developers.add(newDev);
                                List<Project> projects = new ArrayList<Project>();
                                List<PublicNotice> publicNotices = new ArrayList<PublicNotice>();
                                String id = myDatabase.push().getKey();
                                Organization organization = new Organization(OrgName,ceo,hr,projectManagers,developers,projects,publicNotices);
                                myDatabase.child(id).setValue(organization).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        showMessage("Organization Created Successfully");
                                        startActivity(new Intent(CreateOrganization.this,SignInActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(CreateOrganization.this,"Something went wrong!",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else if(!block){
                                for(DataSnapshot child: snapshot.getChildren()){
                                    Organization organization = child.getValue(Organization.class);
                                    System.out.println(organization.getName());
                                    if(organization.getName().equals(OrgName)){
                                        showMessage("Organization with that name already exists!");
                                    }
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }else{
                    Toast.makeText(CreateOrganization.this,"Please enter organization name",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void showMessage(String message){
        Toast.makeText(CreateOrganization.this,message,Toast.LENGTH_SHORT).show();
    }
}