package fr.esilv.sporttogetherbackend.utils;

import com.parse.ParseObject;

import java.util.Date;
import java.util.List;

/**
 * Created by Joshua BWT on 19/10/2015.
 */

public class SportTogetherEvent extends SportTogetherObject {

    public String eventName;
    public Date eventDate;
    public List<SportTogetherUser> participants;
    public Sports sport;

    private ParseObject parseObject;

    public SportTogetherEvent(String eventName, Date eventDate, List<SportTogetherUser> participants, Sports sport)
    {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.participants = participants;
        this.sport = sport;
    }

    public void UpdateEvent()
    {

    }

}
