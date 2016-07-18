package corp.richard.androidchat.singuppak.ui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import corp.richard.androidchat.Login.LoginPresenter;
import corp.richard.androidchat.Login.LoginPresenterImpl;
import corp.richard.androidchat.Login.ui.LoginView;
import corp.richard.androidchat.R;
import corp.richard.androidchat.contactlist.ui.ContactListActivity;

public class SingUpActivity extends AppCompatActivity  implements LoginView{
    @Bind(R.id.editTxtEmail)
    EditText inputEmail;
    @Bind(R.id.editTxtPassword)
    EditText inputPassword;
    @Bind(R.id.wrapperPassword)
    TextInputLayout loginError;

    @Bind(R.id.btnSingup)
    Button btnSingup;
    @Bind(R.id.layoutButtons)
    LinearLayout layoutButtons;
    @Bind(R.id.ProgressBar)
    android.widget.ProgressBar ProgressBar;
    @Bind(R.id.layoutMainContainer)
    RelativeLayout layoutMainContainer;
    private LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        ButterKnife.bind(this);
        setTitle(R.string.sinup_title_screamCrearcuenta);

        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.onCreate();
    }
    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        loginPresenter.onResum();
    }

    @Override
    protected void onPause() {
        loginPresenter.onPause();
        super.onPause();
    }
    @Override
    public void enabledInputs() {
        setInputs(true);
    }

    @Override
    public void disabledInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        ProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        ProgressBar.setVisibility(View.GONE);
    }
    @OnClick(R.id.btnSingup)
    @Override
    public void handleSingUp() {
        loginPresenter.registerNewUser(inputEmail.getText().toString(),inputPassword.getText().toString());
    }

    @Override
    public void handleSingIn() {
        throw new UnsupportedOperationException("Esta operacion no es valida en crear cuenta");
    }

    @Override
    public void navigateToMainScreen() {

        Intent intent =new Intent(this, ContactListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void loginError(String error) {
        throw new UnsupportedOperationException("Esta operacion no es valida en crear cuenta login error");
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(layoutMainContainer,R.string.sinup_notice_message_singup,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void newUserError(String error) {
        inputPassword.setText("");
        String nsgError = String.format(getString(R.string.sinup_error_message_singup),error);
        inputPassword.setError(nsgError);
    }
    private void setInputs(boolean enabled){
        inputEmail.setEnabled(enabled);
        inputPassword.setEnabled(enabled);
        btnSingup.setEnabled(enabled);
    }
}
