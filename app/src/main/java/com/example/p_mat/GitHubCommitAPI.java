package com.example.p_mat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.p_mat.Models.Commit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GitHubCommitAPI extends AppCompatActivity {
    RecyclerView rvCommits;
    public String owner,repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub_commit);


        //Githun APi url Info
        String link = getIntent().getStringExtra("projectLink");
        String sublink = link.substring(19);
        System.out.println(link);
        System.out.println(sublink);
        System.out.println(sublink.indexOf('/'));
        owner=sublink.substring(0,sublink.indexOf('/'));
        repository=sublink.substring(sublink.indexOf('/')+1);


        TextView textView=findViewById(R.id.repoName);
        textView.setText(repository);
        //https://api.github.com/repos/nalin-programmer/Loco-Cart-Frontend/commits

        rvCommits=findViewById(R.id.rvCommits);
        rvCommits.setLayoutManager(new LinearLayoutManager(this));
        AppCompatButton appCompatButton;
        MakeVolleyConnection();
    }

    public void MakeVolleyConnection(){
        ArrayList<Commit>commitsArrayList=new ArrayList<Commit>();
        //Requesting Data from GITHUB api
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        for(int j=0;j<15;j++) {
            String url="https://api.github.com/repos/"+owner+"/"+repository+"/commits?page="+j+"&per_page=100";
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            if(response.length()==0) return;
                            JSONObject obj = response.getJSONObject(i);
                            JSONObject committer = obj.getJSONObject("committer");
                            JSONObject commit = obj.getJSONObject("commit");
                            JSONObject author = commit.getJSONObject("author");
                            String commiterName = author.getString("name");
                            String commitDate = author.getString("date");
                            String commitMsg = commit.getString("message");
                            commitsArrayList.add(new Commit(commiterName, commitDate, commitMsg));
                        }
                        CommitsAdapter adapter = new CommitsAdapter(commitsArrayList);
                        rvCommits.setAdapter(adapter);
                        findViewById(R.id.buttonBarChart).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(), GitHubStatisticsAPI.class);
                                intent.putExtra("owner",owner);
                                intent.putExtra("repository",repository);
                                startActivity(intent);
                            }
                        });

                    } catch (JSONException | ParseException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });

            requestQueue.add(jsonArrayRequest);
        }
    }
}