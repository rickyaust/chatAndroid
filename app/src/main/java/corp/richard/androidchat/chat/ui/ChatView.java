package corp.richard.androidchat.chat.ui;

import corp.richard.androidchat.chat.entities.ChatMessage;

/**
 * Created by carlos.sanchez on 11/06/2016.
 */
public interface ChatView {
    void  onMessageRecived(ChatMessage msg);

}
