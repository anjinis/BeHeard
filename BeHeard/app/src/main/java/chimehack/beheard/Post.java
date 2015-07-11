package chimehack.beheard;

import com.parse.ParseGeoPoint;

/**
 * Created by kevin on 7/11/15.
 */
public class Post {
    ParseGeoPoint location;
    String message;
    int severity;
    int feedback1;
    int feedback2;
    int feedback3;

    public Post(){}

    public Post(ParseGeoPoint loc, String mes, int sev,
                int fb1, int fb2, int fb3) {
        this.location = loc;
        this.message = mes;
        this.severity = sev;
        this.feedback1 = fb1;
        this.feedback2 = fb2;
        this.feedback3 = fb3;
    }
}
