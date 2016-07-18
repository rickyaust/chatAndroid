package corp.richard.androidchat.Login.events;

/**
 * Created by ricardo.ramirez on 08/06/2016.
 */
public class LoginEvent {
    public final static int onSingInError = 0;
    public final static int onSingUpError = 1;
    public final static int onSingInSuccess = 2;
    public final static int onSingUpSuccess = 3;
    public final static int onFailedToRecoverySession = 4;

    private int eventType;
    private String errorMessage;

    public int getEventType(){
        return eventType;
    }
    public void setEventType(int eventType){
        this.eventType = eventType;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage){
        this.errorMessage = errorMessage;
    }
}
