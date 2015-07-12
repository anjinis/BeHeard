package chimehack.beheard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by kevin on 7/11/15.
 */
public class CustomAdapter extends ArrayAdapter<String[]> {

    public CustomAdapter(Context context, int resource, List<String[]> collection) {
        super(context, resource, collection);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row_layout, null);
        }

        // TODO: increment feedback with buttons
        String[] p = getItem(position);

        if (p != null) {
            TextView message = (TextView) v.findViewById(R.id.message);
            Button feedback1 = (Button) v.findViewById(R.id.feedback_1);
            Button feedback2 = (Button) v.findViewById(R.id.feedback_2);
            Button feedback3 = (Button) v.findViewById(R.id.feedback_3);
            TextView severity = (TextView) v.findViewById(R.id.severity);

            if (message != null) {
                message.setText(p[0]);
            }

            if (feedback1 != null) {
                feedback1.setText("" + p[1] + " Send Love");
                feedback1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            if (feedback2 != null) {
                feedback2.setText("" + p[2] + " Not Cool");
                feedback2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
            if (feedback3 != null) {
                feedback3.setText("" + p[3] + " Me Too");
            }
            if (severity != null) {
                severity.setText("Severity: " + p[4]);
            }
        }
        return v;
    }

    private void incrementFeedback(String id, final String feedback) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.getInBackground(id, new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.increment(feedback);
                    object.saveInBackground();
                } else {
                    // something went wrong
                }
            }
        });
    }

}