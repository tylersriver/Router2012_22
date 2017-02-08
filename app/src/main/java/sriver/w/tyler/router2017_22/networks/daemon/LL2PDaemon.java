package sriver.w.tyler.router2017_22.networks.daemon;

import android.util.Log;

import sriver.w.tyler.router2017_22.UI.UIManager;
import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram.LL2PFrame;

/**
 * Created by tyler on 2/7/2017.
 *
 * Daemon to process LL2P frames
 */
public class LL2PDaemon {
    private static LL2PDaemon ourInstance = new LL2PDaemon();

    public static LL2PDaemon getInstance() {
        return ourInstance;
    }

    private LL2PDaemon() {
    }

    public void processLL2PFrame(LL2PFrame frame){
        Log.d(Constants.logTag, "processLL2PFrame: "+ frame.toString());
        UIManager.getInstance().raiseToast("Receive LL2PFrame: " + frame.toString());
    }
}
