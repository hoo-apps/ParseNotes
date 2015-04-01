package edu.hooapps.android.parsenotes.model;

import com.parse.ParseObject;
import com.parse.ParseClassName;

@ParseClassName("Note")
public class Note extends ParseObject {

    private static final String FIELD_TITLE = "title";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_IS_COMPLETE = "isComplete";

    public String getTitle() {
        return getString(FIELD_TITLE);
    }

    public void setTitle(String title) {
        put(FIELD_TITLE, title);
    }

    public String getDescription() {
        return getString(FIELD_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(FIELD_DESCRIPTION, description);
    }

    public boolean isComplete() {
        return getBoolean(FIELD_IS_COMPLETE);
    }

    public void setIsComplete(boolean isComplete) {
        put(FIELD_IS_COMPLETE, isComplete);
    }

}
