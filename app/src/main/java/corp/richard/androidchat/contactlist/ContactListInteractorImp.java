package corp.richard.androidchat.contactlist;

/**
 * Created by carlos on 10/06/2016.
 */
public class ContactListInteractorImp implements CantactListInteractor {
    ContactListRepository repository;

    public ContactListInteractorImp() {
        repository = new ContactListRepositoryImpl();
    }

    @Override
    public void subscribe() {
        repository.subscribeContactListEvents();
    }

    @Override
    public void unsubscribe() {
repository.unsubscribeContactListEvents();
    }

    @Override
    public void destroyListener() {
        repository.destroyListener();
    }

    @Override
    public void removeContact(String email) {
        repository.removeContact(email);
    }
}
