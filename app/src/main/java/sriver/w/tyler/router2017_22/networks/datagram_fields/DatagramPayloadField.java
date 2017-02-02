package sriver.w.tyler.router2017_22.networks.datagram_fields;

import java.net.DatagramPacket;

import sriver.w.tyler.router2017_22.networks.datagram.Datagram;

/**
 * Created by tyler.w.sriver on 1/26/17.
 *
 * This holds the payload information for the datagram
 */
public class DatagramPayloadField implements DatagramHeaderField {
    
    // -- Fields
    // --------------------------------------------------------------
    private Datagram packet;

    // -- Interface Methods
    @Override
    public String toString() {
        return packet.toString();
    }

    @Override
    public String toHexString() {
        return packet.toHexString();
    }

    @Override
    public String toAsciiString() {
        // TODO: 1/26/17 Fix this method

        String hexString = toHexString();
        StringBuilder output = new StringBuilder();
        for(int i=0; i < hexString.length(); i+=2) {
            String temp = hexString.substring(i, i+2);
            output.append((char)Integer.parseInt(temp, 16));
        }
        return output.toString();
    }

    @Override
    public String explainSelf() {
        return "This is the datagram payload: ".concat(packet.toString());
    }

    // -- Other Methods

    /**
     * Constructor
     * @param pkt Datagram
     */
    public DatagramPayloadField(Datagram pkt){
        this.packet = pkt;
    }

    /**
     * Return the packet
     * @return Datagram
     */
    public Datagram getPayload(){
        return packet;
    }
}
