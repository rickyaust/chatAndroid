package corp.richard.androidchat.chat;

/**
 * Created by carlos.sanchez on 11/06/2016.
 */
public interface ChatRepository {
    void sentMessage(String msg);
    void setRecipient(String recipient);
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void changeConnectionStatus(boolean online);

}
