package sriver.w.tyler.router2017_22.networks.daemon;

import java.util.Observable;
import java.util.Observer;

import sriver.w.tyler.router2017_22.networks.datagram.LL3PDatagram;
import sriver.w.tyler.router2017_22.support.BootLoader;

/**
 * Created by tyler.w.sriver on 4/18/17.
 */

public class LL3Daemon implements Observer {

    // -- Fields
    // --------------------------------------------------------------
    private static LL3Daemon ourInstance = new LL3Daemon();
    private ARPDaemon arpDaemon;
    private LRPDaemon lrpDaemon;
    private LL2PDaemon ll2PDaemon;



    // -- Methods
    // --------------------------------------------------------------

    /**
     * Return the singleton instance
     * @return LL3Daemon
     */
    public static LL3Daemon getInstance() {
        return ourInstance;
    }

    /**
     * Constructor called internally
     */
    private LL3Daemon() {
    }

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Make fields on update from bootloader
     * @param o Observable
     * @param arg Object
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o.getClass().equals(BootLoader.class)) {
            arpDaemon = ARPDaemon.getInstance();
            ll2PDaemon = LL2PDaemon.getInstance();
            lrpDaemon = LRPDaemon.getInstance();
        }
    }

    /**
     * This method will create a layer 3 packet object using the
     * string and destination address.
     * It will then ask the method ‘sendLL3PToNextHop(LL3Datagram)
     * to transmit it to the appropriate neighbor.
     * @param message String
     * @param ll3pAddress Integer
     */
    public void sendPayload(String message, Integer ll3pAddress){

    }

    /**
     * This method is given an LL3P Datagram.
     * Using the destination address it consults the forwarding table
     * through the lrp daemon, gets the next nodes ll2p address from the
     * arp daemon, and asks the layer 2 daemon to forward this frame to the adjacent node.
     * @param datagram LL3PDatagram
     */
    public void sendLL3PToNextHop(LL3PDatagram datagram){

    }

    /**
     * This method receives a layer 3 packet.
     * It then tells the arp daemon to touch the record for the layer 2 address
     * @param packet LL3PDatagram
     * @param ll2pAddress Integer
     */
    public void processLL3Packet(LL3PDatagram packet, Integer ll2pAddress) {

    }

}
