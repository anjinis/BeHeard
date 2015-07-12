package chimehack.beheard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;

public class MainActivity extends AppCompatActivity {
    Button mMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupParse();

        Post pf = new Post();
//        pf.createPost(new ParseGeoPoint(38,-122), "Point 1", 0);
//        pf.createPost(new ParseGeoPoint(38.05,-122.05), "Point 2", 0);
//        pf.createPost(new ParseGeoPoint(38.04,-122.04), "Point 3", 0);
//        pf.createPost(new ParseGeoPoint(38.03,-122.03), "Point 4", 0);
//        pf.createPost(new ParseGeoPoint(38.02,-122.02), "Point 4", 0);

        /*
         * View Implementation
         */

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        // Maps Button
        mMapButton = (Button) findViewById(R.id.map_button);
        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });

        // ListView
        ListView feed = (ListView) findViewById(R.id.list);
        ListAdapter customAdapter = new CustomAdapter(this, R.layout.row_layout, pf.getFeedPosts());
        feed.setAdapter(customAdapter);
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up not_button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    /**
     * Setup Parse Essentials
     */
    private void setupParse() {
        ParseCredentials pc = new ParseCredentials();
        Parse.initialize(this, pc.getAPI_KEY(), pc.getCLIENT_KEY());
    }
}
