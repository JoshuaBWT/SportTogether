package fr.esilv.sporttogetherbackend.utils;

import com.facebook.Profile;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Joshua BWT on 19/10/2015.
 */
public class SportTogetherObject {

    public void updateData(final String key, final Object value, final String table, final String reference, final String referenceValue) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(table);
        query.whereEqualTo(reference, referenceValue);
        List<ParseObject> list = query.find();
        if (list.size() > 0) {
            ParseObject obj = list.get(0);
            obj.put(key, value);
            obj.saveInBackground();
        } else {
            ParseObject obj = new ParseObject(table);
            obj.put(key, value);
            obj.saveInBackground();
        }
    }

    public void updateData(final HashMap<String, Object> data, final String table, final String reference, final String referenceValue) throws ParseException {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(table);
        query.whereEqualTo(reference, referenceValue);
        List<ParseObject> list = query.find();
        if (list.size() > 0) {
            ParseObject obj = list.get(0);
            for (Map.Entry<String, Object> o : data.entrySet()) {
                obj.put((String) o.getKey(), o.getValue());
            }
            obj.save();
        } else {
            ParseObject obj = new ParseObject("userData");
            for (Map.Entry<String, Object> o : data.entrySet()) {
                obj.put((String) o.getKey(), o.getValue());
            }
            obj.save();
        }
    }
}
