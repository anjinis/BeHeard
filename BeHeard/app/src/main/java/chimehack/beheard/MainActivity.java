package chimehack.beheard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button mMapButton;
    Button mCreatePostButton;
    ListView mFeed;
    Button mRefreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        // Create Post Button
        mCreatePostButton = (Button) findViewById(R.id.create_button);
        mCreatePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CreatePostActivity.class);
                startActivity(i);
            }
        });

        // Refresh Button
        mRefreshButton = (Button) findViewById(R.id.refresh_button);
        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // ListView

        // TODO: Save Instance State + Pull Down to Reload

        // TODO: REFRESH VIEW after update
        ParseQuery query = ParseQuery.getQuery("Post");

        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> postList, ParseException e) {
                ArrayList<String[]> localDB = new ArrayList<String[]>();
                if (e == null) {
                    for (int i = 0; i < postList.size(); i++) {
                        Log.i("Post #" + (i + 1), postList.get(i).getString("message"));
                        String[] eachPost = new String[6];
                        eachPost[0] = postList.get(i).getString("message");
                        eachPost[1] = "" + postList.get(i).getInt("sendLove");
                        eachPost[2] = "" + postList.get(i).getInt("notCool");
                        eachPost[3] = "" + postList.get(i).getInt("meToo");
                        eachPost[4] = "" + postList.get(i).getInt("severity");
                        eachPost[5] = postList.get(i).getObjectId();
                        localDB.add(0, eachPost);
                    }
                } else {
                    Log.e("FATAL", e.getMessage());
                }
                mFeed = (ListView) findViewById(R.id.list);
                ListAdapter customAdapter = new CustomAdapter(MainActivity.this, R.layout.row_layout, localDB);
                mFeed.setAdapter(customAdapter);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
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
}
