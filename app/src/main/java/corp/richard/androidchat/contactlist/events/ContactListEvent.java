package corp.richard.androidchat.contactlist.events;

import corp.richard.androidchat.entities.User;

/**
 * Created by carlos.sanchez on 10/06/2016.
 */
public class ContactListEvent {
    public  final static  int onContactAdded=0;
    public final static  int onContactChange=1;
    public final static int onContactRemove=2;

    private User user;
    private int eventType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}
