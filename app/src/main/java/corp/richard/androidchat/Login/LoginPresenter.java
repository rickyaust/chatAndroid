package corp.richard.androidchat.Login;

import corp.richard.androidchat.Login.events.LoginEvent;

/**
 * Created by ricardo.ramirez on 08/06/2016.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email,String password);
    void onPause();
    void  onResum();
    void onEventMainThread(LoginEvent event);
}
