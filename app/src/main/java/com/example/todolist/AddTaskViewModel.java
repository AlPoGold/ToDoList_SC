package com.example.todolist;

import static io.reactivex.rxjava3.internal.jdk8.FlowableFlatMapStream.subscribe;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.android.datatransport.runtime.scheduling.Scheduler;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.functions.Action;

public class AddTaskViewModel extends AndroidViewModel {

    private TaskDataBase taskDataBase;
    private TaskDao taskDao;
    private Disposable disposable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<Boolean> getCloseActivity() {
        return closeActivity;
    }

    MutableLiveData<Boolean> closeActivity = new MutableLiveData<>();
    public AddTaskViewModel(@NonNull Application application) {
        super(application);
        taskDataBase = TaskDataBase.getInstance(getApplication());
    }

    public void saveTask(Task task){

        disposable = taskDataBase.tasksDao().addTask(task)
                        .subscribeOn(Schedulers.io())
                        .delay(5, TimeUnit.SECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action() {
            @Override
            public void run() throws Throwable {
                Log.d("adding new task", "subscribe");
                closeActivity.postValue(true);
            }
        });
        compositeDisposable.add(disposable);


//
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                taskDataBase.tasksDao().addTask(task);
//                closeActivity.postValue(true);
//            }
//        });
//        thread.start();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
