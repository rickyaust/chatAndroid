package corp.richard.androidchat.Login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import corp.richard.androidchat.R;
import corp.richard.androidchat.contactlist.ui.ContactListActivity;
import corp.richard.androidchat.singuppak.ui.SingUpActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {
    @Bind(R.id.editTxtEmail)
    EditText inputEmail;
    @Bind(R.id.editTxtPassword)
    EditText inputPassword;
    @Bind(R.id.wrapperPassword)
    TextInputLayout loginError;
    @Bind(R.id.btnSingin)
    Button btnSingin;
    @Bind(R.id.btnSingup)
    Button btnSingup;
    @Bind(R.id.layoutButtons)
    LinearLayout layoutButtons;
    @Bind(R.id.ProgressBar)
    android.widget.ProgressBar ProgressBar;
    @Bind(R.id.layoutMainContainer)
    RelativeLayout layoutMainContainer;
    private LoginPresenter loginPresenter;
    /*@Bind (R.id.sign_in_button)
    Button btnSignButton;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "GoogleActivity";*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenterImpl(this);

      /*  GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();*/


// ...
       /* mAuth = FirebaseAuth.getInstance();
        //findViewById(R.id.sign_in_button).setOnClickListener((View.OnClickListener) this);
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };*/
        ButterKnife.bind(this);
    }

  /*  private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "signInWithCredential", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }

    @OnClick(R.id.sign_in_button)
    public void iniciaGoogle(){
        signIn();
    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

*/
    @Override
    protected void onDestroy() {
        loginPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loginPresenter.onResum();
        loginPresenter.checkForAuthenticatedUser();
    }

    @Override
    protected void onPause() {
        loginPresenter.onPause();
        super.onPause();
    }

    @OnClick(R.id.btnSingin)
    public void handleSingin() {
        Log.e("AndroidChat", inputEmail.getText().toString());
    }
    @OnClick(R.id.btnSingup)
    public void handleSingup() {
        Log.e("AndroidChat", inputEmail.getText().toString());
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

        startActivity(new Intent(this, SingUpActivity.class));
    }

    @OnClick(R.id.btnSingin)
    @Override
    public void handleSingIn() {
        loginPresenter.validateLogin(inputEmail.getText().toString(),inputPassword.getText().toString());
    }

    @Override
    public void navigateToMainScreen() {
        Intent intent =new Intent(this, ContactListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void loginError(String error){
        inputPassword.setText("");
        String nsgError = String.format(getString(R.string.login_error_message_singin),error);
        inputPassword.setError(nsgError);
    }


    @Override
    public void newUserSuccess() {
       // Snackbar.make(layoutMainContainer,R.string.login_notice_message_singup,Snackbar.LENGTH_SHORT).show();
        throw new UnsupportedOperationException("Esta operacion no es valida en login: newUserSuccess");
    }

    @Override
    public void newUserError(String error) {
        /*inputPassword.setText("");
        String nsgError = String.format(getString(R.string.login_error_message_singup),error);
        inputPassword.setError(nsgError);*/
        throw new UnsupportedOperationException("Esta operacion no es valida en login: newUserError");
    }

    private void setInputs(boolean enabled){
        inputEmail.setEnabled(enabled);
        inputPassword.setEnabled(enabled);
        btnSingin.setEnabled(enabled);
        btnSingup.setEnabled(enabled);
    }
}
