package sriver.w.tyler.router2017_22.networks.datagram_fields;

import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler.w.sriver on 3/25/17.
 *
 * TODO: Fill in
 */
public class LRPRouteCount implements DatagramHeaderField {

    // -- Fields
    // --------------------------------------------------------------
    private Integer routeCount;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Set the routeCount
     * @param routeCount String
     */
    public LRPRouteCount(String routeCount){
        this.routeCount = Integer.valueOf(routeCount, 16);
    }

    /**
     * Return hex string of route count
     * @return String
     */
    @Override
    public String toHexString() {
        return Integer.toHexString(routeCount);
    }

    /**
     * Explain the route count
     * @return String
     */
    @Override
    public String explainSelf() {
        return "Route Count: " + Integer.toHexString(routeCount);
    }

    /**
     * Return ASCII string of routeCount
     * @return String
     */
    @Override
    public String toAsciiString() {
        return Utilities.intToAscii(routeCount);
    }

    /**
     * Return route count
     * @return Integer
     */
    public Integer getRouteCount() {
        return routeCount;
    }
}
