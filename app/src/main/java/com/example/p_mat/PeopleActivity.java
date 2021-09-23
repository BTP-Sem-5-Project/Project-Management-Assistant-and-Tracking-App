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
            String[] temprory = new String[4];
            String[] rank = new String[4];
            temprory[0] = "Person1";
            temprory[1] = "Person2";
            temprory[2] = "Person3";
            temprory[3] = "Person4";
            rank[0] = "CEO";
            rank[1]="HR";
            rank[2]="Developer";
            rank[3]="Developer";
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.memberlist);
            MemberAdapter adapter = new MemberAdapter(temprory,rank);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
        }else{
            FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
            System.out.println("Reached else");
            ArrayList<String> StoreMembers = new ArrayList<String>();
            final String[] Manager = {new String()};

            DatabaseReference reference = rootNode.getReference("projects");
            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.i("content","@@@@@"+content);
                    for(DataSnapshot allProjects : snapshot.getChildren()){
                        ProjectHelper projectHelper = allProjects.getValue(ProjectHelper.class);
                        System.out.println(projectHelper.getName());
                        if(projectHelper.getName().equals(content)){
                            System.out.println("Found"+ content);
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

//                    recyclerView.setAdapter(new OrganizationAdapter(dataName, dataDescription));

                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.memberlist);
                    recyclerView.setAdapter(new MemberAdapter(members,rank));
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PeopleActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
//                    MemberAdapter adapter = new MemberAdapter(members,rank);
//                    recyclerView.setHasFixedSize(true);
//                    recyclerView.setAdapter(adapter);
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