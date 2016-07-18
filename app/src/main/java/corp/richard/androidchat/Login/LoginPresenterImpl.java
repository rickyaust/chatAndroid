package corp.richard.androidchat.Login;

import org.greenrobot.eventbus.Subscribe;

import corp.richard.androidchat.Login.events.LoginEvent;
import corp.richard.androidchat.Login.ui.LoginView;
import corp.richard.androidchat.lib.EventBus;
import corp.richard.androidchat.lib.GreenRobotEventBus;

/**
 * Created by ricardo.ramirez on 08/06/2016.
 */
public class LoginPresenterImpl implements LoginPresenter {
    private EventBus eventBus;

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public  LoginPresenterImpl(LoginView LoginView){
        this.loginView=LoginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        loginView = null;

    }

    @Override
    public void checkForAuthenticatedUser() {
        if(loginView != null){
            loginView.disabledInputs();
            loginView.showProgress();
        }
        loginInteractor.checkSession();
    }

    @Override
    public void validateLogin(String email, String password) {
        if(loginView != null){
            loginView.disabledInputs();
            loginView.showProgress();
        }
        loginInteractor.doSingIn(email,password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if(loginView != null){
            loginView.disabledInputs();
            loginView.showProgress();
        }
        loginInteractor.doSingUp(email,password);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onResum() {
        eventBus.register(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()){
            case LoginEvent.onSingInSuccess:
                 onSingInSuccess();
                break;
            case LoginEvent.onSingInError:
                onSingInError(event.getErrorMessage());
                break;
            case LoginEvent.onSingUpError:
                onSingUpError(event.getErrorMessage());
                break;
            case LoginEvent.onSingUpSuccess:
                onSingUpSuccess();
                break;
            case LoginEvent.onFailedToRecoverySession:
                onFailedToRecoverSession();
                break;
        }
    }

    private void onFailedToRecoverSession() {
        if(loginView!=null){
            loginView.hideProgress();
            loginView.enabledInputs();
        }
        //Log.e("LoginPresenterImpl","onFailedToRecoverSession");
    }

    private void onSingInSuccess(){
        if(loginView != null){
            loginView.navigateToMainScreen();
        }
    }
    private void onSingUpSuccess(){
        if(loginView!=null){
            loginView.newUserSuccess();
        }
    }
    private void onSingInError(String error){
        if(loginView!=null){
            loginView.hideProgress();
            loginView.enabledInputs();
            loginView.loginError(error);
        }
    }
    private void onSingUpError(String error){
        loginView.hideProgress();
        loginView.enabledInputs();
        loginView.newUserError(error);
    }
}
