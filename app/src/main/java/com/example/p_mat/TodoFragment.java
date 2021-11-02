package com.example.p_mat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.p_mat.Models.TodoHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TodoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TodoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TodoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodoFragment newInstance(String param1, String param2) {
        TodoFragment fragment = new TodoFragment();
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("FIXED", Context.MODE_PRIVATE);
        String EmailPersonal = sharedPreferences.getString("EMAIL","DEFAULT");
        Toast.makeText(this.getContext(),EmailPersonal,Toast.LENGTH_SHORT).show();
        // Inflate the layout for this fragment
        View TODOACTIVIY = inflater.inflate(R.layout.fragment_todo, container, false);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("organization");
        ArrayList<String> MEMBERS = new ArrayList<>();
        String myproject = sharedPreferences.getString("ORG", "DEFAULT");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot allorg : snapshot.getChildren()){
                    System.out.println(allorg.getKey());
                    if(allorg.getKey().equals(myproject)){
                        ValueEventListener eventListener10 = new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for(DataSnapshot all : snapshot.getChildren()){
                                        if(all.getKey().equals("members")) {
                                            List<String> group = (List<String>) allorg.child("members").getValue();
                                            for (int i = 0; i < group.size(); i++) {
                                                MEMBERS.add(group.get(i));
                                            }
                                        }
                                    }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        };
                        reference.child(myproject).addValueEventListener(eventListener10);
                    }
                }

                System.out.println(MEMBERS);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        reference.addValueEventListener(eventListener);


        FloatingActionButton fab = (FloatingActionButton) TODOACTIVIY.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(getActivity(), add_new_todo.class);
                ii.putExtra("member", MEMBERS);
                startActivity(ii);
            }
        });

        ArrayList<ArrayList<String>> StoreTodo = new ArrayList<ArrayList<String>>();
        DatabaseReference reference2 = rootNode.getReference("todo");
        // get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) TODOACTIVIY.findViewById(R.id.todoItem);
        // get reference to layoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));




        ValueEventListener eventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot allTodo : snapshot.getChildren()){
                    TodoHelper todoHelper = allTodo.getValue(TodoHelper.class);
                    if(todoHelper.getAssignedEmail().equals(sharedPreferences.getString("EMAIL", "DEFAULT"))){
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(todoHelper.getTitle());
                        temp.add(todoHelper.getDescription());
                        StoreTodo.add(temp);
                        System.out.println("EMAIL -> " + todoHelper.getTitle());
                        for(int i = 0; i < temp.size(); i ++){
                            System.out.println("VAL -> " + temp.get(i));
                        }

                        System.out.println("DESCRITPTION " + StoreTodo.size());
                    }
                }
                int N = StoreTodo.size();
                String[] dataTitle = new String[N];
                String[] dataDescription = new String[N];

//                Toast.makeText(getContext(), "HH" + N, Toast.LENGTH_SHORT).show();

                for(int i = 0; i < N; i ++){
                    dataTitle[i] = StoreTodo.get(i).get(0);
                    dataDescription[i] = StoreTodo.get(i).get(1);
                    if(dataDescription[i].length() >= 150){
                        dataDescription[i] = dataDescription[i].substring(0, 150) + "...";
                    }
                }

                // create an adapter
                recyclerView.setAdapter(new ToDoAdapter(dataTitle, dataDescription));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        CompletableFuture.runAsync(() -> {
                System.out.println("1==================================================================================");
                reference2.addValueEventListener(eventListener2);
                System.out.println("2==================================================================================");
        });

        return TODOACTIVIY;
    }
}