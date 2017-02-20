package sriver.w.tyler.router2017_22.support;

import android.app.ActionBar;
import android.app.Activity;
import android.provider.Contacts;
import android.util.Log;

import java.util.Observable;

import sriver.w.tyler.router2017_22.UI.SingleTableUI;
import sriver.w.tyler.router2017_22.UI.TableUI;
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
        addObserver(UIManager.getInstance().getTableUI());

        setChanged();
        notifyObservers();
        testRouterComponents();
        UIManager.getInstance().raiseToast("Router is booted");
    }

    private void testRouterComponents(){

        // -- Test LL2PFrame (Lab 3)
        // -------------------------------------------------------------------
        String frameString = "0011223141598008SentFromRouter1234";
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
        Log.d(Constants.logTag, "Adjacency records created ");

        Table table = new Table();
        Log.d(Constants.logTag, "Table created ");
        table.addItem(adjacencyRecord);
        table.addItem(adjacencyRecord1);
        Log.d(Constants.logTag, "Adjacency records added to table");
        table.removeItem(0x31459);
        Log.d(Constants.logTag,  "Adjacency record removed from table");

        // -- Test LL1Daemon
        ll1.addAdjacency("314159", "10.31.1.1");
        AdjacencyRecord adjacencyRecord2 = ll1.getAdjacencyRecord(0x314159);
        Log.d(Constants.logTag, "Record in LL1 table is:  "+ adjacencyRecord2.toString());
        ll1.removeAdjacency(adjacencyRecord);
        Log.d(Constants.logTag, "Record removed from LL1 table");

        // -- Send Frame
        ll1.addAdjacency("001122", "10.30.48.165");
        ll1.sendFrame(frame);

        // -- Test Factory
        Object[] params = {GetIPAddress.getInstance().getInetAddress("10.31.1.1"), 0x314158};
        AdjacencyRecord record = (AdjacencyRecord) Factory.getInstance().getTableRecord(Constants.ADJACENCY_TABLE_RECORD, params);
        Log.d(Constants.logTag, "Factory generated record is: "+record.toString());

        // -- Test Table and LL1Daemon (Lab 5)
        // -------------------------------------------------------------------
    }
}
