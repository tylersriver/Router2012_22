package sriver.w.tyler.router2017_22.networks.daemon;

import java.util.Observable;
import java.util.Observer;

import sriver.w.tyler.router2017_22.UI.UIManager;
import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram.LL3PDatagram;
import sriver.w.tyler.router2017_22.networks.datagram.TextDatagram;
import sriver.w.tyler.router2017_22.networks.datagram_fields.DatagramPayloadField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PAddressField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PChecksum;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PIdentifierField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PTTLField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PTypeField;
import sriver.w.tyler.router2017_22.support.BootLoader;
import sriver.w.tyler.router2017_22.support.LabException;

/**
 * Created by tyler.w.sriver on 4/18/17.
 *
 * Daemon to handle ll3p datagrams
 */
public class LL3Daemon implements Observer {

    // -- Fields
    // --------------------------------------------------------------
    private static LL3Daemon ourInstance = new LL3Daemon();
    private ARPDaemon arpDaemon;
    private LRPDaemon lrpDaemon;
    private LL2PDaemon ll2PDaemon;
    Integer identifier;


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
            identifier = 0;
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

        // -- Create Fields
        LL3PAddressField sourceAddressField = new LL3PAddressField(Integer.toHexString(Constants.SOURCE_LL3P), true);
        LL3PAddressField destAddressField = new LL3PAddressField(Integer.toHexString(ll3pAddress), false);
        LL3PTypeField typeField = new LL3PTypeField(0x8001);
        LL3PIdentifierField identifierField = new LL3PIdentifierField(getCurrentIdentifier());
        LL3PTTLField ttl = new LL3PTTLField(15);
        DatagramPayloadField payload = new DatagramPayloadField(new TextDatagram(message));
        LL3PChecksum crc = new LL3PChecksum("1234");

        // -- Create Datagram
        LL3PDatagram datagram = new LL3PDatagram(sourceAddressField, destAddressField, ttl, identifierField, typeField, crc, payload);

        sendLL3PToNextHop(datagram); // Send it
    }

    /**
     * This method is given an LL3P Datagram.
     * Using the destination address it consults the forwarding table
     * through the lrp daemon, gets the next nodes ll2p address from the
     * arp daemon, and asks the layer 2 daemon to forward this frame to the adjacent node.
     * @param datagram LL3PDatagram
     */
    public void sendLL3PToNextHop(LL3PDatagram datagram){
        try {
            int nextHop = lrpDaemon.getFIB().getNextHop(datagram.getDestinationAddress().getNetworkNumber());
            int ll2pAddress = arpDaemon.getMACAddress(nextHop);
            ll2PDaemon.forwardLL3P(datagram, ll2pAddress);
        } catch (LabException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method receives a layer 3 packet.
     * It then tells the arp daemon to touch the record for the layer 2 address
     * @param packet LL3PDatagram
     * @param ll2pAddress Integer
     */
    public void processLL3Packet(LL3PDatagram packet, Integer ll2pAddress) {

        try { // Touch associated record
            arpDaemon.getArpTable().Touch(arpDaemon.getKey(ll2pAddress));
        } catch (LabException e) {
            e.printStackTrace();
        }

        // -- Pull destination address for the packet
        int destAdd = packet.getDestinationAddress().getAddress();

        // -- Handle accordingly based on if ours or not
        if(destAdd == Constants.SOURCE_LL3P) {
            int sourceAddress = packet.getSourceAddress().getAddress();
//            UIManager.getInstance().getMessenger().receiveMessage(sourceAddress, packet.getPayload().toString());
            UIManager.getInstance().raiseToast("LL3P: " + packet.getPayload().toString());
        } else {
            // -- Decremeent ttl and check
            packet.decrementTTL();

            // -- If ttl expired throw away
            if (packet.getTtl().getTtl() != 0) {
                sendLL3PToNextHop(packet);
            } else {
                UIManager.getInstance().raiseToast("Killed a packet" + packet.toSummaryString());
            }
        }
    }

    /**
     * Get the identifier
     * @return int
     */
    private int getCurrentIdentifier(){
        identifier++;
        return identifier % 65536;
    }

}
