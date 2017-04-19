package sriver.w.tyler.router2017_22.networks.datagram_fields;

import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler.w.sriver on 4/18/17.
 *
 * This is a 2 byte field that contains a value which cycles from 0 to 65 (0535)
 */
public class LL3PIdentifierField implements DatagramHeaderField {

    // -- Fields
    // --------------------------------------------------------------
    private int identifier;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Constructor
     * @param identifier String
     */
    public LL3PIdentifierField(String identifier) {
        this.identifier = Integer.valueOf(identifier, 16);
    }

    /**
     * Constructor Overload
     * @param identifier int
     */
    public LL3PIdentifierField(int identifier) {
        this.identifier = identifier;
    }

    /**
     * Return identifier as hex string
     * @return String
     */
    @Override
    public String toHexString() {
        return Utilities.padHexString(Integer.toHexString(identifier), 2);
    }

    /**
     * Get string for explanation
     * @return String
     */
    @Override
    public String explainSelf() {
        return "LL3P Identifier: " + identifier;
    }

    /**
     * Get ASCII String for Identifier
     * @return String
     */
    @Override
    public String toAsciiString() {
        return Utilities.intToAscii(identifier);
    }

    /**
     * Return identifier
     * @return int
     */
    public int getIdentifier() {
        return identifier;
    }
}
