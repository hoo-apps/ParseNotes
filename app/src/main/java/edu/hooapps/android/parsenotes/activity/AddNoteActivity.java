package edu.hooapps.android.parsenotes.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.hooapps.android.parsenotes.R;
import edu.hooapps.android.parsenotes.model.ParseNote;

public class AddNoteActivity extends ActionBarActivity {

    private EditText titleView, descriptionView;
    private Button addNoteButton;

    public static void startAddNoteActivity(Context context) {
        Intent intent = new Intent(context, AddNoteActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        // Retrieve the views from the layout
        titleView = (EditText) findViewById(R.id.title);
        descriptionView = (EditText) findViewById(R.id.description);
        addNoteButton = (Button) findViewById(R.id.button_add_note);

        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the new note to Parse
                ParseNote note = new ParseNote();
                note.setTitle(titleView.getText().toString());
                note.setDescription(descriptionView.getText().toString());
                note.saveInBackground();

                // Return to the previous activity
                finish();
            }
        });
    }
}
