package chimehack.beheard;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by CheeLoong on 7/8/2015.
 */

// This class is to store the state of Maps even
// after the map is closed down
public class MapStateManager {
    private static final String LONGITUDE = "longitude";
    private static final String LATITUDE = "latitude";
    private static final String ZOOM = "zoom";
    private static final String BEARING = "bearing";
    private static final String TILT = "tilt";
    private static final String MAPTYPE = "MAPTYPE";
    private static final String PREFS_NAME = "mapCameraState";
    // A class to store data after App is turned stopped.
    private SharedPreferences mapStatePrefs;

    public MapStateManager(Context context) {
        mapStatePrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // This function takes in a GoogleMap object
    // and saves all states from the values from this map.
    // Note: This does not save and restore mapState such as Terrain, Normal
    // Note: To remove SharedPreferences data from your app:
    // Drag your application to AppInfo
    // Then, hit the popped up Clear Data Button.
    public void saveMapState(GoogleMap map) {
        SharedPreferences.Editor editor = mapStatePrefs.edit();
        // get current camera position from google maps object passed in
        CameraPosition position = map.getCameraPosition();
        // Save all the data
        editor.putFloat(LATITUDE, (float) position.target.latitude);
        editor.putFloat(LONGITUDE, (float) position.target.longitude);
        editor.putFloat(ZOOM, position.zoom);
        editor.putFloat(TILT, position.tilt);
        editor.putFloat(BEARING, position.bearing);
        editor.putInt(MAPTYPE, map.getMapType());
        // Finally, save all the changes
        editor.commit();
    }

    // Method to restore the state saved
    // by returning a CameraPosition object or null if nothing is saved yet.
    public CameraPosition getSavedCameraPosition() {
        // Get back latitude or 0 if it doesn't exist
        double latitude = mapStatePrefs.getFloat(LATITUDE, 0);
        // Note: This won't be right in the very improbable case
        // where the user has the latitude of 0
        if (latitude == 0) {
            // return null since nothing is saved yet
            return null;
        }
        double longitude = mapStatePrefs.getFloat(LONGITUDE, 0);

        // Create a LatLng object
        LatLng target = new LatLng(latitude, longitude);
        // Get all other saved information
        float zoom = mapStatePrefs.getFloat(ZOOM, 0);
        float bearing = mapStatePrefs.getFloat(BEARING, 0);
        float tilt = mapStatePrefs.getFloat(TILT, 0);
        CameraPosition position = new CameraPosition(target, zoom, tilt, bearing);
        return position;
    }
}
