package corp.richard.androidchat.addContact;

import org.greenrobot.eventbus.Subscribe;

import corp.richard.androidchat.addContact.AddContactPresenter;
import corp.richard.androidchat.addContact.events.AddContactEvent;
import corp.richard.androidchat.addContact.ui.AddContactView;
import corp.richard.androidchat.lib.EventBus;
import corp.richard.androidchat.lib.GreenRobotEventBus;

/**
 * Created by carlos.sanchez on 11/06/2016.
 */
public class AddcontactPresenterImp implements AddContactPresenter {
    private EventBus eventBus;
    private AddContactView view;
    private AddContactInteractor interactor;

    public AddcontactPresenterImp(AddContactView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new AddContactInteractorImpL();
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if(view != null)
        {
            view.hideInput();
            view.showProgress();
        }
        interactor.execute(email);

    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if(this.view!= null )
        {
            view.hideProgress();
            view.showIntput();
            if(event.isError())
            {
                view.contactNotAdded();
            }
            else
            {
                view.contactAdded();
            }
        }
    }
}
