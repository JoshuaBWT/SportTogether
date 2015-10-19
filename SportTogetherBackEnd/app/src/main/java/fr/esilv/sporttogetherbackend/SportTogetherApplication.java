package fr.esilv.sporttogetherbackend;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import fr.esilv.sporttogetherbackend.utils.ParseConnector;
import fr.esilv.sporttogetherbackend.utils.SportTogetherUser;

/**
 * Created by Joshua BWT on 01/10/2015.
 */
public class SportTogetherApplication extends Application {

    public static SportTogetherUser currentUser;

    @Override
    public void onCreate() {
        super.onCreate();

        ParseConnector connector = new ParseConnector();
        connector.connect(this);
    }
}
