package com.example.p_mat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p_mat.Models.InvitationHelper;
import com.example.p_mat.Models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvitationAdapter extends RecyclerView.Adapter<InvitationAdapter.InvitationViewHolder> {
    private String[] orgTitle;
    private String[] action;

    public InvitationAdapter(String[] orgTitle, String[] action){
        this.orgTitle = orgTitle;
        this.action = action;
    }

    @Override
    public InvitationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.invitation_card,parent, false);
        return new InvitationViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull InvitationAdapter.InvitationViewHolder holder, int position) {
        String title = orgTitle[position];
        holder.title.setText(title);
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call activity.
                Toast.makeText(v.getContext(), "Accepted", Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("FIXED", Context.MODE_PRIVATE);
                String email = sharedPreferences.getString("EMAIL", "DEFAULT");
                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                DatabaseReference reference = rootNode.getReference("users");
                DatabaseReference reference2 = rootNode.getReference("organization");



                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot alluser : snapshot.getChildren()){
                            User user = alluser.getValue(User.class);
                            HashMap<String, Object> result = new HashMap<>();
                            result.put("organization", title);
                            if(user.getEmail().equals(email)){
                                reference.child(user.getId()).updateChildren(result);
                                alluser.getRef().removeValue();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };

                reference.addValueEventListener(eventListener);

                ValueEventListener eventListener2 = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot allorg : snapshot.getChildren()){
                            System.out.println("------------------------------------------------------------------");
                            System.out.println(allorg.getKey());
                            Toast.makeText(v.getContext(), allorg.getKey(), Toast.LENGTH_SHORT).show();
                            if(allorg.getKey().equals(title)){
                                ArrayList<String> group = new ArrayList<>();
                                System.out.println(allorg.getKey());

                                ArrayList<String> finalGroup = group;
                                ValueEventListener eventListener10 = new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        for(DataSnapshot all : snapshot.getChildren()){
                                            if(all.getKey().equals("members")){
                                                System.out.println(allorg.getKey());
                                                List<String> g = (List<String>) allorg.child("members").getValue();
                                                for(int i = 0; i < g.size(); i ++){
                                                    finalGroup.add(g.get(i));
                                                }
                                            }
                                        }
                                        HashMap<String, Object> result = new HashMap<>();
                                        int ss = finalGroup.size();
                                        result.put(ss+"", email);
                                        for(int i = 0; i < ss; i ++){
                                            result.put(i+"", finalGroup.get(i));
                                            System.out.println(finalGroup.get(i));
                                        }
                                        finalGroup.clear();
                                        reference2.child(title).child("members").setValue(result);

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                };
                                reference2.child(title).addListenerForSingleValueEvent(eventListener10);




                            }
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
                reference2.addListenerForSingleValueEvent(eventListener2);



            }
        });
        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call activity.
                Toast.makeText(v.getContext(), "Rejected", Toast.LENGTH_SHORT).show();


                SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("FIXED", Context.MODE_PRIVATE);
                String email = sharedPreferences.getString("EMAIL", "DEFAULT");
                FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                DatabaseReference reference = rootNode.getReference("invitation");



                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot alluser : snapshot.getChildren()){
                            InvitationHelper user = alluser.getValue(InvitationHelper.class);


                            if(user.getEmail().equals(email) && user.getTitle().equals(title)){
                                alluser.getRef().removeValue();
                            }
                        }
                        v.getContext().startActivity(new Intent(v.getContext(), Dashboard.class));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };

                reference.addListenerForSingleValueEvent(eventListener);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orgTitle.length;
    }

    public class InvitationViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        Button accept, reject;
        public InvitationViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            accept = (Button) itemView.findViewById(R.id.accept);
            reject = (Button) itemView.findViewById(R.id.reject);
        }
    }
}
