package com.example.p_mat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.p_mat.Models.TodoHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//      storeNewTodo();
//      retrieveUserTodo();


        // Inflate the layout for this fragment
        View TODOACTIVIY = inflater.inflate(R.layout.fragment_todo, container, false);
        // get a reference to recyclerView
        RecyclerView recyclerView = (RecyclerView) TODOACTIVIY.findViewById(R.id.todoItem);
        // get reference to layoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        String[] temprory = new String[4];
        temprory[0] = "Prerit";
        temprory[1] = "Vijay";
        temprory[2] = "Nalin";
        temprory[3] = "Himanshu";

        // create an adapter
        recyclerView.setAdapter(new ToDoAdapter(temprory));

        return TODOACTIVIY;
    }

    private void retrieveUserTodo() {
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("todo");

        String myEmail = "preritkrjha@gmail.com";

        System.out.println("==================================================================================");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot allTodo : snapshot.getChildren()){
                    TodoHelper todoHelper = allTodo.getValue(TodoHelper.class);
                    System.out.println("DESCRITPTION " + todoHelper.getDescription());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        reference.addValueEventListener(eventListener);
        System.out.println("==================================================================================");
    }

    public void storeNewTodo(){
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("todo");


        String DeadlineDate = "27112001"; // DDMMYYYY
        String DeadlineTime = "0300"; // HHMM
        String Title = "Backend Work"; // Not more than 25 characters
        String Description = "Finish Todo Backend before the deadline, its urgent"; // Not more than 60 characters
        Boolean Completed = false; // true or false
        String AssignedEmail = "preritkrjha@gmail.com";

        TodoHelper todoHelper = new TodoHelper(DeadlineDate, DeadlineTime, Title, Description, Completed, AssignedEmail);

        String id = reference.push().getKey();

        reference.child(id).setValue(todoHelper);
    }
}