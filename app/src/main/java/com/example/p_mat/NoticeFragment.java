package com.example.p_mat;

import android.content.Intent;
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

import com.example.p_mat.Models.NoticeHelper;
import com.example.p_mat.Models.TodoHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoticeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NoticeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoticeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoticeFragment newInstance(String param1, String param2) {
        NoticeFragment fragment = new NoticeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View NOTICEACTIVIY = inflater.inflate(R.layout.fragment_notice, container, false);

        FloatingActionButton fab = (FloatingActionButton) NOTICEACTIVIY.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), add_new_notice.class));
            }
        });

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        ArrayList<ArrayList<String>> StoreTodo = new ArrayList<ArrayList<String>>();
        DatabaseReference reference = rootNode.getReference("notice");
        String myproject = "id123";

        RecyclerView recyclerView = (RecyclerView) NOTICEACTIVIY.findViewById(R.id.noticeitems);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot allNotice : snapshot.getChildren()){
                    NoticeHelper noticeHelper = allNotice.getValue(NoticeHelper.class);
                    if(noticeHelper.getAssignedProject().equals(myproject)){
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(noticeHelper.getTitle());
                        temp.add(noticeHelper.getDescription());
                        StoreTodo.add(temp);
                        System.out.println("EMAIL -> " + noticeHelper.getTitle());
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
                    if(dataDescription[i].length() >= 350){
                        dataDescription[i] = dataDescription[i].substring(0, 350) + "...";
                    }
                }

                // create an adapter
                recyclerView.setAdapter(new NoticeAdapter(dataTitle, dataDescription));
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

        return NOTICEACTIVIY;
    }
}