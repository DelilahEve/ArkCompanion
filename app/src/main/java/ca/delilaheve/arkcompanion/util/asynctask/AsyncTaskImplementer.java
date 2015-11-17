package ca.delilaheve.arkcompanion.util.asynctask;

import android.app.Activity;

public interface AsyncTaskImplementer {

    void onTaskComplete(String taskId);

    Activity getCurrentActivity();

}
