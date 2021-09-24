package com.example.p_mat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.p_mat.Models.InvitationHelper;
import com.example.p_mat.Models.NoticeHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

public class InvitationBox extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitation_box);

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("invitation");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.invitationitems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<ArrayList<String>> StoreTodo = new ArrayList<ArrayList<String>>();


        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot allInvite : snapshot.getChildren()){
                    InvitationHelper inviteHelper = allInvite.getValue(InvitationHelper.class);
                    System.out.println(inviteHelper.getEmail());
                    if(inviteHelper.getEmail().equals("preritkumarjha19@cse.iiitp.ac.in")){
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(inviteHelper.getTitle());
                        StoreTodo.add(temp);
                        System.out.println("OKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
                    }
                }
                int N = StoreTodo.size();
                String[] dataTitle = new String[N];
                String[] action = new String[N];
                System.out.println(N);

                for(int i = 0; i < N; i ++){
                    dataTitle[i] = StoreTodo.get(i).get(0);
                    action[i] = "NULL";
                }

                // create an adapter
                recyclerView.setAdapter(new InvitationAdapter(dataTitle, action));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        System.out.println("1==================================================================================");
        reference.addValueEventListener(eventListener);
        System.out.println("2==================================================================================");

    }
}