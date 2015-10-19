package fr.esilv.sporttogetherbackend.utils;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import fr.esilv.sporttogetherbackend.SportTogetherApplication;

/**
 * Created by Joshua BWT on 01/10/2015.
 */
public class ParseConnector {

    public ParseConnector()
    {

    }

    public void connect(Application app)
    {
        FacebookSdk.sdkInitialize(app);
        Parse.enableLocalDatastore(app);

        // Add your initialization code here
        //Parse.initialize(this, "4jKJRsnEUbSRcE8CbGSbGRyx9TR3fZIQqWEotssU", "8CVSlybsgjnvsVWzoyYHFu3wYEz6q0Elib1kKVlE");
        Parse.initialize(app, "HOVdbsaWteX1rOmnBs6pkylsyr7rhic2paeH9YZf", "TFGptAndVH9fANkkDiD7zoRWFD5FXdPdGhj5GMAK");

        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
        ParseFacebookUtils.initialize(app);

        SportTogetherApplication.currentUser = new SportTogetherUser();
    }



    /*
    public void CleanAll(String[] classnames)
    {

        for(int i = 0; i < classnames.length; i++)
        {
            ParseQuery<ParseObject> query = ParseQuery.getQuery(classnames[i]);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> list, ParseException e) {
                    list.clear();
                }
            });
        }
    }

    public void ResetAll()
    {
        String[] classnames = SportTogetherObject.CLASS_NAMES;

    }
    */

}
