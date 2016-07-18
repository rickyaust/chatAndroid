package corp.richard.androidchat.addContact;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.PriorityQueue;

import corp.richard.androidchat.addContact.events.AddContactEvent;
import corp.richard.androidchat.domain.FirebaseHelper;
import corp.richard.androidchat.entities.User;
import corp.richard.androidchat.lib.EventBus;
import corp.richard.androidchat.lib.GreenRobotEventBus;

/**
 * Created by carlos.sanchez on 11/06/2016.
 */
public class AddcontactRepositoryImpl implements AddContactRepository {
    private EventBus eventBus;
    private FirebaseHelper helper;

    public AddcontactRepositoryImpl() {
        this.helper= FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void addCotact(final String email) {
        final  String key= email.replace(".","_");
        Firebase userRefrence = helper.getUserReference(email);
        userRefrence.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
       User user = dataSnapshot.getValue(User.class);
        if(user != null)
        {
           Firebase myContactReference = helper.getMyContactsReference();
            myContactReference.child(key).setValue(user.isOnline());
            String currentUserKey = helper.getAuthUserEmail();
            currentUserKey = currentUserKey.replace(".","_");
            Firebase reverseContactReference = helper.getContactsReference(email);
            reverseContactReference.child(currentUserKey).setValue(user.ONLINE);
            postSuccess();
        }
        else
        {
            postError();
        }
    }

    @Override
    public void onCancelled(FirebaseError firebaseError) {
     postError();
    }
});
    }
    private  void postSuccess()
    {
        post(false);
    }
    private  void postError()
    {
        post(true);
    }
    private void post(boolean error) {
        AddContactEvent event = new AddContactEvent();

     //   if(error)
    //    {
            event.setError(error);
            eventBus.post(event);
     //   }
       // else
        //{
           // event.setError(false);
          //  eventBus.post(event);
       // }
    }
}
