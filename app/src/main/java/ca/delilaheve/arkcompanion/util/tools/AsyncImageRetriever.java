package ca.delilaheve.arkcompanion.util.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;

import ca.delilaheve.arkcompanion.util.asynctask.AsyncTask;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;

public class AsyncImageRetriever extends AsyncTask {

    private URL url;

    private Bitmap image;

    public AsyncImageRetriever(URL url, AsyncTaskImplementer implementer, String taskId) {
        super(taskId, implementer);
        this.url = url;
    }

    @Override
    public void run() {
        try {
            image = BitmapFactory.decodeStream(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        taskCompleted();
    }

    public Bitmap getImage() {
        return image;
    }
}
