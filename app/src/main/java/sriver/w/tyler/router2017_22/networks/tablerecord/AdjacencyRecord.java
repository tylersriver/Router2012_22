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

    /**
     * Constructor that takes object array
     * Note: params[0] = ipaddress, params[1] = ll2paddress
     * @param params Object
     */
    public AdjacencyRecord(Object[] params){
        this.ipaddress = (InetAddress) params[0];
        this.ll2pAddress = (Integer) params[1];
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

    // -- Getter/Setters
    /**
     * Return the LL2P address
     * @return Integer
     */
    public Integer getLl2pAddress() {
        return ll2pAddress;
    }

    /**
     * Set the LL2PAddress
     * @param ll2pAddress Integer
     */
    public void setLl2pAddress(Integer ll2pAddress) {
        this.ll2pAddress = ll2pAddress;
    }

    /**
     * Return the ip Address
     * @return InetAddress
     */
    public InetAddress getIpaddress() {
        return ipaddress;
    }

    /**
     * Set ipaddress
     * @param ipaddress InetAddress
     */
    public void setIpaddress(InetAddress ipaddress) {
        this.ipaddress = ipaddress;
    }
}
