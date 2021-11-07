package com.example.p_mat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p_mat.Models.Developer;
import com.example.p_mat.Models.DeveloperModel;

import java.util.ArrayList;
import java.util.List;

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.ViewHolder> {
    public ArrayList<DeveloperModel> data = new ArrayList<DeveloperModel>();

    DeveloperAdapter(ArrayList<DeveloperModel> d){
        data = d;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView DevName, DevSimilarity;
        CheckBox IncludeDev;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            DevName = itemView.findViewById(R.id.dev_name);
            DevSimilarity = itemView.findViewById(R.id.dev_similarity);
            IncludeDev = itemView.findViewById(R.id.include_dev);
        }
    }


    @NonNull
    @Override
    public DeveloperAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dev_list_item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull DeveloperAdapter.ViewHolder holder, int position) {
        holder.DevName.setText(data.get(position).getName());
        holder.DevSimilarity.setText("Similarity = "+String.valueOf(Math.round(data.get(position).getMatch()*100.0)/100.0)+"%");
        holder.IncludeDev.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
