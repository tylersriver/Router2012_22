package sriver.w.tyler.router2017_22.support;

import android.app.ActionBar;
import android.app.Activity;
import android.provider.Contacts;

import java.util.Observable;

import sriver.w.tyler.router2017_22.UI.UIManager;
import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.daemon.LL1Daemon;
import sriver.w.tyler.router2017_22.networks.datagram.LL2PFrame;
import sriver.w.tyler.router2017_22.networks.table.Table;
import sriver.w.tyler.router2017_22.networks.tablerecord.AdjacencyRecord;

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
        addObserver(LL1Daemon.getInstance());

        setChanged();
        notifyObservers();
        testRouterComponents();
        UIManager.getInstance().raiseToast("Router is booted");
    }

    private void testRouterComponents(){

        // -- Test LL2PFrame (Lab 3)
        // -------------------------------------------------------------------
        String frameString = "0011223141598008Hi1234";
        LL2PFrame frame = new LL2PFrame(frameString.getBytes());

//        UIManager.getInstance().raiseToast("Frame is: " + frame.toString());
//        UIManager.getInstance().raiseToast("Protocol Explanation: " + frame.toProtocolExplanationString());
//        UIManager.getInstance().raiseToast("The payload is: " + frame.getPayload().toString());
//        UIManager.getInstance().raiseToast("Hex Characters are: " + frame.toHexString());
//        UIManager.getInstance().raiseToast("Summary String is: " + frame.toSummaryString());

        // -- Test Table and LL1Daemon (Lab 4)
        // -------------------------------------------------------------------
        LL1Daemon ll1 = LL1Daemon.getInstance();

        // -- Test table class
        AdjacencyRecord adjacencyRecord = new AdjacencyRecord(GetIPAddress.getInstance().getInetAddress("10.31.1.1"), 0x314159);
        AdjacencyRecord adjacencyRecord1 = new AdjacencyRecord(GetIPAddress.getInstance().getInetAddress("10.31.1.2"), 0x314158);
        UIManager.getInstance().raiseToast("Adjacency records created: "+adjacencyRecord.toString());

        Table table = new Table();
        UIManager.getInstance().raiseToast("Table created");
        table.addItem(adjacencyRecord);
        table.addItem(adjacencyRecord1);
        UIManager.getInstance().raiseToast("Adjacency records added to table");
        table.removeItem(0x31459);
        UIManager.getInstance().raiseToast("Adjacency record removed from table");

        // -- Test LL1Daemon
        ll1.addAdjacency("314159", "10.31.1.1");
        AdjacencyRecord adjacencyRecord2 = ll1.getAdjacencyRecord(0x314159);
        UIManager.getInstance().raiseToast("Record in ll1 table is: " + adjacencyRecord2.toString());
        ll1.removeAdjacency(adjacencyRecord);
        UIManager.getInstance().raiseToast("Record removed from ll1 table");
    }
}
