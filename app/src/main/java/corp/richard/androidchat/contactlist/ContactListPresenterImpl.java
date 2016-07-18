package corp.richard.androidchat.contactlist;

import org.greenrobot.eventbus.Subscribe;

import corp.richard.androidchat.contactlist.events.ContactListEvent;
import corp.richard.androidchat.contactlist.ui.Adapters.ContactListAdapter;
import corp.richard.androidchat.contactlist.ui.ContactListView;
import corp.richard.androidchat.entities.User;
import corp.richard.androidchat.lib.EventBus;
import corp.richard.androidchat.lib.GreenRobotEventBus;

/**
 * Created by carlos.sanchez on 10/06/2016.
 */
public  class ContactListPresenterImpl implements ContactListPresenter {
    private EventBus  eventBus;
    private ContactListView view;
    private  CantactListInteractor listInteractor;
    private  ContactListSessionInteractor sessionInteractor;

    public ContactListPresenterImpl(ContactListView view) {
        this.view = view;
        eventBus = GreenRobotEventBus.getInstance();
        this.listInteractor = new ContactListInteractorImp();
        this.sessionInteractor = new  ContactListSessionInteractorImpl();
    }

    @Override
    public void onPause() {
sessionInteractor.changeConnectionStatus(User.OFFLINE);
        listInteractor.unsubscribe();
    }

    @Override
    public void onResum() {
        sessionInteractor.changeConnectionStatus(User.ONLINE);
        listInteractor.subscribe();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        listInteractor.destroyListener();
        view = null;

    }

    @Override
    public void signOff() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        listInteractor.unsubscribe();
        listInteractor.destroyListener();;
        sessionInteractor.signOff();
    }

    @Override
    public String getCurrentUseremail() {
        return sessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        listInteractor.removeContact(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user =event.getUser();
        switch (event.getEventType())
        {
            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;
            case ContactListEvent.onContactChange:
                onContactChange(user);
                break;
            case ContactListEvent.onContactRemove:
                onContactRemove(user);
                break;
        }
    }
    private void onContactAdded(User user)
    {
        if(view != null)
        {
            view.onContactAdded(user);
        }
    }
    private void onContactChange(User user)
    {
        if(view != null)
        {
            view.onContactChanged(user);
        }
    }
    private void onContactRemove(User user)
    {
        if(view != null)
        {
            view.onContactRemoved(user);
        }
    }
}
