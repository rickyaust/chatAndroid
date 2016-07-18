package corp.richard.androidchat.contactlist.ui.Adapters;

import corp.richard.androidchat.entities.User;

/**
 * Created by carlos.sanchez on 10/06/2016.
 */
public interface onItemClickListener {
    void onClick(User user);
    void inItemLogClick(User user);
}
