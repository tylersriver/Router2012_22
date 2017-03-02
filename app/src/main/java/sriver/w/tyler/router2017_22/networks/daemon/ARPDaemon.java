package sriver.w.tyler.router2017_22.networks.daemon;

import java.util.Observable;
import java.util.Observer;

import sriver.w.tyler.router2017_22.networks.table.TimedTable;
import sriver.w.tyler.router2017_22.networks.tablerecord.ARPRecord;
import sriver.w.tyler.router2017_22.support.BootLoader;
import sriver.w.tyler.router2017_22.support.LabException;

/**
 * Created by tyler.w.sriver on 3/2/17.
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
     * Test the ARPDaemon
     */
    public void testARP(){
        AddARPEntry(0x712712, 0x0A01);
        AddARPEntry(0x712713, 0x0A02);
        AddARPEntry(0x712714, 0x0A03);

        arpTable.removeItem(0x712712);

        arpTable.expireRecords(200);
    }
}
