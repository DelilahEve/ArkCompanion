package ca.delilaheve.arkcompanion.util.asynctask;

import android.app.Activity;

public abstract class AsyncTask implements Runnable {

    private AsyncTaskImplementer implementer;

    private String taskId;

    public AsyncTask(String taskId, AsyncTaskImplementer implementer) {
        this.taskId = taskId;
        this.implementer = implementer;
    }

    public void taskCompleted() {
        Activity activity = implementer.getCurrentActivity();
        activity.runOnUiThread(new AsyncTaskCompleteHandler(taskId, implementer));
    }

}
