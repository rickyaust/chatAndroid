package corp.richard.androidchat.addContact.events;

/**
 * Created by carlos.sanchez on 11/06/2016.
 */
public class AddContactEvent {
    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
