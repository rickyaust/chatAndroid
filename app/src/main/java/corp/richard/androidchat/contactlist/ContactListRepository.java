package corp.richard.androidchat.contactlist;

/**
 * Created by carlos.sanchez on 10/06/2016.
 */
public interface ContactListRepository {
 void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void destroyListener();
    void subscribeContactListEvents();
    void unsubscribeContactListEvents();
    void changeConnectionStatus(boolean online);
}
