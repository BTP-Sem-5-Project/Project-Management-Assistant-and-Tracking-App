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
    private String[] deadlineTime;
    private String[] deadllineDate;

    public ToDoAdapter(String[] dataTitle, String[] dataDescription,String[] deadlineDate,String[] deadlineTime){
        this.dataTitle = dataTitle;
        this.dataDescription = dataDescription;
        this.deadlineTime = deadlineTime;
        this.deadllineDate = deadlineDate;
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
        String hour = deadlineTime[position].substring(0,2);
        String Min = deadlineTime[position].substring(2);
        String Year = deadllineDate[position].substring(4);
        String Month = deadllineDate[position].substring(2,4);
        String Day = deadllineDate[position].substring(0,2);
        holder.date.setText(Day);
        holder.month.setText(Month);
        holder.year.setText(Year);
        holder.time.setText(hour+""+Min+"");

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
