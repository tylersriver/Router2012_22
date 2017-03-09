package sriver.w.tyler.router2017_22.networks.daemon;

/**
 * Created by tyler.w.sriver on 3/9/17.
 */
public class LRPDaemon {
    private static LRPDaemon ourInstance = new LRPDaemon();

    public static LRPDaemon getInstance() {
        return ourInstance;
    }

    private LRPDaemon() {
    }
}
