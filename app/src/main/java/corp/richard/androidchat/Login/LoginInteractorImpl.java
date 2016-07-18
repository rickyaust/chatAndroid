package corp.richard.androidchat.Login;

/**
 * Created by ricardo.ramirez on 08/06/2016.
 */
public class LoginInteractorImpl implements LoginInteractor {
    private LoginRepository loginReposity;

    public LoginInteractorImpl(){
      loginReposity = new LoginRepositoryImpl();
    }

    @Override
    public void checkSession() {
        loginReposity.checkSession();
    }

    @Override
    public void doSingUp(String email, String Password) {
        loginReposity.singUp(email,Password);
    }

    @Override
    public void doSingIn(String email, String Password) {
        loginReposity.singIn(email,Password);
    }
}
