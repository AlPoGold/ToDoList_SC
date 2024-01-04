package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutTasks;
    private FloatingActionButton addNewTask;

    private ArrayList<Task> tasksExample = new ArrayList<>();
    private HashMap<Integer, String> colorPriority = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        colorPriority.put(0, "#00FF00");
        colorPriority.put(1, "#FFD700");
        colorPriority.put(2, "#FF0000");
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int rNum = random.nextInt(3);
            Task task = new Task(i, i+":Task", rNum);
            tasksExample.add(task);
        }

        showTasks();
    }

    private void showTasks() {
        for (Task task: tasksExample
             ) {
            View view = getLayoutInflater().inflate(
                    R.layout.task_item,
                    linearLayoutTasks,
                    false
            );

            TextView tvTask = view.findViewById(R.id.task_item);
            tvTask.setText(task.getText());
            int colorResId = task.getPriority();
            int parsedColor = Color.parseColor(colorPriority.get(colorResId));
            tvTask.setBackgroundColor(parsedColor);

            linearLayoutTasks.addView(view);
        }
    }

    private void initViews() {
        linearLayoutTasks = findViewById(R.id.linearLayoutTasks);
        addNewTask = findViewById(R.id.btnAddNewTask);
    }


}