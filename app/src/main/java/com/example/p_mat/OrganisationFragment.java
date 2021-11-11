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
import android.widget.TextView;
import android.widget.Toast;

import com.example.p_mat.Models.OrganizationHelper;
import com.example.p_mat.Models.ProjectHelper;
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
 * Use the {@link OrganisationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrganisationFragment extends Fragment {

    public Button AddProjectButton;

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

        FloatingActionButton fab2 = (FloatingActionButton) ORGANIZATIONACTIVITY.findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), InvitationBox.class));
            }
        });




        Button peopleButton = (Button) ORGANIZATIONACTIVITY.findViewById(R.id.peoplebutton);
        Button inviteButton = (Button) ORGANIZATIONACTIVITY.findViewById(R.id.invitebutton);

        Button addNoticeButton = (Button) ORGANIZATIONACTIVITY.findViewById(R.id.addnoticebuttonorganisation);
        Button createOrganizationButton = (Button) ORGANIZATIONACTIVITY.findViewById(R.id.createorganization);
        addNoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrganisationFragment.this.getActivity(),add_new_notice.class);
                startActivity(intent);

            }
        });

        createOrganizationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrganisationFragment.this.getActivity(),CreateOrganization.class);
                startActivity(intent);
            }
        });





        AddProjectButton = ORGANIZATIONACTIVITY.findViewById(R.id.addProjectButton);

        AddProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrganisationFragment.this.getActivity(),AddProject.class));
            }
        });
        inviteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(OrganisationFragment.this.getActivity(),InviteNewMember.class);
                startActivity(intent);
            }
        });
        RecyclerView recyclerView = (RecyclerView) ORGANIZATIONACTIVITY.findViewById(R.id.projectlist);
        TextView organizationName = (TextView) ORGANIZATIONACTIVITY.findViewById(R.id.organizationName);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        peopleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onClickPeopleButton();
            }
        });

        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        ArrayList<ArrayList<String>> StoreProject = new ArrayList<ArrayList<String>>();
        DatabaseReference reference = rootNode.getReference("projects");
        String myEmail = "nalinagrawal333@gmail.com";
        String myOrganization = "btp5";
        organizationName.setText(myOrganization);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot allProjects : snapshot.getChildren()){
                    ProjectHelper projectHelper = allProjects.getValue(ProjectHelper.class);
                    if(projectHelper.getOrganization().equals(myOrganization)){
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(projectHelper.getName());
                        temp.add(projectHelper.getDescription());
                        temp.add(projectHelper.getGitHub());
                        StoreProject.add(temp);
                    }
                }
                int N = StoreProject.size();
                System.out.println("Length of n: "+ N);
                String[] dataName = new String[N];
                String[] dataDescription = new String[N];
                String[] dataLink = new String[N];


                for(int i = 0; i < N; i ++){
                    dataName[i] = StoreProject.get(i).get(0);
                    dataDescription[i] = StoreProject.get(i).get(1);
                    if(dataDescription[i].length() >= 150){
                        dataDescription[i] = dataDescription[i].substring(0, 150) + "...";
                    }
                    dataLink[i] = StoreProject.get(i).get(2);
                }
                // create an adapter
                OrganizationAdapter.RecycleViewClickListener listener;
                listener = new OrganizationAdapter.RecycleViewClickListener() {
                    @Override
                    public void onClick(View v, int positionId) {
                        Intent intent = new Intent(OrganisationFragment.this.getActivity(),ProjectPage.class);
                        intent.putExtra("projectName",dataName[positionId]);
                        intent.putExtra("projectDescription", dataDescription[positionId]);
                        intent.putExtra("projectLink",dataLink[positionId]);
                        startActivity(intent);
                    }
                };



                recyclerView.setAdapter(new OrganizationAdapter(dataName, dataDescription,listener));
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



        return ORGANIZATIONACTIVITY;
    }
    public void onClickPeopleButton(){
        Intent intent = new Intent(OrganisationFragment.this.getActivity(),PeopleActivity.class);
        intent.putExtra("content", "organization");
        startActivity(intent);
    }

}