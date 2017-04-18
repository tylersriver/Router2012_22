package sriver.w.tyler.router2017_22.support;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import sriver.w.tyler.router2017_22.UI.UIManager;
import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.daemon.ARPDaemon;
import sriver.w.tyler.router2017_22.networks.daemon.LL1Daemon;
import sriver.w.tyler.router2017_22.networks.daemon.LL2PDaemon;
import sriver.w.tyler.router2017_22.networks.daemon.LRPDaemon;
import sriver.w.tyler.router2017_22.networks.daemon.Scheduler;
import sriver.w.tyler.router2017_22.networks.datagram.ARPDatagram;
import sriver.w.tyler.router2017_22.networks.datagram.LL2PFrame;
import sriver.w.tyler.router2017_22.networks.table.RoutingTable;
import sriver.w.tyler.router2017_22.networks.table.Table;
import sriver.w.tyler.router2017_22.networks.tablerecord.AdjacencyRecord;
import sriver.w.tyler.router2017_22.networks.tablerecord.RoutingRecord;

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
        addObserver(UIManager.getInstance().getTableUI());
        addObserver(UIManager.getInstance().getSnifferUI());
        addObserver(FrameLogger.getInstance());
        addObserver(LL1Daemon.getInstance());
        addObserver(LL2PDaemon.getInstance());
        addObserver(ARPDaemon.getInstance());
        addObserver(LRPDaemon.getInstance());
        addObserver(Scheduler.getInstance());

        setChanged();
        notifyObservers();
        testRouterComponents();
        UIManager.getInstance().raiseToast("Router is booted");
    }

    private void testRouterComponents(){

        // -- Test LL2PFrame (Lab 3)
        // -------------------------------------------------------------------
        String frameString = "1122333141598008SentFromRouter1234";
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
        ll1.addAdjacency("112233", "171.17.1.13");
        ll1.sendFrame(frame);



        // -- Test Factory
        Object[] params = {GetIPAddress.getInstance().getInetAddress("10.31.1.1"), 0x314158};
        AdjacencyRecord record = (AdjacencyRecord) Factory.getInstance().getTableRecord(Constants.ADJACENCY_TABLE_RECORD, params);
        Log.d(Constants.logTag, "Factory generated record is: "+record.toString());



        // -- Test ARPDaemon (Lab 7)
        // -------------------------------------------------------------------
        //ARPDaemon.getInstance().testARP();


        // ===================================================================
        //
        // -- Test Routing and Forwarding Tables (Lab 9)
        //
        // ===================================================================
        RoutingTable routingTable = new RoutingTable();
        RoutingTable forwardingTable = new RoutingTable();

        // -- Create records
        RoutingRecord record1 = new RoutingRecord(8,1,8);
        RoutingRecord record2 = new RoutingRecord(1,0,1);
        RoutingRecord record3 = new RoutingRecord(2,1,8);
        RoutingRecord record4 = new RoutingRecord(4,2,7);
        RoutingRecord record5 = new RoutingRecord(7,3,7);
        RoutingRecord record6 = new RoutingRecord(8,2,7);
        RoutingRecord record7 = new RoutingRecord(2,1,7);

        // -- Add routes
        routingTable.addNewRoute(record1);
        routingTable.addNewRoute(record2);
        routingTable.addNewRoute(record3);
        routingTable.addNewRoute(record4);
        routingTable.addNewRoute(record5);
        routingTable.addNewRoute(record6);
        routingTable.addNewRoute(record7);

        // -- Test expire
        routingTable.expireRecords(1);
        Log.d(Constants.logTag, "Num Records: " +routingTable.getTableAsArrayList().size());

        // -- Test route update
        record1 = new RoutingRecord(8,3,5);
        record2 = new RoutingRecord(8,4,5);
        routingTable.addNewRoute(record1);
        routingTable.addNewRoute(record2);
        Log.d(Constants.logTag, "Routes added");

        // -- Test Best routes
        record1 = new RoutingRecord(4,3,5);
        record2 = new RoutingRecord(4,4,6);
        record3 = new RoutingRecord(4,5,7);

        record4 = new RoutingRecord(5,3,6);
        record5 = new RoutingRecord(5,4,7);
        record6 = new RoutingRecord(5,5,8);

        routingTable.addNewRoute(record1);
        routingTable.addNewRoute(record2);
        routingTable.addNewRoute(record3);
        routingTable.addNewRoute(record4);
        routingTable.addNewRoute(record5);
        routingTable.addNewRoute(record6);

        List<RoutingRecord> bestRoutes = new ArrayList<>();
        bestRoutes = routingTable.getBestRoutes();
        Log.d(Constants.logTag, "Best Routes got");

        routingTable.addNewRoute(record1);
        routingTable.addNewRoute(record2);
        routingTable.addNewRoute(record3);
        routingTable.addNewRoute(record4);
        routingTable.addNewRoute(record5);
        routingTable.addNewRoute(record6);
        Log.d(Constants.logTag, "Check LastTimeTouched");
        // ===================================================================

        // -- Lab 10
        LRPDaemon.getInstance().getRoutingTable().addNewRoute(record1);

    }
}
