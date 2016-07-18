package corp.richard.androidchat.chat;

/**
 * Created by carlos on 11/06/2016.
 */
public class ChatInteractorImpl implements ChatInteractor {
    ChatRepository repository;

    public ChatInteractorImpl() {
        this.repository = new ChatRepositoryImpl();
    }
    @Override
    public void sentMessage(String msg) {
        repository.sentMessage(msg);
    }

    @Override
    public void setRecipient(String recipient) {
        repository.setRecipient(recipient);
    }

    @Override
    public void subscribe() {
        repository.subscribe();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribe();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }
}
