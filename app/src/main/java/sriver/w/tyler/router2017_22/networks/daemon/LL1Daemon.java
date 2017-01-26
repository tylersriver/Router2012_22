package sriver.w.tyler.router2017_22.networks.daemon;

import java.util.Observable;

/**
 * Created by tyler.w.sriver on 1/26/17.
 *
 * Daemon class
 */
public class LL1Daemon extends Observable {
    private static LL1Daemon ourInstance = new LL1Daemon();

    public static LL1Daemon getInstance() {
        return ourInstance;
    }

    private LL1Daemon() {
    }
}
