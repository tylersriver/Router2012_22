package sriver.w.tyler.router2017_22.UI;

import android.app.Activity;
import android.content.Context;

import java.util.Observable;
import java.util.Observer;

import sriver.w.tyler.router2017_22.support.BootLoader;
import sriver.w.tyler.router2017_22.support.ParentActivity;

/**
 * Created by tyler.w.sriver on 2/16/17.
 *
 * Class for managing the UI of the tables
 */
public class TableUI implements Runnable, Observer {

    // -- Fields
    // --------------------------------------------------------------
    private SingleTableUI adjacencyUI;
    private SingleTableUI arpTableUI;
    private SingleTableUI routingTableUI;
    private SingleTableUI forwardingUI;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Empty constructor
     */
    public TableUI(){
    }

    /**
     * Function to keep display current
     * Note: will be called every second by scheduler's timer
     */
    @Override
    public void run() {
    }

    /**
     * Wait on boot to load objects
     * @param o Observable
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass().equals(BootLoader.class)) {
            Activity activity = ParentActivity.getActivity();
            Context context = activity.getBaseContext(); // TODO: 2/19/2017 Probably needs changed?

            // -- Create Table UI's
            adjacencyUI = new SingleTableUI(); // TODO: 2/19/2017 Arguments
            arpTableUI = new SingleTableUI(); // TODO: 2/19/2017 Arguments
            routingTableUI = new SingleTableUI(); // TODO: 2/19/2017 Arguments
            forwardingUI = new SingleTableUI(); // TODO: 2/19/2017 Arguments
        }
    }
}
