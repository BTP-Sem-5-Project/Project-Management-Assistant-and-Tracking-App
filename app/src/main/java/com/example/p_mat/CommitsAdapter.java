package com.example.p_mat;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.p_mat.Models.Commit;
import com.google.firebase.database.annotations.NotNull;

import java.util.List;

public class CommitsAdapter extends RecyclerView.Adapter<CommitsAdapter.ViewHolder> {
    private List<Commit>mCommits;
    public CommitsAdapter(List<Commit>commits){
        mCommits=commits;
    }
    @NonNull
    @NotNull
    @Override
    public CommitsAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View commiteView = inflater.inflate(R.layout.commit,parent,false);
        ViewHolder viewHolder = new ViewHolder(commiteView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CommitsAdapter.ViewHolder holder, int position) {
        Commit commit=mCommits.get(position);

        TextView nameTextView=holder.nameText;
        nameTextView.setText(commit.getName());

        TextView dateTextView=holder.dateText;
        dateTextView.setText(commit.getDate());

        TextView msgTextView=holder.msgText;
        msgTextView.setText(commit.getMsg());
    }
    @Override
    public int getItemCount() {
        return mCommits.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView dateText;
        public TextView nameText;
        public TextView msgText;



        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            nameText=(TextView)itemView.findViewById(R.id.gitName);
            dateText=(TextView)itemView.findViewById(R.id.giDate);
            msgText=(TextView)itemView.findViewById(R.id.gitMsg);

        }
    }
}
