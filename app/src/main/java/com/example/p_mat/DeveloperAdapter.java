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
    public ListItemClickListener mOnClickListener;

    public DeveloperAdapter(ArrayList<DeveloperModel> d,ListItemClickListener onClickListener){
        data = d;
        this.mOnClickListener = onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView DevName, DevSimilarity,IncludeDev;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            DevName = itemView.findViewById(R.id.dev_name);
            DevSimilarity = itemView.findViewById(R.id.dev_similarity);
            IncludeDev = itemView.findViewById(R.id.include_dev);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            mOnClickListener.onListItemClick(view,position);
        }
    }

    interface ListItemClickListener{
        void onListItemClick(View view,int position);
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
        holder.IncludeDev.setText("Selected: No");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
