package corp.richard.androidchat.chat.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import corp.richard.androidchat.R;
import corp.richard.androidchat.chat.ui.adapters.ChatAdapter;
import corp.richard.androidchat.chat.ChatPresenter;
import corp.richard.androidchat.chat.ChatPresenterImpl;
import corp.richard.androidchat.chat.entities.ChatMessage;
import corp.richard.androidchat.domain.AvatarHelper;
import corp.richard.androidchat.lib.GlidImageLoader;
import corp.richard.androidchat.lib.ImageLoader;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatActivity extends AppCompatActivity implements ChatView {

    @Bind(R.id.imgAvatar)
    CircleImageView imgAvatar;
    @Bind(R.id.txtUser)
    TextView txtUser;
    @Bind(R.id.txtStatus)
    TextView txtStatus;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.MessageRecyclerViewContacts)
    RecyclerView MessageRecyclerViewContacts;
    @Bind(R.id.editTextMessage)
    EditText editTextMessage;
    @Bind(R.id.btnSendMessage)
    ImageButton btnSendMessage;
    private ChatAdapter adapter;
    private ChatPresenter presenter;
    public final static String EMAIL_KEY="email";
    public final static String ONLINE_KEY="online";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        setupAdapter();
        setupRecyclerView();

        presenter = new ChatPresenterImpl(this);
        presenter.onCreate();
        setupToolbar(getIntent());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupRecyclerView() {
        MessageRecyclerViewContacts.setLayoutManager( new LinearLayoutManager(this));
        MessageRecyclerViewContacts.setAdapter(adapter);

    }
    private void setupAdapter() {
        adapter = new ChatAdapter(this, new ArrayList<ChatMessage>());
    }
    private void setupToolbar(Intent i) {
        String receipent =i.getStringExtra(EMAIL_KEY);
        presenter.setChatRecipient(receipent);
        boolean online = i.getBooleanExtra(ONLINE_KEY,false);



        String status = online ? "online":"offline";
        int color= online ? Color.GREEN : Color.RED;
        txtUser.setText(receipent);
        txtStatus.setText(status);
       txtStatus.setTextColor(color);
        ImageLoader imageLoader =  new GlidImageLoader(getApplicationContext());
        imageLoader.load(imgAvatar, AvatarHelper.getAvatarUrl(receipent));
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        presenter.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onMessageRecived(ChatMessage msg) {
adapter.add(msg);
        MessageRecyclerViewContacts.scrollToPosition(adapter.getItemCount()-1);
    }

    @OnClick(R.id.btnSendMessage)
    public void sendMessage() {
        presenter.sendMessage(editTextMessage.getText().toString());
        editTextMessage.setText("");
    }
}
