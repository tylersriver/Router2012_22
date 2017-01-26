package sriver.w.tyler.router2017_22.networks.datagram_fields;

/**
 * Created by tyler.w.sriver on 1/24/17.
 *
 * This class describes the structure of
 * the LL2P Frame
 */
public class LL2PAddressField implements DatagramHeaderField {

    // -- Fields
    // --------------------------------------------------------------
    private Integer address; // 0123456789 ABCDEF |
    private boolean isSourceAddress;
    private String explanation;

    // -- Interface Methods
    @Override
    public String toString() {
        return Integer.toHexString(address);
    }

    @Override
    public String toHexString() {
        return Integer.toHexString(address);
    }

    @Override
    public String toAsciiString() {
        int temp = address;
        StringBuilder builder = new StringBuilder();
        while(temp > 0){
            builder.append(Integer.toString(temp % 256));
            temp = temp / 256;
        }
        return builder.toString();
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
        this.address = Integer.parseInt(address);
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
}
