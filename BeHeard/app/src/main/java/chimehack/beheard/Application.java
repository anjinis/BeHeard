package chimehack.beheard;

import com.parse.Parse;

/**
 * Created by kevin on 7/12/15.
 */
public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, new ParseCredentials().getAPI_KEY(), new ParseCredentials().getCLIENT_KEY());
    }
}
