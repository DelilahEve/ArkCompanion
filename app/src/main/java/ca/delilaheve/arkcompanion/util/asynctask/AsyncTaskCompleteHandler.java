package ca.delilaheve.arkcompanion.util.asynctask;


public class AsyncTaskCompleteHandler implements Runnable {

    private AsyncTaskImplementer implementer;

    private String taskId;

    public AsyncTaskCompleteHandler(String taskId, AsyncTaskImplementer implementer) {
        this.taskId = taskId;
        this.implementer = implementer;
    }

    @Override
    public void run() {
        implementer.onTaskComplete(taskId);
    }
}
