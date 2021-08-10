package com.example.p_mat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.OrganizationViewHolder>{
    private String[] data;
    public OrganizationAdapter(String[] data){
        this.data = data;
    }

    @Override
    public OrganizationAdapter.OrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.organization_project_card,parent, false);
        return new OrganizationAdapter.OrganizationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrganizationAdapter.OrganizationViewHolder holder, int position) {
        String projecttitle = data[position];
        holder.projecttitle.setText(projecttitle);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class OrganizationViewHolder extends RecyclerView.ViewHolder{
        TextView projecttitle;
        TextView projectdescription;
        public OrganizationViewHolder(@NonNull View itemView) {
            super(itemView);
            projecttitle = (TextView) itemView.findViewById(R.id.projecttitle);
            projectdescription = (TextView) itemView.findViewById(R.id.projectdescription);
        }
    }
}
