package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton addNewTask;
    private TaskAdapter taskAdapter;

//    private TaskDataBase taskDataBase;
    public static  HashMap<Integer, String> colorPriority = new HashMap<>();
    private MainViewModel viewModel;

//    private Handler handler = new Handler(Looper.getMainLooper());





    @Override
    protected void onResume() {
        super.onResume();
        viewModel.refreshList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        colorPriority.put(0, "#00FF00");
        colorPriority.put(1, "#FFD700");
        colorPriority.put(2, "#F44336");



        taskAdapter = new TaskAdapter();
        recyclerView.setAdapter(taskAdapter);
        viewModel.getCount().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                Toast.makeText(
                        MainActivity.this,
                        String.valueOf(count),
                        Toast.LENGTH_SHORT
                ).show();

            }
        });
        viewModel.getNotes().observe(
                this,
                new Observer<List<Task>>() {
                    @Override
                    public void onChanged(List<Task> tasks) {
                        taskAdapter.setTasks(tasks);
                    }
                }
        );
        taskAdapter.setOntaskClickListener(new TaskAdapter.OntaskClickListener() {
            @Override
            public void onTaskClick(Task task) {
                viewModel.showCount();
//                basetasks.remove(task.getId());
//                showTasks();

            }
        });


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(
                        0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Task task = taskAdapter.getTasks().get(position);
                viewModel.remove(task);
//                Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        viewModel.remove(task);
////                        taskDataBase.tasksDao().removeTask(task.getId());
////                        handler.post(new Runnable() {
////                            @Override
////                            public void run() {
////                                showTasks();
////                            }
////                        });
//                    }
//                });
//                thread.start();



            }
        });


        itemTouchHelper.attachToRecyclerView(recyclerView);
        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddingNewTask.newIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

//    private void showTasks() {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<Task> tasks = taskDataBase.tasksDao().getTasks();
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        taskAdapter.setTasks(tasks);
//                    }
//                });
//
//            }
//        });
//        thread.start();
//
//
////            view.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    basetasks.remove(task.getId());
////                    showTasks();
////                }
////            });
//
//
//
//    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerViewTasks);
        addNewTask = findViewById(R.id.btnAddNewTask);
    }


}