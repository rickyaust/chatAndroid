package corp.richard.androidchat.contactlist.ui.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import corp.richard.androidchat.R;
import corp.richard.androidchat.domain.AvatarHelper;
import corp.richard.androidchat.entities.User;
import corp.richard.androidchat.lib.ImageLoader;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by carlos.sanchez on 10/06/2016.
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {

    private List<User> contactList;
    private ImageLoader imageLoader;
    private onItemClickListener onItemClickListener;

    public ContactListAdapter(List<User> contactList, ImageLoader imageLoading, corp.richard.androidchat.contactlist.ui.Adapters.onItemClickListener onItemClickListener) {
        this.contactList = contactList;
        this.imageLoader = imageLoading;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
User user=contactList.get(position);
        holder.setClickListener(user, onItemClickListener);
        String email =user.getEmail();
        boolean online = user.isOnline();
        String status = online ? "online":"offline";
    int color= online ? Color.GREEN : Color.RED;
        holder.txtUser.setText(email);
        holder.txtStatus.setText(status);
        holder.txtStatus.setTextColor(color);
        imageLoader.load(holder.imgAvatar, AvatarHelper.getAvatarUrl(email));
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void add(User user) {
        if(!contactList.contains(user))
        {
            contactList.add(user);
            notifyDataSetChanged();
        }
    }

    public void update(User user) {
        if(contactList.contains(user))
        {
            int index= contactList.indexOf(user);
            contactList.set(index,user);
            notifyDataSetChanged();
        }
    }

    public void remove(User user) {
        if(contactList.contains(user))
        {

            contactList.remove(user);
            notifyDataSetChanged();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imgAvatar)
        CircleImageView imgAvatar;
        @Bind(R.id.txtUser)
        TextView txtUser;
        @Bind(R.id.txtStatus)
        TextView txtStatus;

        private  View view;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.view= itemView;
        }
        private void setClickListener(final User user, final onItemClickListener listener)
        {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(user);
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    listener.inItemLogClick(user);
                    return false;
                }
            });
        }
    }
}
