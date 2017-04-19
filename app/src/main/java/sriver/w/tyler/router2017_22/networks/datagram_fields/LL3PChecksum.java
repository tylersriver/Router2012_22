package sriver.w.tyler.router2017_22.networks.datagram_fields;

/**
 * Created by tyler.w.sriver on 4/18/17.
 *
 * This fields acts as place holder and
 * is nearly identical to LL2P CRC so
 * we are extending that class
 */

public class LL3PChecksum extends CRC {

    // -- Fields
    // --------------------------------------------------------------

    /**
     * Constructor
     * @param crcValue String
     */
    public LL3PChecksum(String crcValue) {
        super(crcValue);
    }

    /**
     * Different Explanation
     * @return Sgtring
     */
    @Override
    public String explainSelf() {
        return String.format("LL3P Checksum: " + toAsciiString());
    }
}
