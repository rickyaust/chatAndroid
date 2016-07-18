package corp.richard.androidchat.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * Created by carlos on 10/06/2016.
 */
public class GlidImageLoader implements ImageLoader {
    private RequestManager requestManager;

    public GlidImageLoader(Context context) {
        this.requestManager = Glide.with(context);
    }

    @Override
    public void load(ImageView imgAvatar, String url) {
            requestManager.load(url).into(imgAvatar);
    }
}
