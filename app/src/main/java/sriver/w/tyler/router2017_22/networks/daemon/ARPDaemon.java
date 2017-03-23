package sriver.w.tyler.router2017_22.networks.daemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.StringTokenizer;

import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram.ARPDatagram;
import sriver.w.tyler.router2017_22.networks.table.TimedTable;
import sriver.w.tyler.router2017_22.networks.tablerecord.ARPRecord;
import sriver.w.tyler.router2017_22.networks.tablerecord.TableRecord;
import sriver.w.tyler.router2017_22.support.BootLoader;
import sriver.w.tyler.router2017_22.support.LabException;
import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler.w.sriver on 3/2/17.
 *
 * Daemon for handling ARP records
 */
public class ARPDaemon extends Observable implements Observer, Runnable {

    // -- Fields
    // --------------------------------------------------------------
    private static ARPDaemon ourInstance = new ARPDaemon();
    private TimedTable arpTable;
    private LL2PDaemon ll2PDaemon;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Empty Constructor
     */
    private ARPDaemon(){
        arpTable = new TimedTable();
    }

    /**
     * Return singleton instance
     * @return ARPDaemon
     */
    public static ARPDaemon getInstance(){
        return ourInstance;
    }

    /**
     * Observer update
     * @param o Observable
     * @param arg arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass().equals(BootLoader.class)) {
            ll2PDaemon = LL2PDaemon.getInstance();
        }
    }

    /**
     * Run this to spin off threads
     */
    @Override
    public void run() {
        arpTable.expireRecords(Constants.MAX_AGE_ALLOWED);
    }

    /**
     * Add Entry to table if not exists
     * @param ll2pAddress Integer
     * @param ll3pAddress Integer
     */
    private void AddARPEntry(Integer ll2pAddress, Integer ll3pAddress){
        ARPRecord record = new ARPRecord(ll2pAddress, ll3pAddress);
        try {
            record = (ARPRecord) arpTable.getItem(record);
            arpTable.Touch(record.getKey());
        } catch (LabException ex) {
            arpTable.addItem(record);
        }
    }

    /**
     * Return the MAC address for given ll3paddress
     * @param ll3pAddress Integer
     * @return Integer
     */
    public Integer getMACAddress(Integer ll3pAddress){
        try {
            ARPRecord record = (ARPRecord) arpTable.getItem(ll3pAddress);
            return record.getLl2pAddress();
        } catch (LabException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Test the ARPDaemon
     */
    public void testARP(){
        AddARPEntry(0x712712, 0x0A01);
        AddARPEntry(0x712713, 0x0A02);
        AddARPEntry(0x712714, 0x0A03);
        arpTable.removeItem(0x712712);
        arpTable.expireRecords(3);
        ll2PDaemon.sendArpRequest(0x112233);
    }

    /**
     * Get the ARP Table
     * @return TimedTable
     */
    public TimedTable getArpTable() {
        return arpTable;
    }

    /**
     * Get list of all ll3p Addresses in table
     * @return ArrayList
     */
    public ArrayList<Integer> getAttachedNodes(){
        ArrayList<Integer> ll3pAddresses = new ArrayList<Integer>();
        for (TableRecord record : arpTable.getTableAsArrayList()){
            ARPRecord recordToUse = (ARPRecord) record;
            ll3pAddresses.add(recordToUse.getLl3pAddress());
        }
        return ll3pAddresses;
    }

    /**
     * Handle receiving an arp reply
     * @param ll2pAddress int
     * @param datagram ARPDatagram
     */
    public void processArpReply(int ll2pAddress, ARPDatagram datagram){
        AddARPEntry(ll2pAddress, Integer.valueOf(datagram.toTransmissionString(),16));
    }

    /**
     * Add/Touch record then send reply
     * @param ll2pAddress Integer
     * @param datagram ARPDatagram
     */
    public void processArpRequest(Integer ll2pAddress, ARPDatagram datagram){
        AddARPEntry(ll2pAddress, Integer.valueOf(datagram.toTransmissionString(),16));
        ll2PDaemon.sendArpReply(ll2pAddress);
    }

    /**
     * Send an ARP request
     * @param ll2paddress Integer
     */
    public void sendARPRequest(Integer ll2paddress){
        ll2PDaemon.sendArpRequest(ll2paddress);
    }
}
