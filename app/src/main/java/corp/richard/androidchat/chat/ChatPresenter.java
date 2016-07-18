package corp.richard.androidchat.chat;

import corp.richard.androidchat.chat.events.ChatEvent;

/**
 * Created by carlos.sanchez on 11/06/2016.
 */
public interface ChatPresenter {
    void  onPause();
    void onResume();
    void onCreate();
    void onDestroy();
    void setChatRecipient(String recipient);
    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);
}
