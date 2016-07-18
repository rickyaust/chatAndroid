package corp.richard.androidchat.addContact.ui;

/**
 * Created by carlos.sanchez on 11/06/2016.
 */
public interface AddContactView {
    void showIntput();
    void hideInput();
    void showProgress();
    void hideProgress();
    void contactAdded();
    void contactNotAdded();
}
