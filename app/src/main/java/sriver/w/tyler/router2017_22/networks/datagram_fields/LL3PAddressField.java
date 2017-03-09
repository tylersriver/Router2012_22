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
        setExplanation();
    }

    /**
     * Get the address as a hex string
     * @return String of hex characters
     */
    @Override
    public String toHexString() {
        return Utilities.padHexString(Integer.toHexString(networkNumber), 1) + "."
          + Utilities.padHexString(Integer.toHexString(hostNumber), 1);
    }

    /**
     * MEthod to build transmission hex string
     * @return String
     */
    public String toTransmissionString() {
        return Utilities.padHexString(Integer.toHexString(address), 1);
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

    /**
     * Build explanation string
     */
    private void setExplanation(){
        StringBuilder builder = new StringBuilder();

        // -- Check isSource
        if(isSourceAddress) builder.append("Source");
        else builder.append("Destination");

        builder.append("LL3P Address: ");
        builder.append(Integer.toHexString(address));
        explanationString = builder.toString();
    }
}
