package sriver.w.tyler.router2017_22.networks.datagram_fields;

import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler on 3/1/2017.
 *
 * Class representing the LL3PAddress
 */
public class LL3PAddressField implements DatagramHeaderField {


    // -- Fields
    // --------------------------------------------------------------
    private Integer address;
    private Integer networkNumber;
    private Integer hostNumber;
    private Boolean isSourceAddress;
    private String explanationString;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * constructor
     * @param hexAddress String
     * @param isSourceAddress Boolean
     */
    public LL3PAddressField(String hexAddress, Boolean isSourceAddress){
        this.isSourceAddress = isSourceAddress;
        this.address = Integer.valueOf(hexAddress, 16);
        this.networkNumber = Integer.valueOf(hexAddress.substring(0,2), 16);
        this.hostNumber = Integer.valueOf(hexAddress.substring(2,4), 16);
        this.explanationString = String.valueOf(networkNumber) + "." + Integer.toHexString(hostNumber);
    }

    /**
     * Get the address as a hex string
     * @return String of hex characters
     */
    @Override
    public String toHexString() {
        return Integer.toHexString(address);
    }

    /**
     * Get the explanation string
     * @return String of format networkNum.hostNum
     */
    @Override
    public String explainSelf() {
        return explanationString;
    }

    /**
     * Get the ascii representation of the address
     * @return String
     */
    @Override
    public String toAsciiString() {
        return Utilities.intToAscii(address);
    }
}
