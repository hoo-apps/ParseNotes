package edu.hooapps.android.parsenotes.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.List;

import edu.hooapps.android.parsenotes.R;
import edu.hooapps.android.parsenotes.model.ParseNote;

public class NoteListFragment extends ListFragment {

    private Context context;

    public NoteListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = this.getActivity();

        // Configure ListView
        getListView().setDivider(null);
        getListView().setDrawSelectorOnTop(true);

        // Load data into the adapter
        ParseQuery<ParseNote> query = ParseQuery.getQuery(ParseNote.class);
        query.setLimit(25);
        query.findInBackground(new FindCallback<ParseNote>() {
            @Override
            public void done(List<ParseNote> parseNotes, ParseException e) {
                if (parseNotes != null) {
                    setListAdapter(new ParseNoteAdapter(context, parseNotes));
                }
            }
        });
    }

    private static class ParseNoteAdapter extends BaseAdapter {
        Context context;
        List<ParseNote> noteList;

        private ParseNoteAdapter(Context context, List<ParseNote> data) {
            this.context = context;
            this.noteList = data;
        }

        @Override
        public int getCount() {
            return noteList.size();
        }

        @Override
        public ParseNote getItem(int position) {
            return noteList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.view_list_item_note, parent, false);
                convertView.setTag(new ViewHolder(convertView));
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            ParseNote note = getItem(position);
            holder.title.setText(note.getTitle());
            holder.description.setText(note.getDescription());
            if (note.isComplete()) {
                holder.container.setBackgroundColor(Color.parseColor("#A5D6A7"));
            } else {
                holder.container.setBackgroundColor(Color.parseColor("#FFFFFF"));
            }
            return convertView;
        }

        private static class ViewHolder {
            TextView title, description;
            LinearLayout container;

            public ViewHolder(View v) {
                title = (TextView) v.findViewById(R.id.title);
                description = (TextView) v.findViewById(R.id.description);
                container = (LinearLayout) v.findViewById(R.id.container);
            }

        }
    }

}
