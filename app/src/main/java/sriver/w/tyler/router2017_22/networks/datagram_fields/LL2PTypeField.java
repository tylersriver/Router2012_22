package sriver.w.tyler.router2017_22.networks.datagram_fields;

import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler.w.sriver on 1/25/17.
 *
 * This class represents the structure of the LL@P Type Field
 */
public class LL2PTypeField implements DatagramHeaderField {

    // -- Fields
    // --------------------------------------------------------------
    private Integer type;
    private String explanation;

    // -- Interface Methods
    // --------------------------------------------------------------

    @Override
    public String toString() {
        return Integer.toHexString(this.type);
    }

    @Override
    public String toHexString() {
        return Integer.toHexString(this.type);
    }

    @Override
    public String toAsciiString() {
        return Utilities.intToAscii(type);
    }

    @Override
    public String explainSelf() {
        return explanation;
    }

    // -- Other Methods
    // --------------------------------------------------------------

    /**
     * Constructor with type as an int
     * @param typeValue int
     */
    public LL2PTypeField(Integer typeValue){
        this.type = typeValue;
        setExplanation();
    }

    /**
     * Constructor with type as a string
     * @param typeValueString string
     */
    public LL2PTypeField(String typeValueString){
        this.type = Integer.parseInt(typeValueString);
        setExplanation();
    }

    /**
     * Build explanation string
     */
    private void setExplanation(){
        StringBuilder builder = new StringBuilder();
        builder.append("LL3P type (0x").append(Integer.toHexString(this.type)).append(")");
    }
}
