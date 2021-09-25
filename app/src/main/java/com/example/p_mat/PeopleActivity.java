package com.example.p_mat;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.p_mat.Models.OrganizationHelper;
import com.example.p_mat.Models.ProjectHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class PeopleActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_people);

        String content = getIntent().getStringExtra("content");
        System.out.println("Reached PeopleActivity"+content);
        if(content.equals("organization")){

            String orgName = "btp5";

            FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
            DatabaseReference reference = rootNode.getReference("organization");
            ArrayList<String> StoreMembers = new ArrayList<String>();
            final String[] CEO = {new String()};
            final String[] HR = {new String()};
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot allOrganization : snapshot.getChildren()){
                        OrganizationHelper organizationHelper = allOrganization.getValue(OrganizationHelper.class);
                        if(organizationHelper.getName().equals(orgName)){
                            CEO[0] = organizationHelper.getCEO();
                            HR[0] = organizationHelper.getHR();
                            for(String members: organizationHelper.getMembers()){
                                StoreMembers.add(members);
                            }
                            break;

                        }
                    }
                    int N = StoreMembers.size();
                    String[] members = new String[N];
                    String[] rank = new String[N];
                    for(int i=0;i<N;i++){
                        members[i]=StoreMembers.get(i);
                        if(CEO[0].equals(members[i])){
                            rank[i]="CEO";
                        }else if(HR[0].equals(members[i])){
                            rank[i]="HR";
                        }else{
                            rank[i]="Developer";
                        }
                    }

                    System.out.println("Length of n: "+ N);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.memberlist);
                    recyclerView.setAdapter(new MemberAdapter(members,rank));
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PeopleActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            };
            CompletableFuture.runAsync(() -> {
                System.out.println("1==================================================================================");
                reference.addValueEventListener(eventListener);
                System.out.println("2==================================================================================");
            });

        }else{
            FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
            System.out.println("Reached else");
            ArrayList<String> StoreMembers = new ArrayList<String>();
            final String[] Manager = {new String()};

            DatabaseReference reference = rootNode.getReference("projects");
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {;
                    for(DataSnapshot allProjects : snapshot.getChildren()){
                        ProjectHelper projectHelper = allProjects.getValue(ProjectHelper.class);
                        if(projectHelper.getName().equals(content)){
                            Manager[0] = projectHelper.getProjectManager();
                            for(String members: projectHelper.getMembers()){
                                StoreMembers.add(members);
                            }
                            break;

                        }
                    }
                    int N = StoreMembers.size();
                    String[] members = new String[N];
                    String[] rank = new String[N];
                    for(int i=0;i<N;i++){
                        members[i]=StoreMembers.get(i);
                        if(Manager[0].equals(members[i])){
                            rank[i]="Project Manager";
                        }else{
                            rank[i]="Developer";
                        }
                    }

                    System.out.println("Length of n: "+ N);
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.memberlist);
                    recyclerView.setAdapter(new MemberAdapter(members,rank));
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PeopleActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }

            };
            CompletableFuture.runAsync(() -> {
                System.out.println("1==================================================================================");
                reference.addValueEventListener(eventListener);
                System.out.println("2==================================================================================");
            });
        }
    }

}