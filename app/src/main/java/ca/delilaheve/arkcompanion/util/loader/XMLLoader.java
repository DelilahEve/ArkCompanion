package ca.delilaheve.arkcompanion.util.loader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;

import ca.delilaheve.arkcompanion.util.asynctask.AsyncTask;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;

public abstract class XMLLoader extends AsyncTask {

    private InputStream input;

    private XmlPullParser xpp;

    public XMLLoader(URL url, AsyncTaskImplementer implementer, String taskId, InputStream input){
        super(taskId, implementer);
        this.input = input;
    }

    @Override
    public void run() {
        load(input);
    }

    public void load(InputStream input){
        try {
            // Create Pull Parser to read file
            XmlPullParserFactory xppFactory = XmlPullParserFactory.newInstance();
            xppFactory.setNamespaceAware(true);
            xpp = xppFactory.newPullParser();
            xpp.setInput(input, null);

            // Reading XML file
            String currentTag = "";
            int event = xpp.getEventType();
            // While the document has not ended, read it
            while(event != XmlPullParser.END_DOCUMENT){
                try {
                    event = xpp.next();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                    continue;
                }

                // Start tag event
                if(event == XmlPullParser.START_TAG){
                    currentTag = xpp.getName();
                    // Pass tag to startTagRead event
                    startTagRead(currentTag);

                }
                else if(event == XmlPullParser.TEXT){
                    if(xpp.isWhitespace())
                        continue;

                    // Get text and pass to textRead event
                    String text = xpp.getText();
                    text = text.trim();
                    text = text.replace("\\n", " ");

                    textRead(currentTag, text);
                }
                else if(event == XmlPullParser.END_TAG) {
                    // Fire end tag read event
                    endTagRead(xpp.getName());
                }
            }

            input.close();
            taskCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void startTagRead(String tag);

    public abstract void textRead(String tag, String text);

    public abstract void endTagRead(String tag);

    public String checkAttribute(String attribute){
        if(attribute == null || attribute == "")
            return null;

        String value;

        value = xpp.getAttributeValue(null, attribute);

        return value;
    }
}
