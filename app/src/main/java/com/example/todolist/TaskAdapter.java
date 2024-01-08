package com.example.todolist;

import static com.example.todolist.MainActivity.colorPriority;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    ArrayList<Task> tasks = new ArrayList<>();

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.task_item,
                parent,
                false
        );
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder viewHolder, int position) {
        Task task = tasks.get(position);

        viewHolder.textViewTask.setText(task.getText());
        int colorResId = task.getPriority();
        int parsedColor = Color.parseColor(colorPriority.get(colorResId));
        viewHolder.textViewTask.setBackgroundColor(parsedColor);

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTask = itemView.findViewById(R.id.task_item);
        }
    }
}