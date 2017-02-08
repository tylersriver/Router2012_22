package sriver.w.tyler.router2017_22.support;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by tyler on 2/8/2017.
 */

public class PacketInformation {

    // -- Fields
    // --------------------------------------------------------------
    private DatagramSocket socket;
    private DatagramPacket packet;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Constructor
     * @param socket DatagramSocket
     * @param packet DatagramPacket
     */
    public PacketInformation(DatagramSocket socket, DatagramPacket packet){
        this.socket= socket;
        this.packet = packet;
    }

    /**
     * Get the socket
     * @return DatagramSocket
     */
    public DatagramSocket getSocket() {
        return socket;
    }

    /**
     * Get the packet
     * @return DatagramPacket
     */
    public DatagramPacket getPacket() {
        return packet;
    }
}
