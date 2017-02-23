package sriver.w.tyler.router2017_22.networks.datagram_fields;

import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler.w.sriver on 1/24/17.
 *
 * This class describes the structure of
 * the LL2P Frame
 */
public class LL2PAddressField implements DatagramHeaderField {

    // -- Fields
    // --------------------------------------------------------------
    private Integer address;
    private boolean isSourceAddress;
    private String explanation;

    // -- Interface Methods
    @Override
    public String toString() {
        return Utilities.padHexString(Integer.toHexString(address), 3);
    }

    @Override
    public String toHexString() {
        return Utilities.padHexString(Integer.toHexString(address), 3);
    }

    @Override
    public String toAsciiString() {
        return Utilities.intToAscii(address);
    }

    @Override
    public String explainSelf() {
        return explanation;
    }



    // -- Other Methods
    // --------------------------------------------------------------
    /**
     * Constructor with address as int
     * @param address int
     * @param isSource boolean
     */
    public LL2PAddressField(int address, boolean isSource){
        this.address = address;
        isSourceAddress = isSource;
        setExplanation();
    }

    /**
     * Constructor with address as address
     * @param address String
     * @param isSource boolean
     */
    public LL2PAddressField(String address, boolean isSource){
        this.address = Integer.parseInt(address, 16);
        isSourceAddress = isSource;
        setExplanation();
    }

    /**
     * Build explanation string
     */
    private void setExplanation(){
        StringBuilder builder = new StringBuilder();
        if(isSourceAddress){
            builder.append("Source");
        } else {
            builder.append("Destination");
        }
        builder.append("LL2P Address: Ox");
        builder.append(Integer.toHexString(address));
        explanation = builder.toString();
    }

    /**
     * Return true if this is a source address
     * @return boolean
     */
    public boolean isSourceAddressField(){
        return isSourceAddress;
    }

    /**
     * Get the address
     * @return Integer
     */
    public Integer getAddress(){
        return address;
    }
}
