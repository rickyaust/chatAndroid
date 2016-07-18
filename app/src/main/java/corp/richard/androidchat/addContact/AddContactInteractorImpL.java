package corp.richard.androidchat.addContact;

/**
 * Created by carlos.sanchez on 11/06/2016.
 */
public class AddContactInteractorImpL implements AddContactInteractor {
    AddContactRepository repository;

    public AddContactInteractorImpL() {
        repository = new AddcontactRepositoryImpl();
    }

    @Override
    public void execute(String email) {
        repository.addCotact(email);
    }
}
