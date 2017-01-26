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
        return Integer.toHexString(Integer.parseInt(crcValue));
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
    public CRC(String typeValueString) throws UnsupportedEncodingException {
        this.crcValue = truncateWhenUTF8(typeValueString, 2);
    }

    /**
     * This function truncates a given string to the
     * given max length in bytes
     * @param s string
     * @param maxBytes int
     * @return truncated string
     */
    private String truncateWhenUTF8(String s, int maxBytes) {
        int b = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // ranges from http://en.wikipedia.org/wiki/UTF-8
            int skip = 0;
            int more;
            if (c <= 0x007f) {
                more = 1;
            }
            else if (c <= 0x07FF) {
                more = 2;
            } else if (c <= 0xd7ff) {
                more = 3;
            } else if (c <= 0xDFFF) {
                // surrogate area, consume next char as well
                more = 4;
                skip = 1;
            } else {
                more = 3;
            }

            if (b + more > maxBytes) {
                return s.substring(0, i);
            }
            b += more;
            i += skip;
        }
        return s;
    }
}
