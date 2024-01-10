package com.example.todolist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class AddTaskViewModel extends AndroidViewModel {

    private TaskDataBase taskDataBase;

    public MutableLiveData<Boolean> getCloseActivity() {
        return closeActivity;
    }

    MutableLiveData<Boolean> closeActivity = new MutableLiveData<>();
    public AddTaskViewModel(@NonNull Application application) {
        super(application);
        taskDataBase = TaskDataBase.getInstance(getApplication());
    }

    public void saveTask(Task task){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                taskDataBase.tasksDao().addTask(task);
                closeActivity.postValue(true);
            }
        });
        thread.start();
    }


}
