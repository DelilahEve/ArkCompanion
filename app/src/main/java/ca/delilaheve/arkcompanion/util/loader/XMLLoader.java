package ca.delilaheve.arkcompanion.util.loader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;

public abstract class XMLLoader {

    public void load(InputStream input){
        try {
            // Create Pull Parser to read file
            XmlPullParser xpp;
            XmlPullParserFactory xppFactory = XmlPullParserFactory.newInstance();
            xppFactory.setNamespaceAware(true);
            xpp = xppFactory.newPullParser();
            xpp.setInput(input, null);

            // Reading XML file
            String currentTag = "";
            int event = xpp.getEventType();
            // While the document has not ended, read it
            while(event != XmlPullParser.END_DOCUMENT){
                event = xpp.next();

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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract void startTagRead(String tag);

    public abstract void textRead(String tag, String text);

    public abstract void endTagRead(String tag);
}
