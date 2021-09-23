package com.example.p_mat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrganizationAdapter extends RecyclerView.Adapter<OrganizationAdapter.OrganizationViewHolder>{
    private String[] name,description;
    private RecycleViewClickListener listener;

    public OrganizationAdapter(String[] name,String[] description, RecycleViewClickListener listener){
        this.name = name;
        this.description=description;
        this.listener=listener;
    }

    @Override
    public OrganizationAdapter.OrganizationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.organization_project_card,parent, false);
        return new OrganizationAdapter.OrganizationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrganizationAdapter.OrganizationViewHolder holder, int position) {
        String projecttitle = name[position];
        String projectdescription = description[position];
        holder.projecttitle.setText(projecttitle);
        holder.projectdescription.setText(projectdescription);
    }
    public interface RecycleViewClickListener{
        void onClick(View v, int positionId);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public class OrganizationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView projecttitle;
        TextView projectdescription;
        public OrganizationViewHolder(@NonNull View itemView) {
            super(itemView);
            projecttitle = (TextView) itemView.findViewById(R.id.projecttitle);
            projectdescription = (TextView) itemView.findViewById(R.id.projectdescription);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition());
        }
    }
}
