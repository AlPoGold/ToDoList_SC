package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayoutTasks;
    private FloatingActionButton addNewTask;

    private DataBaseTasks basetasks = DataBaseTasks.getInstance();
    private HashMap<Integer, String> colorPriority = new HashMap<>();


    @Override
    protected void onResume() {
        super.onResume();
        showTasks();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        colorPriority.put(0, "#00FF00");
        colorPriority.put(1, "#FFD700");
        colorPriority.put(2, "#F44336");


        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddingNewTask.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    private void showTasks() {
        linearLayoutTasks.removeAllViews();
        Log.d("TASK_BANK", basetasks.toString());
        for (Task task: basetasks.getTasks()
             ) {
            View view = getLayoutInflater().inflate(
                    R.layout.task_item,
                    linearLayoutTasks,
                    false
            );

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    basetasks.remove(task.getId());
                    showTasks();
                }
            });

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