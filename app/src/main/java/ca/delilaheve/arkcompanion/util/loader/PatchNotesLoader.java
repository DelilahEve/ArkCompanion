package ca.delilaheve.arkcompanion.util.loader;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;

public class PatchNotesLoader extends XMLLoader {

    HashMap<String, ArrayList<String>> notesMap;

    private ArrayList<String> currentNotes;

    private int patchCount = 0;

    public PatchNotesLoader(URL url, AsyncTaskImplementer implementer, String taskId, InputStream input){
        super(url, implementer, taskId, input);
        notesMap = new HashMap<>();
    }

    @Override
    public void startTagRead(String tag) {}

    @Override
    public void textRead(String tag, String text) {
        if(patchCount > 6)
            return;

        String upcomingTitle = "Upcoming Version: v";
        String currentTitle = "Current Version: v";
        String titlePattern = "v[0-9]+\\.[0-9]";
        Pattern p = Pattern.compile(titlePattern);

        if(text.contains("Current ARK Official Server Network Servers Version"))
            return;

        if(text.startsWith(upcomingTitle) || text.startsWith(currentTitle) || p.matcher(text).matches()) {

            if(currentNotes != null) {
                notesMap.put(text, currentNotes);
                patchCount++;
            }

            currentNotes = new ArrayList<>();

        }

        if(text.startsWith("\"*") || text.startsWith("\"-")) {
            currentNotes.add(text);
        }

    }

    @Override
    public void endTagRead(String tag) {}

    public HashMap<String, ArrayList<String>> getNotesMap() {
        return notesMap;
    }
}
