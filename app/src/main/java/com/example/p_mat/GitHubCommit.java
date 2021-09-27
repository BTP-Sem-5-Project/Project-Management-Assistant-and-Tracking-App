package com.example.p_mat;
import androidx.appcompat.app.AppCompatActivity;
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

public class GitHubCommit extends AppCompatActivity {
    RecyclerView rvCommits;

    //Githun APi url Info
    String owner="nalin-programmer";
    String repository="Loco-Cart-Backend";
    String url="https://api.github.com/repos/"+owner+"/"+repository+"/commits";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_hub_commit);
        TextView textView=findViewById(R.id.repoName);
        textView.setText(repository);

        rvCommits=findViewById(R.id.rvCommits);
        rvCommits.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.buttonBarChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),GitBarChart.class));
            }
        });

        MakeVolleyConnection(url);
    }
    public void MakeVolleyConnection(String url){
        ArrayList<Commit>commitsArrayList=new ArrayList<Commit>();
        //Requesting Data from GITHUB api
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0;i<response.length();i++){
                        JSONObject obj=response.getJSONObject(i);
                        JSONObject committer=obj.getJSONObject("committer");
                        JSONObject commit = obj.getJSONObject("commit");
                        JSONObject author = commit.getJSONObject("author");
                        String commiterName=committer.getString("login");
                        String commitDate=author.getString("date");
                        String commitMsg =commit.getString("message");
                        commitsArrayList.add(new Commit(commiterName,commitDate,commitMsg));
                    }
                    CommitsAdapter adapter= new CommitsAdapter(commitsArrayList);
                    rvCommits.setAdapter(adapter);
                }catch (JSONException | ParseException e) {
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