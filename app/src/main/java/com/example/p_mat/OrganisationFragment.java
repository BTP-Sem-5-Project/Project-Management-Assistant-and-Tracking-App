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
import android.widget.Button;

import com.example.p_mat.Models.OrganizationHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrganisationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrganisationFragment extends Fragment {


    // Organization: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // Organization: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrganisationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrganisationFragment.
     */
    // Organization: Rename and change types and number of parameters
    public static OrganisationFragment newInstance(String param1, String param2) {
        OrganisationFragment fragment = new OrganisationFragment();
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
            View ORGANIZATIONACTIVITY = inflater.inflate(R.layout.fragment_organisation, container, false);

            Button peopleButton = (Button) ORGANIZATIONACTIVITY.findViewById(R.id.peoplebutton);
            RecyclerView recyclerView = (RecyclerView) ORGANIZATIONACTIVITY.findViewById(R.id.projectlist);

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            peopleButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    onClickPeopleButton();
                }
            });
            FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
            ArrayList<ArrayList<String>> StoreProject = new ArrayList<ArrayList<String>>();;
            DatabaseReference reference = rootNode.getReference("project");
            String myEmail = "nalinagrawal333@gmail.com";




            return ORGANIZATIONACTIVITY;
    }
    public void onClickPeopleButton(){
        Intent intent = new Intent(OrganisationFragment.this.getActivity(),PeopleActivity.class);
        intent.putExtra("content", "organization");
        startActivity(intent);
    }
}