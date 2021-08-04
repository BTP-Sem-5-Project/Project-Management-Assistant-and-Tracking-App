package com.example.p_mat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>{

    private String[] data;
    public NoticeAdapter(String[] data){
        this.data = data;
    }

    @Override
    public NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.notice_card,parent, false);
        return new NoticeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewHolder holder, int position) {
        String title = data[position];
        holder.title.setText(title);
    }

    @Override
    public int getItemCount() {
        return data.length;
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

