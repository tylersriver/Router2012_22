package sriver.w.tyler.router2017_22.networks.daemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import sriver.w.tyler.router2017_22.networks.datagram.LRPPacket;
import sriver.w.tyler.router2017_22.networks.datagram_fields.NetworkDistancePair;
import sriver.w.tyler.router2017_22.networks.table.RoutingTable;
import sriver.w.tyler.router2017_22.networks.table.Table;
import sriver.w.tyler.router2017_22.networks.tablerecord.ARPRecord;
import sriver.w.tyler.router2017_22.networks.tablerecord.RoutingRecord;
import sriver.w.tyler.router2017_22.networks.tablerecord.TableRecord;
import sriver.w.tyler.router2017_22.networks.tablerecord.TableRecordClass;
import sriver.w.tyler.router2017_22.support.BootLoader;
import sriver.w.tyler.router2017_22.support.LabException;

/**
 * Created by tyler.w.sriver on 3/9/17.
 *
 * TODO: Describe
 */
public class LRPDaemon implements Observer, Runnable {

    // -- Fields
    // --------------------------------------------------------------v
    private static LRPDaemon ourInstance = new LRPDaemon();
    public ARPDaemon arpDaemon;
    private RoutingTable routingTable;
    private RoutingTable forwardingTable;
    private LL2PDaemon ll2PDaemon;
    private int sequenceNumber;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Return singleton instance
     * @return LRPDaemon
     */
    public static LRPDaemon getInstance() {
        return ourInstance;
    }

    /**
     * Constructor, instantiates fields
     */
    private LRPDaemon() {
        routingTable = new RoutingTable();
        forwardingTable = new RoutingTable();
        sequenceNumber = 0;
    }

    // -- Interface Methods
    // --------------------------------------------------------------v

    /**
     * Updates deamon when called by bootloader
     * or the
     * @param o Observable
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass().equals(BootLoader.class)) {
            arpDaemon = ARPDaemon.getInstance();
            ll2PDaemon = LL2PDaemon.getInstance();
        } else if (o.getClass().equals(ARPDaemon.class)) {
            List<TableRecordClass> deletedRecords = (ArrayList<TableRecordClass>) arg;

            for (int i = 0; i < deletedRecords.size(); i++) {
                forwardingTable.removeRoutesFrom(((ARPRecord)deletedRecords.get(i)).getLl3pAddress());
                routingTable.removeRoutesFrom(((ARPRecord)deletedRecords.get(i)).getLl3pAddress());
            }
        }
    }

    @Override
    public void run() {

    }

    /**
     * This method does 2 things.
     *      - touch the ARP entry that contains the LL2P address that sent us this LRP packet.
     *      - unpack the routing update and adds or updates every route in the routing table
     * @param lrpPacket byte[]
     * @param ll2pSource Integer
     */
    public void receiveNewLRP(byte[] lrpPacket, Integer ll2pSource){
        try { // Touch record
            arpDaemon.getArpTable().Touch(getRecordMatchingLL2P(ll2pSource).getLl3pAddress());
        } catch (LabException e) {
            e.printStackTrace();
        }

        // Process packet
        processLRP(lrpPacket);
    }

    /**
     * Method to get record with matching ll2p address
     * @param ll2pSource Integer
     * @return ARPRecord
     */
    private ARPRecord getRecordMatchingLL2P(Integer ll2pSource){
        ARPRecord recordToTouch = null;

        // Find ARPRecord with matching ll2paddress
        for (TableRecord record: arpDaemon.getArpTable().getTableAsArrayList()) {
            ARPRecord recordCast = (ARPRecord) record;
            if(recordCast.getLl2pAddress() == ll2pSource){
                recordToTouch = recordCast;
            }
        }
        return recordToTouch;
    }

    /**
     * Convert bytes to lrp packet
     * and process
     * @param lrp byte[]
     */
    public void processLRP(byte[] lrp){
        processLRPPacket(new LRPPacket(lrp));
    }

    /**
     * TODO: Describe
     * @param lrp LRPPacket
     */
    public void processLRPPacket(LRPPacket lrp) {

        List<NetworkDistancePair> receivedRoutes = lrp.getRoutes();
        List<RoutingRecord> newRecords = new ArrayList<>();

        // -- Transpose LRPPacket's into Routing Records
        for(int i = 0; i < receivedRoutes.size(); i++) {
            NetworkDistancePair netDistPair = receivedRoutes.get(i);
            RoutingRecord temp = new RoutingRecord(netDistPair.getNetwork(), netDistPair.getDistance()+1, lrp.getSourceLL3P().getAddress());
            newRecords.add(temp);
        }

        // -- Update Best Routes
        if(routingTable.addRoutes(newRecords)) {
            forwardingTable.clear();
            forwardingTable.addRoutes(routingTable.getBestRoutes());
        }
    }

    // -- Getters
    // --------------------------------------------------------------v

    /**
     * Get the routing table as RoutingTable
     * @return RoutingTable
     */
    public RoutingTable getRoutingTable() {
        return routingTable;
    }

    /**
     * Get routing table as list
     * @return List
     */
    public List<TableRecord> getRoutingTableAsList(){
        return routingTable.getTableAsArrayList();
    }

    /**
     * Get routing TAble as base class
     * @return Table
     */
    public Table getRoutingTableAsTable(){
        return routingTable;
    }

    /**
     * Get forwarding table as routing table
     * @return RoutingTable
     */
    public RoutingTable getFIB(){
        return forwardingTable;
    }

    /**
     * Get forwarding table as List
     * @return List
     */
    public List<TableRecord> getForwardingTableAsList(){
        return forwardingTable.getTableAsArrayList();
    }

    /**
     * Get forwarding table as base class
     * @return Table
     */
    public Table getForwardingTable(){
        return forwardingTable;
    }

}
