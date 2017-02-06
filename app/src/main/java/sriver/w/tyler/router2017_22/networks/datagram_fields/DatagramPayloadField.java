package sriver.w.tyler.router2017_22.networks.datagram_fields;

import android.provider.ContactsContract;

import java.net.DatagramPacket;

import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram.Datagram;
import sriver.w.tyler.router2017_22.networks.datagram.TextDatagram;
import sriver.w.tyler.router2017_22.support.Factory;

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
        return packet.toProtocolExplanationString();
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
     * constructor overload
     * @param FieldValue int
     * @param contents string
     */
    public DatagramPayloadField(int FieldValue, String contents) {
        packet = Factory.getInstance().createPayload(FieldValue, contents);
    }

    /**
     * Return the packet
     * @return Datagram
     */
    public Datagram getPayload(){
        return packet;
    }
}
