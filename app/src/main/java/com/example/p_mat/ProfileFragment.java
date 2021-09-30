package com.example.p_mat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p_mat.Models.NoticeHelper;
import com.example.p_mat.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    TextView textView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference("users");
        View PROFILEACTIVITY = inflater.inflate(R.layout.fragment_profile, container, false);
        System.out.println("Here @@@@");
        TextView name = (TextView) PROFILEACTIVITY.findViewById(R.id.name);
        TextView github = (TextView) PROFILEACTIVITY.findViewById(R.id.github);
        TextView email = (TextView) PROFILEACTIVITY.findViewById(R.id.email);
        TextView mobile = (TextView) PROFILEACTIVITY.findViewById(R.id.mobile);
        TextView qualification = (TextView) PROFILEACTIVITY.findViewById(R.id.qualification);
        TextView linkedin = (TextView) PROFILEACTIVITY.findViewById(R.id.linkedin);
        TextView exprience = (TextView) PROFILEACTIVITY.findViewById(R.id.exprience);
        TextView skills = (TextView) PROFILEACTIVITY.findViewById(R.id.skills);
        TextView available = (TextView) PROFILEACTIVITY.findViewById(R.id.status_viewer);
        TextView organization = (TextView) PROFILEACTIVITY.findViewById(R.id.organization);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("FIXED", Context.MODE_PRIVATE);
        String myemail = sharedPreferences.getString("EMAIL", "DEFAULT");

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("users");

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot allUsers : snapshot.getChildren()){
                    if(allUsers.child("email").getValue().equals(myemail)){
                        name.setText(allUsers.child("name").getValue().toString());
                        exprience.setText(allUsers.child("experience").getValue().toString());
                        qualification.setText(allUsers.child("qualification").getValue().toString());
                        github.setText(allUsers.child("github").getValue().toString());
                        linkedin.setText(allUsers.child("linkedin").getValue().toString());
                        email.setText(allUsers.child("email").getValue().toString());
                        mobile.setText(allUsers.child("phone").getValue().toString());
                        String skill = "";
                        List Sk =(ArrayList<String>) allUsers.child("skills").getValue();
                        for( int i=0;i<Sk.size();i++){
                            skill = skill + Sk.get(i);
                            if(i!=Sk.size()-1){
                                skill=skill+", ";
                            }
                        }
                        skills.setText(skill);
                        Boolean check = (Boolean)allUsers.child("available").getValue();
                        if(check) {
                            available.setText("AVAILABLE");
                        }else{
                            available.setText("UNAVAILABLE");
                        }
                        organization.setText(allUsers.child("organization").getValue().toString());
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        reference.addValueEventListener(eventListener);

//        myDatabase.orderByChild("email").equalTo(myid).addValueEventListener(new ValueEventListener() {
//            System.out.
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//
//                    for(DataSnapshot child: snapshot.getChildren()){
//                        User user = child.getValue(User.class);
//
//                        name.setText(user.getName());
//                        exprience.setText(user.getExperience());
//                        qualification.setText(user.getQualification());
//                        github.setText(user.getGithub());
//                        linkedin.setText(user.getLinkedin());
//                        email.setText(user.getEmail());
//                        mobile.setText(user.getPhone());
//                        String skill = "";
//                        for(String x: user.getSkills()){
//                            skill = skill + x+", ";
//                        }
//                        skills.setText(skill);
//                        if(user.getAvailable()) {
//                            available.setText("AVAILABLE");
//                        }else{
//                            available.setText("UNAVAILABLE");
//                        }
//                        organization.setText(user.getOrganization());
//                    }
//                }else{
//                    Toast.makeText(getActivity(),"Something Went Wrong",Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        return PROFILEACTIVITY;
    }

}