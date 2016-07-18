package corp.richard.androidchat.chat.events;

import corp.richard.androidchat.chat.entities.ChatMessage;

/**
 * Created by carlos.sanchez on 11/06/2016.
 */
public class ChatEvent {
    ChatMessage msg;

    public ChatEvent(ChatMessage msg) {
        this.msg = msg;
    }

    public ChatMessage getMessage() {
        return msg;
    }
}
