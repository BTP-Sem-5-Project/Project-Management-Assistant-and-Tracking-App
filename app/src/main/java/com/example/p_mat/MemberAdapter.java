package com.example.p_mat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {
    private String[] data;
    public MemberAdapter(String[] data){
        this.data = data;
    }

    @Override
    public MemberAdapter.MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.organization_member_card,parent, false);
        return new MemberAdapter.MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberAdapter.MemberViewHolder holder, int position) {
        String membertitle = data[position];
        holder.membertitle.setText(membertitle);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class MemberViewHolder extends RecyclerView.ViewHolder{
        TextView membertitle;
        TextView memberdescription;
        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            membertitle = (TextView) itemView.findViewById(R.id.membername);
            memberdescription = (TextView) itemView.findViewById(R.id.memberrank);
        }
    }
}
