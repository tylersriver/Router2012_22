package sriver.w.tyler.router2017_22.networks.datagram_fields;

import android.net.Network;

import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler.w.sriver on 3/25/17.
 *
 * This class holds a pair of bytes that
 * represent the "Network" name and it's distance from
 * your router
 */
public class NetworkDistancePair implements DatagramHeaderField {

    // -- Fields
    // --------------------------------------------------------------
    private Integer network;
    private Integer distance;

    // -- Methods
    // ==============================================================

    /**
     * Builds the pair from the given hex string
     * Note: Network: first 2 characters Distance: last 2 characters
     * @param netDistPair String
     */
    public NetworkDistancePair(String netDistPair){
        network = Integer.valueOf(netDistPair.substring(0,2), 16);
        distance = Integer.valueOf(netDistPair.substring(2,4), 16);
    }

    /**
     * Overloaded constructor to take the fields as to Integers
     * @param net Integer
     * @param dist Integer
     */
    public NetworkDistancePair(Integer net, Integer dist){
        this.network = net;
        this.distance = dist;
    }

    // -- Interface Methods
    // --------------------------------------------------------------
    /**
     * Return hex string of combined Network and Distance
     * @return String
     */
    @Override
    public String toHexString() {
        return Utilities.padHexString(Integer.toHexString(network), 1) + Utilities.padHexString(Integer.toHexString(distance), 1);
    }

    /**
     * Explain the Network and Distance pair
     * Note: Format: Network: 00 | Distance: 00
     * @return String
     */
    @Override
    public String explainSelf() {
        return "Network: " + Integer.toHexString(network) + " | " + "Distance: " + Integer.toHexString(distance);
    }

    /**
     * Return ASCII string of pair
     * @return String
     */
    @Override
    public String toAsciiString() {
        return Utilities.intToAscii(network) + Utilities.intToAscii(distance);
    }

    // -- Getters
    // --------------------------------------------------------------

    public Integer getNetwork() {
        return network;
    }

    public Integer getDistance() {
        return distance;
    }
}
