package corp.richard.androidchat.contactlist;

import corp.richard.androidchat.contactlist.events.ContactListEvent;

/**
 * Created by carlos.sanchez on 10/06/2016.
 */
public  interface ContactListPresenter {
    void onPause();
    void onResum();
    void onCreate();
    void onDestroy();
    void signOff();
    String  getCurrentUseremail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);
}
