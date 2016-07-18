package corp.richard.androidchat.lib;

/**
 * Created by ricardo.ramirez on 08/06/2016.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
