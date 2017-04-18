package sriver.w.tyler.router2017_22.UI;

import android.app.Activity;
import android.content.Context;

import java.util.Observable;
import java.util.Observer;

import sriver.w.tyler.router2017_22.R;
import sriver.w.tyler.router2017_22.networks.daemon.ARPDaemon;
import sriver.w.tyler.router2017_22.networks.daemon.LL1Daemon;
import sriver.w.tyler.router2017_22.networks.daemon.LRPDaemon;
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
        arpTableUI.updateView();
        adjacencyUI.updateView();
        routingTableUI.updateView();
        forwardingUI.updateView();
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

            // -- Create Table UI's
            adjacencyUI = new AdjacencyTableUI(activity, R.id.adjacencyListView, LL1Daemon.getInstance().getAdjacencyTable(), LL1Daemon.getInstance());
            arpTableUI = new SingleTableUI(activity, R.id.arpListView, ARPDaemon.getInstance().getArpTable());
            routingTableUI = new SingleTableUI(activity, R.id.routingListView, LRPDaemon.getInstance().getRoutingTable());
            forwardingUI = new SingleTableUI(activity, R.id.forwardingListView, LRPDaemon.getInstance().getForwardingTable());

            ARPDaemon.getInstance().addObserver(arpTableUI);
        }
    }
}
