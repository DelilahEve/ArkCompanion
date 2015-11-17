package ca.delilaheve.arkcompanion.util.tools;

import java.io.InputStream;

import ca.delilaheve.arkcompanion.util.asynctask.AsyncTask;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;

public class AsyncFileDownloader extends AsyncTask {

    private InputStream inputStream;

    private AsyncTaskImplementer implementer;

    public AsyncFileDownloader(InputStream inputStream, AsyncTaskImplementer implementer, String taskId) {
        super(taskId, implementer);
        this.inputStream = inputStream;
        this.implementer = implementer;
    }

    @Override
    public void run() {
        taskCompleted();
    }
}
