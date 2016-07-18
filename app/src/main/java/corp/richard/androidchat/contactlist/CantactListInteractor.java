package corp.richard.androidchat.contactlist;

/**
 * Created by carlos.sanchez on 10/06/2016.
 */
public interface CantactListInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContact(String email);
}
