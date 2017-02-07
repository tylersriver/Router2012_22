package sriver.w.tyler.router2017_22.networks.daemon;

/**
 * Created by tyler on 2/7/2017.
 */
public class LL2PDaemon {
    private static LL2PDaemon ourInstance = new LL2PDaemon();

    public static LL2PDaemon getInstance() {
        return ourInstance;
    }

    private LL2PDaemon() {
    }
}
