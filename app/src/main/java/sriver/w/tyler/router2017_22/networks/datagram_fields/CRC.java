package sriver.w.tyler.router2017_22.networks.datagram_fields;

import java.io.UnsupportedEncodingException;

/**
 * Created by tyler.w.sriver on 1/26/17.
 *
 * This class implements the crc check functionality
 */
public class CRC implements DatagramHeaderField {

    // -- Fields
    // --------------------------------------------------------------
    private String crcValue;

    // -- Interface Methods
    @Override
    public String toString() {
        return crcValue;
    }

    @Override
    public String toHexString() {
        return Integer.toHexString(Integer.valueOf(crcValue, 16));
    }

    @Override
    public String toAsciiString() {
        return this.crcValue;
    }

    @Override
    public String explainSelf() {
        return "CRC is: ".concat(crcValue);
    }

    // -- Other Methods
    // --------------------------------------------------------------

    /**
     * Constructor
     * @param typeValueString string
     */
    public CRC(String typeValueString) {
        this.crcValue = typeValueString.substring(0, 4);
    }
}
