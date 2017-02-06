package sriver.w.tyler.router2017_22.networks.tablerecord;

import java.net.InetAddress;

/**
 * Created by tyler.w.sriver on 2/5/17.
 *
 *
 */
public class AdjacencyRecord extends TableRecordClass {

    // -- Fields
    // --------------------------------------------------------------
    private Integer ll2pAddress;
    private InetAddress ipaddress;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Constructor
     * @param ipaddress InetAddress
     * @param ll2pAddress Integer
     */
    public AdjacencyRecord(InetAddress ipaddress, Integer ll2pAddress){
        this.ipaddress = ipaddress;
        this.ll2pAddress = ll2pAddress;
    }

    @Override
    public Integer getKey() {
        return ll2pAddress;
    }

    @Override
    public Integer getAgeInSec() {
        return 0;
    }

    @Override
    public String toString() {
        return "LL2P Address: " + Integer.toHexString(ll2pAddress) + "; " + "IP Address: " + ipaddress.toString();
    }
}
