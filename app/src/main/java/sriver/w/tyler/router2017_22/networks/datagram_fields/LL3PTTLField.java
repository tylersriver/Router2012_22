package sriver.w.tyler.router2017_22.networks.datagram_fields;

import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler.w.sriver on 4/18/17.
 *
 * Constructor to hold the Time To Live field
 * for an LL3P Datagram
 */
public class LL3PTTLField implements DatagramHeaderField {

    // -- Fields
    // --------------------------------------------------------------
    private int ttl;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Constructor
     * @param ttl int
     */
    public LL3PTTLField(int ttl){
        this.ttl = ttl;
    }

    /**
     * Constructor overload
     * @param timeToLive string
     */
    public LL3PTTLField(String timeToLive) {
        this.ttl = Integer.valueOf(timeToLive, 16);
    }


    /**
     * Return ttl as hex string
     * @return String
     */
    @Override
    public String toHexString() {
        return Utilities.padHexString(Integer.toHexString(ttl),1);
    }

    /**
     * Explain the field
     * @return String
     */
    @Override
    public String explainSelf() {
        return "Time To Live: " + ttl;
    }

    /**
     * Get ascii for ttl
     * @return int
     */
    @Override
    public String toAsciiString() {
        return Utilities.intToAscii(ttl);
    }

    /**
     * Return ttl
     * @return int
     */
    public int getTtl() {
        return ttl;
    }

    /**
     * Set the Time to live
     * @param ttl int
     */
    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
}
