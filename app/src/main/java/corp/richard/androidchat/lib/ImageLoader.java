package corp.richard.androidchat.lib;

import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by carlos.sanchez on 10/06/2016.
 */
public interface ImageLoader {
    void load(ImageView imgAvatar, String url);
}
