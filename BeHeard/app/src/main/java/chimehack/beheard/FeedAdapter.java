package chimehack.beheard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by kevin on 7/11/15.
 */
public class FeedAdapter extends ArrayAdapter<String> {

    public FeedAdapter(Context context, String[] values) {
        super(context, R.layout.row_layout, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());

        View view = inflater.inflate(R.layout.row_layout, parent, false);
        String message = getItem(position);

        TextView messageView = (TextView) view.findViewById(R.id.message);
        messageView.setText(message);

        return view;
    }
}
