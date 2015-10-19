package fr.esilv.sporttogetherbackend.utils;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import fr.esilv.sporttogetherbackend.LoginActivity;
import fr.esilv.sporttogetherbackend.SportTogetherApplication;

/**
 * Created by Joshua BWT on 01/10/2015.
 */
public class SportTogetherUser extends SportTogetherObject {
    private Profile profile;
    private ParseUser user;

    public void SportTogetherUser()
    {

    }

    public String firstName()
    {
        return profile.getFirstName();
    }

    public String lastName()
    {
        return profile.getLastName();
    }

    public Profile getProfile()
    {
        return this.profile;
    }

    public Bitmap profilePicture() throws IOException {
        String imageURL = "http://graph.facebook.com/"+this.profile.getId()+"/picture?type=large";
        InputStream in = (InputStream) new URL(imageURL).getContent();
        return BitmapFactory.decodeStream(in);
    }

    public void AuthenticateWithFacebook(android.app.Activity activity) throws IOException {
        List<String> permissions = new ArrayList<String>();
        permissions.add("public_profile");
        permissions.add("user_status");
        permissions.add("user_friends");
        ParseFacebookUtils
        .logInWithReadPermissionsInBackground(activity, permissions, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if(parseUser.isNew()) {
                    HashMap<String, Object> values = new HashMap<String, Object>();
                    values.put("fbId", Profile.getCurrentProfile().getId());
                    values.put("fName", Profile.getCurrentProfile().getFirstName());
                    values.put("lName", Profile.getCurrentProfile().getLastName());
                    try {
                        updateUserData(values);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }
                else{
                    //Update user qui se reconnecte
                }
            }
        });

    }

    public Boolean isAuthenticated()
    {
        return user.isAuthenticated();
    }


    public void updateUserData(final HashMap<String, Object> data) throws ParseException {
        updateData(data, "userData", "fbId", profile.getId());
    }

    public void updateUserData(final String key, final Object value) throws ParseException {
        updateData(key, value, "userData", "fbId", profile.getId());
    }
}
