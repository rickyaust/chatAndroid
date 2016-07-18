package corp.richard.androidchat.chat;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import corp.richard.androidchat.chat.entities.ChatMessage;
import corp.richard.androidchat.chat.events.ChatEvent;
import corp.richard.androidchat.domain.FirebaseHelper;
import corp.richard.androidchat.lib.EventBus;
import corp.richard.androidchat.lib.GreenRobotEventBus;

/**
 * Created by carlos on 11/06/2016.
 */
public class ChatRepositoryImpl implements ChatRepository {
    private String recipient;
    private FirebaseHelper helper;
    private EventBus    eventBus;
    private ChildEventListener chatEventListener;

    public ChatRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void sentMessage(String msg) {
        //String keySender = helper.getAuthUserEmail().replace(".","_");
        //ChatMessage chatMessage = new ChatMessage(keySender, msg);
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(helper.getAuthUserEmail());
        chatMessage.setMsg(msg);
        Firebase chatsReference = helper.getChatsReference(recipient);
        chatsReference.push().setValue(chatMessage);
    }

    @Override
    public void setRecipient(String recipient) {
        this.recipient =recipient;
    }

    @Override
    public void subscribe() {
        if (chatEventListener == null) {
            chatEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
                    ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                    String msgSender = chatMessage.getSender();
                    //msgSender = msgSender.replace("_",".");
                    chatMessage.setSentByMe(msgSender.equals(helper.getAuthUserEmail()));

                    //String currentUserEmail = helper.getAuthUserEmail();
                    //chatMessage.setSentByMe(msgSender.equals(currentUserEmail));

                    ChatEvent chatEvent = new ChatEvent(chatMessage);
                    EventBus eventBus = GreenRobotEventBus.getInstance();
                    eventBus.post(chatEvent);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildKey) {}

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {}

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(FirebaseError firebaseError) {}
            };
            helper.getChatsReference(recipient).addChildEventListener(chatEventListener);
        }
    }

    @Override
    public void unsubscribe() {
        if (chatEventListener != null) {
            helper.getChatsReference(recipient).removeEventListener(chatEventListener);
        }
    }

    @Override
    public void destroyListener() {
        chatEventListener = null;
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }
}
