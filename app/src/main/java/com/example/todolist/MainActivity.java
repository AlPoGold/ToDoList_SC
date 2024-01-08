package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    private RecyclerView recyclerView;
    private FloatingActionButton addNewTask;
    private TaskAdapter taskAdapter;

    private DataBaseTasks basetasks = DataBaseTasks.getInstance();
    public static  HashMap<Integer, String> colorPriority = new HashMap<>();



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



        taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);








        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddingNewTask.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    private void showTasks() {
        taskAdapter.setTasks(basetasks.getTasks());

//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    basetasks.remove(task.getId());
//                    showTasks();
//                }
//            });



    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewTasks);
        addNewTask = findViewById(R.id.btnAddNewTask);
    }


}