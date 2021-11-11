package com.example.p_mat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.p_mat.Models.Developer;
import com.example.p_mat.Models.DeveloperModel;
import com.example.p_mat.Models.Notice;
import com.example.p_mat.Models.Organization;
import com.example.p_mat.Models.Project;
import com.example.p_mat.Models.ProjectHelper;
import com.example.p_mat.Models.ProjectManager;
import com.example.p_mat.Models.Task;
import com.example.p_mat.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AddProject extends AppCompatActivity implements DeveloperAdapter.ListItemClickListener{

    public ArrayList<String> pmIds = new ArrayList<String>();
    public ArrayList<String> pmEmails = new ArrayList<String>();
    public ArrayList<String> pmNames = new ArrayList<String>();
    public ArrayList<String> devIds = new ArrayList<String>();
    public ArrayList<String> devEmails = new ArrayList<String>();
    public ArrayList<String> devNames = new ArrayList<String>();
    public ArrayList<List<String>> devSkills = new ArrayList<List<String>>();
    public Organization organization;
    public Button GetDevs,SubmitProject;
    public EditText DesiredSkills,projectName,githubLink;
    public RecyclerView mRecyclerView;
    public TextView recyclerTitle;
    public ArrayList<DeveloperModel> recyclerDevs = new ArrayList<DeveloperModel>();
    Boolean found = false;
    public HashSet<String> selectedDevIds = new HashSet<String>();
    public Spinner mySpinner;
    public String organiaztionId,orgName;


    private DatabaseReference myDatabase,orgDatabase,projectDatabase,orgRef;
    private AutoCompleteTextView selectDev;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        myDatabase = FirebaseDatabase.getInstance().getReference("users");

        orgDatabase = FirebaseDatabase.getInstance().getReference("organizations");

        Spinner spinner = (Spinner) findViewById(R.id.pm_spinner);
        GetDevs = findViewById(R.id.get_devs);
        DesiredSkills = findViewById(R.id.desiredSkills);
        mRecyclerView = findViewById(R.id.dev_ranks);
        recyclerTitle = findViewById(R.id.list_title);
        projectName = findViewById(R.id.addProjectName);
        githubLink = findViewById(R.id.addGitHubLink);
        SubmitProject = findViewById(R.id.addProject);
        mySpinner = findViewById(R.id.pm_spinner);

        SharedPreferences sharedPreferences = this.getSharedPreferences("FIXED", Context.MODE_PRIVATE);
        organiaztionId = "-Mn_q4jynbHJS3npzGBX";
        orgName = sharedPreferences.getString("ORG","");
        System.out.println(organiaztionId+" ASDdas "+sharedPreferences.getString("ORG",""));

//        if(!found){
//            orgDatabase.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    for(DataSnapshot orgSnapshot: snapshot.getChildren()){
//                        System.out.println("--");
//                        Organization org = orgSnapshot.getValue(Organization.class);
//                        System.out.println(org.getName());
//                        if(org.getName().equals(orgName)){
//                            organization = org;
//                            found=true;
//                            System.out.println("found");
//                            break;
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//
//                }
//            });
//        }


        SubmitProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!projectName.getText().toString().isEmpty()){
                    if(!githubLink.getText().toString().isEmpty()){
                        if(!selectedDevIds.isEmpty()){
                            orgDatabase.orderByChild("name").equalTo(orgName).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    System.out.println(snapshot);
                                    if(snapshot.exists()){
                                        for(DataSnapshot childSnapshot:snapshot.getChildren()){
                                            Organization organization = childSnapshot.getValue(Organization.class);
                                            List<Project> projectList = organization.getProject();
                                            System.out.println(projectList);

                                            String pmName = mySpinner.getSelectedItem().toString();
                                            ProjectManager projectManager = new ProjectManager();
                                            for(int i=0;i<pmNames.size();i++){
                                                if(pmNames.get(i).equals(pmName)){
                                                    String pmId = pmIds.get(i);
                                                    String pmEmail = pmEmails.get(i);
                                                    projectManager = new ProjectManager(pmName,pmId,pmEmail);
                                                    break;
                                                }
                                            }
                                            List<Notice> notices = new ArrayList<Notice>();
                                            List<Task> tasks = new ArrayList<Task>();
                                            List<Developer> developers = new ArrayList<Developer>();
                                            projectDatabase = FirebaseDatabase.getInstance().getReference("projects");
                                            List<String> projectMembers = new ArrayList<String>();
                                            projectMembers.add(projectManager.email);

                                            for(int i=0;i<devIds.size();i++){
                                                if(selectedDevIds.contains(devIds.get(i))){
                                                    developers.add(new Developer(devNames.get(i),devIds.get(i),devEmails.get(i)));
                                                    projectMembers.add(devEmails.get(i));
                                                }
                                            }
                                            ProjectHelper projectHelper = new ProjectHelper("This is "+projectName.getText().toString(),projectName.getText().toString(),projectManager.email,projectMembers,orgName,githubLink.getText().toString());
                                            String id = projectDatabase.push().getKey();


                                            orgRef = FirebaseDatabase.getInstance().getReference("organization");


                                            System.out.println(developers);
                                            Project newProject = new Project(projectName.getText().toString(),githubLink.getText().toString(),developers,notices,tasks,projectManager);
                                            System.out.println(newProject);
                                            if(projectList!=null)
                                            projectList.add(newProject);
                                            else{
                                                projectList = new ArrayList<Project>();
                                                projectList.add(newProject);
                                            }
                                            System.out.println(projectList);
                                            projectDatabase.child(id).setValue(projectHelper);
                                            orgDatabase.child(organiaztionId).child("project").setValue(projectList);

                                            Toast.makeText(getApplicationContext(),"Project Added Successfully",Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(AddProject.this,Dashboard.class));
                                            finish();
                                            android.os.Process.killProcess(android.os.Process.myPid());
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }else{
                            Toast.makeText(getApplicationContext(),"Please select developers",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Please enter GiHub link",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Please enter project name",Toast.LENGTH_SHORT).show();
                }
            }
        });

        GetDevs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(devSkills);
                String url = "https://btp-sem5-ml.herokuapp.com/rank_devs";
                List<String> jsonResponses = new ArrayList<>();

                if(DesiredSkills.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter a skillset to rank developers",Toast.LENGTH_SHORT).show();
                    return;
                }

//                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//
//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        System.out.println("Done with api call");
//                        System.out.println(response.toString());
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        System.out.println("Error");
//                        System.out.println(error.toString());
//                    }
//                });
//                requestQueue.add(jsonObjectRequest);
                HashMap<String, Object> params= new HashMap<String,Object>();
                params.put("desired_skills",DesiredSkills.getText().toString());
                List<HashMap<String,Object>> candidateList = new ArrayList<HashMap<String,Object>>();
                for(int i=0;i<devIds.size();i++){
                    HashMap<String,Object> candidate = new HashMap<String, Object>();
                    candidate.put("id",devIds.get(i));
                    candidate.put("name",devNames.get(i));
                    candidate.put("skills",devSkills.get(i));
                    System.out.println(candidate);
                    candidateList.add(candidate);
                }
                params.put("candidates",candidateList);
                System.out.println(params);
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray arr = response.getJSONArray("ranklist");
                                    System.out.println(arr);
                                    recyclerDevs.clear();
                                    System.out.println("################################3");
                                    for(int i=0;i<arr.length();i++){
                                        JSONObject obj = arr.getJSONObject(i);
                                        String id = obj.getString("id");
                                        String name = obj.getString("name");
                                        double similarity = obj.getDouble("similarity")*100;
                                        DeveloperModel newDev = new DeveloperModel(name,id,similarity);
                                        System.out.println(newDev);
                                        recyclerDevs.add(newDev);
                                    }
                                    System.out.println(recyclerDevs);
                                    DeveloperAdapter adapter = new DeveloperAdapter(recyclerDevs,AddProject.this::onListItemClick);
                                    mRecyclerView.setAdapter(adapter);
                                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
                                    recyclerTitle.setText("Here's a list of developers ranked by simialrity with the desired skills using our smart ML assistant. Click on the developers to confirm their inclusion");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        },new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.e("Error: ", error.getMessage());
                            Log.e("VOLLEY", error.toString());
                            System.out.println(error.toString());
                            Toast.makeText(getApplicationContext(), "Failed " + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });

        orgDatabase.orderByChild("name").equalTo(orgName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                System.out.println(snapshot);
                if(snapshot.exists()){
                    for(DataSnapshot child:snapshot.getChildren()){
                        organization = child.getValue(Organization.class);
                        found=true;
                        organiaztionId = child.getKey();
                        System.out.println("@@@+"+child.getKey());
                        System.out.println(organization.getProjectManager());
                        System.out.println("found");
                        List<ProjectManager> projectManagers = organization.getProjectManager();

                        System.out.println("2#########################################");
                        for(int i=0;i<projectManagers.size();i++){
                            System.out.println(projectManagers.get(i).getId() + " " + projectManagers.get(i).getName() + " " + projectManagers.get(i).getEmail());
                            pmIds.add(projectManagers.get(i).getId());
                            pmNames.add(projectManagers.get(i).getName());
                            pmEmails.add(projectManagers.get(i).getEmail());
                        }
                        // Create an ArrayAdapter using the string array and a default spinner layout
                        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                                (getApplicationContext(), android.R.layout.simple_spinner_item,
                                        pmNames); //selected item will look like a spinner set from XML
                        // Specify the layout to use when the list of choices appears
                        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        // Apply the adapter to the spinner
                        spinner.setAdapter(spinnerArrayAdapter);

                        List<Developer> developers = organization.getDeveloper();

                        for(int i=0;i<developers.size();i++){
                            devEmails.add(developers.get(i).getEmail());
                            devNames.add(developers.get(i).getName());
                            myDatabase.orderByChild("email").equalTo(developers.get(i).getEmail()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists()){
                                        for(DataSnapshot devChild: snapshot.getChildren()){
                                            User newDev = devChild.getValue(User.class);
                                            List<String> newDevSkills = newDev.getSkills();
                                            System.out.println(newDevSkills);
                                            devIds.add(newDev.getId());
                                            devSkills.add(newDevSkills);
                                        }
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                        System.out.println(devSkills);

                        System.out.println(devEmails);
                        System.out.println(devNames);
                        System.out.println(devSkills);
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
//        while(!found)
        System.out.println(devSkills);
        System.out.println("1#########################################");
        if(found){
            List<ProjectManager> projectManagers = organization.getProjectManager();

            System.out.println("2#########################################");
            for(int i=0;i<projectManagers.size();i++){
                System.out.println(projectManagers.get(i).getId() + " " + projectManagers.get(i).getName() + " " + projectManagers.get(i).getEmail());
                pmIds.add(projectManagers.get(i).getId());
                pmNames.add(projectManagers.get(i).getName());
                pmEmails.add(projectManagers.get(i).getEmail());
            }





        }

    }

    @Override
    public void onListItemClick(View itemView,int position) {
        TextView IncludeDev = itemView.findViewById(R.id.include_dev);
        if(IncludeDev.getText().toString().equalsIgnoreCase("Selected: No")){
            Toast.makeText(this,"Selected "+recyclerDevs.get(position).getName(),Toast.LENGTH_SHORT).show();
            selectedDevIds.add(recyclerDevs.get(position).getId());
            IncludeDev.setText("Selected: Yes");
        }else{
            Toast.makeText(this,"Unselected "+recyclerDevs.get(position).getName(),Toast.LENGTH_SHORT).show();
            selectedDevIds.remove(recyclerDevs.get(position).getId());
            IncludeDev.setText("Selected: No");
        }
        System.out.println(selectedDevIds);
    }
}