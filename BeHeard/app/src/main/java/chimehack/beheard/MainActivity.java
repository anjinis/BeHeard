package chimehack.beheard;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.Parse;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

public class MainActivity extends AppCompatActivity {
    Button mMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Parse
         */
        /*
            // a new post object
            // this would be in the view form
            array = []; // not sure how to do in java
            ParseObject post = new ParseObject("Post");
            post.put("message",<textfield value>);
            post.put("severity",<radiobutton value>);
            post.saveInBackground();
            // add post to array
            array.push(post); // not sure how to do this in java
            
            // these are your posts
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
            query.getInBackground(array[post].get("id"), new GetCallback<ParseObject>() {
              public void done(ParseObject object, ParseException e) {
                if (e == null) {
                  // get object
                } else {
                  // something went wrong
                }
              }
            });

            // these are the posts from everyone
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Post");
            query.findInBackground(new FindCallback<ParseObject>() {
                public void done(List<ParseObject> posts, ParseException e) {
                    if (e == null) {
                        // the objects
                    } else {
                        // something went wrong
                    }
                }
            });
        */
        // intial parse setup
        setupParse();

        /*
         * View Implementation
         */

        // Action Bar
        ActionBar actionBar = getActionBar();

        // Maps Button
        mMapButton = (Button) findViewById(R.id.map_button);
        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     *  Setup Parse Essentials
     */
    private void setupParse() {
        /*
         * Parse
         */
        ParseCredentials pc = new ParseCredentials();
        //Parse.enableLocalDatastore(this);
        Parse.initialize(this, pc.getAPI_KEY(), pc.getCLIENT_KEY());
    }

    /**
     * Make Post into DB
     */
    private void makePost(Post newPost) {

    }

    /**
     * Get single Post from DB
     */
    private Post query(){
        return new Post(); // return query results here
    }
}
