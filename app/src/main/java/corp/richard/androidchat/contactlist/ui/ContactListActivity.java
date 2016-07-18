package corp.richard.androidchat.contactlist.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import corp.richard.androidchat.Login.ui.LoginActivity;
import corp.richard.androidchat.R;
import corp.richard.androidchat.addContact.ui.AddContactFragment;
import corp.richard.androidchat.chat.ui.ChatActivity;
import corp.richard.androidchat.contactlist.ContactListPresenter;
import corp.richard.androidchat.contactlist.ContactListPresenterImpl;
import corp.richard.androidchat.contactlist.ui.Adapters.ContactListAdapter;
import corp.richard.androidchat.contactlist.ui.Adapters.onItemClickListener;
import corp.richard.androidchat.entities.User;
import corp.richard.androidchat.lib.GlidImageLoader;
import corp.richard.androidchat.lib.ImageLoader;

public class ContactListActivity extends AppCompatActivity implements ContactListView,onItemClickListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerViewContacts)
    RecyclerView recyclerViewContacts;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private ContactListPresenter presenter;
    private ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        setupAdapter();
        setupRecyclerView();
        presenter = new ContactListPresenterImpl(this);
        presenter.onCreate();
        setupToolbar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contactslist,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== R.id.action_logout)
        {
            presenter.signOff();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                           | Intent.FLAG_ACTIVITY_NEW_TASK
                           | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        recyclerViewContacts.setLayoutManager( new LinearLayoutManager(this));
        recyclerViewContacts.setAdapter(adapter);
    }

    private void setupAdapter() {
        ImageLoader  loader = new GlidImageLoader(this.getApplicationContext());
        //User user = new User();
       // user.setOnline(false);
       // user.setEmail("aacs85@gmail.com");
       // adapter = new ContactListAdapter(Arrays.asList(new User[]{user}),loader,this);
        adapter = new ContactListAdapter(new ArrayList<User>(),loader,this);
    }

    private void setupToolbar() {
        toolbar.setTitle(presenter.getCurrentUseremail());
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {

        super.onResume();
        presenter.onResum();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @OnClick(R.id.fab)
    public  void addContact()
    {
        new AddContactFragment().show(getSupportFragmentManager(),getString(R.string.addcontact_menu_title));
    }
    @Override
    public void onContactAdded(User user) {
        adapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        adapter.update(user);
    }

    @Override
    public void onContactRemoved(User user) {
        adapter.remove(user);
    }

    @Override
    public void onClick(User user) {
       // Toast.makeText(this,user.getEmail(),Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(ChatActivity.EMAIL_KEY,user.getEmail());
        intent.putExtra(ChatActivity.ONLINE_KEY,user.getEmail());
        startActivity(intent);
    }

    @Override
    public void inItemLogClick(User user) {
        presenter.removeContact(user.getEmail());

    }
}
