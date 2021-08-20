package com.example.p_mat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>{

    private String[] dataTitle;
    private String[] dataDescription;

    public NoticeAdapter(String[] dataTitle, String[] dataDescription){
        this.dataTitle = dataTitle;
        this.dataDescription = dataDescription;
    }

    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.notice_card,parent, false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        String title = dataTitle[position];
        holder.title.setText(title);
        String description = dataDescription[position];
        holder.description.setText(description);
    }

    @Override
    public int getItemCount() {
        return dataTitle.length;
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder{
        TextView dateandtime;
        TextView subtitle;
        TextView title;
        TextView description;
        public NoticeViewHolder(@NonNull View itemView) {
            super(itemView);
            dateandtime = (TextView) itemView.findViewById(R.id.noticedateandtime);
            title = (TextView) itemView.findViewById(R.id.noticetitle);
            subtitle = (TextView) itemView.findViewById(R.id.noticesubtitle);
            description = (TextView) itemView.findViewById(R.id.noticedescription);
        }
    }

}

