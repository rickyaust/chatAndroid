package corp.richard.androidchat.contactlist.ui;

import corp.richard.androidchat.entities.User;

/**
 * Created by carlos.sanchez on 10/06/2016.
 */
public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
