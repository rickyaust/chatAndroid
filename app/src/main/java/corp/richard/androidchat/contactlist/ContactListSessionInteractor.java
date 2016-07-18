package corp.richard.androidchat.contactlist;

/**
 * Created by carlos.sanchez on 10/06/2016.
 */
public interface ContactListSessionInteractor {
void  signOff();
   String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
}
