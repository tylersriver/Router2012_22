package sriver.w.tyler.router2017_22.support;

import android.app.Activity;
import android.provider.Contacts;

import java.util.Observable;

import sriver.w.tyler.router2017_22.UI.UIManager;
import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram.LL2PFrame;

/**
 * Created by tyler.w.sriver on 1/11/2017.
 *
 * This class boots the router and notify's
 * all the observers
 */
public class BootLoader extends Observable {

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Constructor
     * @param activity Activity
     */
    public BootLoader(Activity activity){
        bootRouter(activity);
    }

    /**
     * This class instantiates the router and needed
     * components
     * @param activity Activity
     */
    private void bootRouter(Activity activity){
        ParentActivity.setActivity(activity);
        addObserver(Constants.getInstance());
        addObserver(UIManager.getInstance());
        addObserver(FrameLogger.getInstance());

        setChanged();
        notifyObservers();
        testRouterComponents();
        UIManager.getInstance().raiseToast("Router is booted");
    }

    private void testRouterComponents(){
        String frameString = "0011223141598008Hi0000";
        LL2PFrame frame = new LL2PFrame(frameString.getBytes());

        UIManager.getInstance().raiseToast("Frame is: " + frame.toString());
        UIManager.getInstance().raiseToast("Protocol Explanation: " + frame.toProtocolExplanationString());
        UIManager.getInstance().raiseToast("The payload is: " + frame.getPayload().toAsciiString());
        UIManager.getInstance().raiseToast("Hex Characters are: " + frame.toHexString());
        UIManager.getInstance().raiseToast("Summary String is: " + frame.toSummaryString());
    }
}
