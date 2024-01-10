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
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    List<Task> tasks = new ArrayList<>();

    public void setOntaskClickListener(OntaskClickListener ontaskClickListener) {
        this.ontaskClickListener = ontaskClickListener;
    }

    private OntaskClickListener ontaskClickListener;

    public void setTasks(List<Task> tasks) {
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
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ontaskClickListener!=null) {
                    ontaskClickListener.onTaskClick(task);
                }
            }
        });

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

    interface OntaskClickListener{
        void onTaskClick(Task task);
    }
}
