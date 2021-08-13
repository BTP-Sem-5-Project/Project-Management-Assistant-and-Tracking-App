package com.example.p_mat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>{

    private String[] dataTitle;
    private String[] dataDescription;


    public ToDoAdapter(String[] dataTitle, String[] dataDescription){
        this.dataTitle = dataTitle;
        this.dataDescription = dataDescription;
    }

    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.todo_card,parent, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        String title = dataTitle[position];
        holder.title.setText(title);
        String description = dataDescription[position];
        holder.description.setText(description);
    }

    @Override
    public int getItemCount() {
        return dataTitle.length;
    }

    public class ToDoViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView month;
        TextView year;
        TextView time;
        TextView title;
        TextView description;
        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.tododate);
            month = (TextView) itemView.findViewById(R.id.todomonth);
            year = (TextView) itemView.findViewById(R.id.todoyear);
            time = (TextView) itemView.findViewById(R.id.todoDate);
            title = (TextView) itemView.findViewById(R.id.todotitle);
            description = (TextView) itemView.findViewById(R.id.tododescription);
        }
    }

}
