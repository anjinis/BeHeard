package chimehack.beheard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.SaveCallback;


public class CreatePostActivity extends AppCompatActivity {
    Button mShareButton;
    Button mCancelButton;
    EditText mMessage;
    SeekBar mSeverity;
    EditText mLocation;
    int mSeverityVal = 1;

    Post mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        mPost = new Post();

        mShareButton = (Button) findViewById(R.id.share_button);
        mCancelButton = (Button) findViewById(R.id.cancel_button);
        mMessage = (EditText) findViewById(R.id.user_entered_message);
        mLocation = (EditText) findViewById(R.id.location_entry);
        mSeverity = (SeekBar) findViewById(R.id.seek1);

        mSeverity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mSeverityVal = progress/20 + 1;
                if(mSeverityVal == 6) mSeverityVal = 5;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mSeverityVal = ((seekBar.getProgress()/20) + 1);
                if(mSeverityVal == 6) mSeverityVal = 5;
            }
        });


        mShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: translate location text to LONG/LAT and pass as PARSE GEOPOINT
                createPost(new ParseGeoPoint(37.7763,-122.417), mMessage.getText().toString(), mSeverityVal);
                Intent i = new Intent(CreatePostActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CreatePostActivity.this, MainActivity.class);
                startActivity(i);
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
