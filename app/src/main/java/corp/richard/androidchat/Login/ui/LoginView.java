package corp.richard.androidchat.Login.ui;

/**
 * Created by ricardo.ramirez on 08/06/2016.
 */
public interface LoginView {
    void enabledInputs();
    void disabledInputs();
    void showProgress();
    void hideProgress();

    void handleSingUp();
    void handleSingIn();

    void navigateToMainScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);

}
