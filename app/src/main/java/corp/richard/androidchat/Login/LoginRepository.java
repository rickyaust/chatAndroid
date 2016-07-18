package corp.richard.androidchat.Login;

/**
 * Created by ricardo.ramirez on 08/06/2016.
 */
public interface LoginRepository {
    void singUp(String email,String password);
    void singIn(String email,String password);
    void checkSession();
}
