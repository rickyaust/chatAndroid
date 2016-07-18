package corp.richard.androidchat.Login;

/**
 * Created by ricardo.ramirez on 08/06/2016.
 */
public interface LoginInteractor {
    void checkSession();
    void doSingUp(String email,String Password);
    void doSingIn(String email,String Password);
}
