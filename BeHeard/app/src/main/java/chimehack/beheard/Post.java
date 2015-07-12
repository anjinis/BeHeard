package chimehack.beheard;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

//-----------------------------------------------------------------
// To open menu and right-click menu items on top of screen
//-----------------------------------------------------------------
// Google Maps core classes
// Lattitude and Longitude for google maps
// Classes to be able to update camera for google maps
// To give location to a given text address
// To work with user location

/**
 * Created by kevin on 7/11/15.
 */
public class Post {

    ArrayList mUserPosts = new ArrayList();

    public void createPost(ParseGeoPoint location,String message,int severity) {
        ParseObject post = new ParseObject("Post");
        post.put("message", message);
        //ParseGeoPoint point = new ParseGeoPoint(40,40);
        post.put("location",location);
        post.put("sendLove", 0);
        post.put("notCool",0);
        post.put("meToo", 0);
        post.put("severity", severity);
        post.saveInBackground();
        mUserPosts.add(post.getObjectId());
        public void done(ParseException e) {
        if (e == null) {
            String id = gameScore.getObjectId();
        } else {
            // the save call was not successful.
        }
    }
    }
    public void getUserPosts() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereContainedIn("id", mUserPosts);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> postList, ParseException e) {
                if (e == null) {
                    //Log.d("Posts", postList);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }
    public void getCard(String id) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.setLimit(10);
        query.getInBackground(id, new GetCallback<ParseObject>() {
            public void done(ParseObject post, ParseException e) {
                if (e == null) {
                    Log.d("Posts", "Retrieved " + post + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }
    public void getFeed() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.setLimit(10);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> postList, ParseException e) {
                if (e == null) {
                    Log.d("Posts", "Retrieved " + postList.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

    }

    // feedback can either be sendLove, notCool, or meToo
    public void incrementFeedback(String id,final String feedback) {
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
    public void getAll() {
        ParseQuery query = ParseQuery.getQuery("Post");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> postList, ParseException e) {
                if (e == null) {
                    //Log.d("score", "Retrieved " + scoreList.size() + " scores");
                    for (int i = 0; i < postList.size(); i++) {
                        Log.d("Post #" + i, postList.get(i).getString("message").toString());
                    }
                } else {
                    //Log.d("score", "Error: " + e.getMessage());

                }
            }
        });
    }
    public void getNearbyPosts(ParseGeoPoint location) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.whereNear("location", location);
        query.whereWithinMiles("location", location, 3);
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> postList, ParseException e) {
                if (e == null) {
                    //Log.d("score", "Retrieved " + scoreList.size() + " scores");
                    for (int i = 0; i < postList.size(); i++) {
                        Log.d("GET NEARBY POSTS CALLED", postList.get(i).getString("message").toString());
                    }
                } else {
                    //Log.d("score", "Error: " + e.getMessage());

                }
            }
        });
    }
}
