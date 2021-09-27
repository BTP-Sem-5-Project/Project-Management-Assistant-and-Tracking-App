package com.example.p_mat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InvitationAdapter extends RecyclerView.Adapter<InvitationAdapter.InvitationViewHolder> {
    private String[] orgTitle;
    private String[] action;

    public InvitationAdapter(String[] orgTitle, String[] action){
        this.orgTitle = orgTitle;
        this.action = action;
    }

    @Override
    public InvitationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.invitation_card,parent, false);
        return new InvitationViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull InvitationAdapter.InvitationViewHolder holder, int position) {
        String title = orgTitle[position];
        holder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        return orgTitle.length;
    }

    public class InvitationViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public InvitationViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
