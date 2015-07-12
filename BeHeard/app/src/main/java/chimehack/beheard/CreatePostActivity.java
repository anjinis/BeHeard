package chimehack.beheard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.SaveCallback;


public class CreatePostActivity extends AppCompatActivity {
    Button mCreateButton;
    Button mCancelButton;
    EditText mMessage;

    Post mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        mPost = new Post();

        mCreateButton = (Button) findViewById(R.id.create_button);
        mCancelButton = (Button) findViewById(R.id.cancel_button);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void createPost(ParseGeoPoint location, String message, int severity) {
        final ParseObject post = new ParseObject("Post");
        post.put("message", message);
        //ParseGeoPoint point = new ParseGeoPoint(40,40);
        post.put("location", location);
        post.put("sendLove", 0);
        post.put("notCool", 0);
        post.put("meToo", 0);
        post.put("severity", severity);
        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    String id = post.getObjectId();
                } else {
                    Log.e("FAILURE", "FAILED TO MAKE POST");
                }
            }
        });
    }
}
