package corp.richard.androidchat.addContact;

import corp.richard.androidchat.addContact.events.AddContactEvent;

/**
 * Created by carlos.sanchez on 11/06/2016.
 */
public interface AddContactPresenter {
    void onShow();
    void onDestroy();
    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
