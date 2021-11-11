package com.example.p_mat;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import java.util.List;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.anychart.APIlib;
import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import com.anychart.enums.LegendLayout;
import com.github.mikephil.charting.data.BarEntry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class GitHubStatisticsAPI extends AppCompatActivity {
    //Githun APi url Info
    String owner;
    String repository;
    String urlForBarchart;
    String urlForPiechart;
    HashMap<String,Integer>map=new HashMap<>();
    HashMap<Integer,Integer>hmap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_statistics_chart);
        owner = getIntent().getStringExtra("owner");
        repository = getIntent().getStringExtra("repository");
        System.out.println(owner);
        System.out.println(repository);
        urlForBarchart="https://api.github.com/repos/"+owner+"/"+repository+"/stats/commit_activity";
        urlForPiechart="https://api.github.com/repos/"+owner+"/"+repository+"/commits?per_page=100";
        MakeVolleyConnectionPieChart();
        MakeVolleyConnectionBarChart();

    }
    public void setPieChart(){
        AnyChartView pieChart=findViewById(R.id.newPieChart);
        APIlib.getInstance().setActiveAnyChartView(pieChart);
        Pie pie= AnyChart.pie();
        List<DataEntry>dataEntries=new ArrayList<>();
        for (String name: map.keySet()) {
            String key = name.toString();
            int value = map.get(name);
            if(!key.equals("web-flow")){
           dataEntries.add(new ValueDataEntry(key,value));
            }
        }
        pie.data(dataEntries);
        pie.legend().itemsLayout(LegendLayout.VERTICAL);
        pie.title("Contribution");

        pieChart.setChart(pie);
    }
    public void BarChart(){
        AnyChartView barChart=findViewById(R.id.newBarChart);
        APIlib.getInstance().setActiveAnyChartView(barChart);
        Cartesian cartesian = AnyChart.column();
        List<DataEntry>dataEntries=new ArrayList<>();
        for (Integer name: hmap.keySet()) {
            String key = name.toString();
            int value = hmap.get(name);
            dataEntries.add(new ValueDataEntry(key,value));
        }
        Column column = cartesian.column(dataEntries);
        cartesian.animation(true);
        cartesian.title("Commits/Week");
        cartesian.xAxis(0).title("Week");
        cartesian.yAxis(0).title("Commit");
        barChart.setChart(cartesian);

    }
    public void MakeVolleyConnectionBarChart(){
        //String url="https://api.github.com/repos/nalin-programmer/Loco-Cart-Backend/stats/commit_activity";
        //Requesting Data from GITHUB api
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, urlForBarchart, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0;i<response.length();i++){
                        JSONObject obj=response.getJSONObject(i);
                        int total=obj.getInt("total");
                        hmap.put(i,total);

                       // visitors.add(new BarEntry(i,total));
                    }
                    //setBarChart(visitors);
                    BarChart();

                }catch (JSONException e) {
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


    public void MakeVolleyConnectionPieChart(){
        ArrayList<BarEntry> visitors=new ArrayList<>();
        //Requesting Data from GITHUB api
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, urlForPiechart, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0;i<response.length();i++){
                        JSONObject obj=response.getJSONObject(i);
                        JSONObject committer=obj.getJSONObject("committer");
                        String committerName=committer.getString("login");

                            if (map.containsKey(committerName)) {
                                map.put(committerName, map.get(committerName) + 1);
                            }
                            else {
                                map.put(committerName, 1);

                        }

                    }
                    setPieChart();

                }catch (JSONException e) {
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
