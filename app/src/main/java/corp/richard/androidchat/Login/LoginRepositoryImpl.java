package corp.richard.androidchat.Login;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

import corp.richard.androidchat.Login.events.LoginEvent;
import corp.richard.androidchat.domain.FirebaseHelper;
import corp.richard.androidchat.entities.User;
import corp.richard.androidchat.lib.EventBus;
import corp.richard.androidchat.lib.GreenRobotEventBus;

/**
 * Created by ricardo.ramirez on 08/06/2016.
 */
public class LoginRepositoryImpl implements LoginRepository {
    private FirebaseHelper helper;
    private Firebase dataReference;
    private Firebase myUserReference;

    public LoginRepositoryImpl(){
        this.helper = FirebaseHelper.getInstance();
        this.dataReference = helper.getDataReference();
        this.myUserReference = helper.getMyUserReference();
    }
    @Override
    public void singUp(final String email, final String password) {
        dataReference.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                postEvent(LoginEvent.onSingUpSuccess);
                singIn(email,password);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSingUpError,firebaseError.getMessage());
            }
        });

    }

    @Override
    public void singIn(String email, String password) {
        //
        dataReference.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                initSignIn();
                postEvent(LoginEvent.onSingInSuccess);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                postEvent(LoginEvent.onSingInError,firebaseError.getMessage());
            }
        });
    }

    @Override
    public void checkSession() {
        //Log.e("loginRepositoryImpl","check session");
        if(dataReference.getAuth()!=null){
            initSignIn();
        }else {
            postEvent(LoginEvent.onFailedToRecoverySession);
        }
    }
    private void initSignIn(){
        myUserReference = helper.getMyUserReference();
        myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);
                //postEvent(LoginEvent.onSingInSuccess);

                if(currentUser == null){
                    registerNewUser();
                }
                helper.changeUserConnectionStatus(User.ONLINE);
                postEvent(LoginEvent.onSingInSuccess);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) { }
        });
    }
    private void registerNewUser(){
        String email = helper.getAuthUserEmail();
        if(email != null){
            User currentUser = new User();
            currentUser.setEmail(email);
            myUserReference.setValue(currentUser);
        }

    }
    private void postEvent(int type,String errorMessage){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if(errorMessage != null){
            loginEvent.setErrorMessage(errorMessage);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);

    }
    private void postEvent(int type){
        postEvent(type,null);
    }
}
