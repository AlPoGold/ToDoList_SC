package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class AddingNewTask extends AppCompatActivity {
    public static final String ERROR_INPUT_NEW_TASK = "Please, enter text of new task";
    private EditText etNewTask;
    private RadioGroup priority;
    private RadioButton lowPriority;
    private RadioButton mediumPriority;
    private RadioButton highPriority;
    private Button btnSaveNewTask;

    private String textTask;
    private int priorityId;

    private  AddTaskViewModel viewModel;

//    private DataBaseTasks baseTasks = DataBaseTasks.getInstance();
//    private TaskDataBase taskDataBase = TaskDataBase.getInstance(getApplication());
//
//    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_new_task);
        initViews();

        viewModel = new ViewModelProvider(this).get(AddTaskViewModel.class);
        viewModel.getCloseActivity().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean closeActivity) {
                if(closeActivity) finish();
            }
        });
        lowPriority.setChecked(true);


        btnSaveNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        textTask = getTextNewTask();
        priorityId = getPriorityInt();
        Task task = new Task( textTask, priorityId);
        viewModel.saveTask(task);

        Log.d("NEW_TASK", task.toString());
    }

    private int getPriorityInt() {
        if(mediumPriority.isChecked()) return 1;
        else if (lowPriority.isChecked()) {
            return 0;
        }else return 2;
    }

    private String getTextNewTask() {
        String text = etNewTask.getText().toString();

            if (text.isEmpty()){
                Toast.makeText(
                        this,
                        ERROR_INPUT_NEW_TASK,
                        Toast.LENGTH_SHORT).show();
            }


        return text;

    }

    private void initViews() {
        etNewTask = findViewById(R.id.etNewtask);
        priority = findViewById(R.id.rgPriority);
        lowPriority = findViewById(R.id.rbLowPriority);
        mediumPriority = findViewById(R.id.rbMediumPriority);
        highPriority = findViewById(R.id.rbHighPriority);

        btnSaveNewTask = findViewById(R.id.btnSaveNewtask);
    }

    public static Intent newIntent(Context context){
        return new Intent(context, AddingNewTask.class);


    }
}