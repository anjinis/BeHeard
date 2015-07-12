package chimehack.beheard;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button mMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupParse();

        Post pf = new Post();
        pf.createPost(new ParseGeoPoint(40,45), "i need help. some guy walked into me and it felt awkward...",2);
        pf.createPost(new ParseGeoPoint(30,45), "this is another test",0);
        pf.getAll();

        pf.getCard("wdUJCnCLMy");
        pf.getNearbyPosts(new ParseGeoPoint(40,45));

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
