package corp.richard.androidchat.contactlist;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import corp.richard.androidchat.contactlist.events.ContactListEvent;
import corp.richard.androidchat.domain.FirebaseHelper;
import corp.richard.androidchat.entities.User;
import corp.richard.androidchat.lib.EventBus;
import corp.richard.androidchat.lib.GreenRobotEventBus;

/**
 * Created by carlos on 10/06/2016.
 */
public class ContactListRepositoryImpl implements ContactListRepository {
    private EventBus eventBus;
    private FirebaseHelper helper;
    private ChildEventListener contactEventListener;

    public ContactListRepositoryImpl() {
        this.helper =FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void signOff() {
      helper.singoff();
    }

    @Override
    public String getCurrentUserEmail() {
        return helper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail =  helper.getAuthUserEmail();
        //helper.getOneContactReference(currentUserEmail,email);
       // helper.getOneContactReference(email,currentUserEmail);
        helper.getOneContactReference(currentUserEmail,email).removeValue();
        helper.getOneContactReference(email,currentUserEmail).removeValue();
    }

    @Override
    public void destroyListener() {
        contactEventListener = null;
    }

    @Override
    public void subscribeContactListEvents() {
            if(contactEventListener == null) {
    contactEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            handleContac(dataSnapshot,ContactListEvent.onContactAdded);
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            handleContac(dataSnapshot,ContactListEvent.onContactChange);

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            handleContac(dataSnapshot,ContactListEvent.onContactRemove);
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {

        }
    };
            }
        helper.getMyUserReference().addChildEventListener(contactEventListener);
    }

    private void handleContac(DataSnapshot dataSnapshot, int type) {
        String email=null;
        boolean online=false;
        for (DataSnapshot child : dataSnapshot.getChildren()) {
           email = child.getKey();
            online =((Boolean)child.getValue()).booleanValue();
           // Firebase reference = getOneContactReference(email, myEmail);
           // reference.setValue(online);
            email=email.replace("_",".");
            //  boolean online =((Boolean)dataSnapshot.getValue()).booleanValue();
            User user= new User();
            user.setEmail(email);
            user.setOnline(online);
            post(type,user);
        }
       // DataSnapshot child = dataSnapshot.getChildren();
        //String email=dataSnapshot.getKey();

        //email=email.replace("_",".");
      //  boolean online =((Boolean)dataSnapshot.getValue()).booleanValue();
        //User user= new User();
       // user.setEmail(email);
      //  user.setOnline(online);
      //  post(type,user);
    }

    private void post(int type, User user) {
        ContactListEvent event = new ContactListEvent();
        event.setEventType(type);
        event.setUser(user);
        eventBus.post(event);
    }

    @Override
    public void unsubscribeContactListEvents() {
        if(contactEventListener != null)
        {
            helper.getMyUserReference().removeEventListener(contactEventListener);
        }
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }
}
