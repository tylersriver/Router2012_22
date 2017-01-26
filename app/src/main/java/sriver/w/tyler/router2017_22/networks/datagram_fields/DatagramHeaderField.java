package sriver.w.tyler.router2017_22.networks.datagram_fields;

/**
 * Created by tyler.w.sriver on 1/24/17.
 *
 * This class is the generic interface
 * for Datagram's used in the application
 */
public interface DatagramHeaderField {

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Appropriate display string of field contents
     * @return String
     */
    String toString();

    /**
     * Return contents as hex
     * @return String
     */
    String toHexString();

    /**
     * Return string formatted to display
     * content and meaning of field
     * @return String
     */
    String explainSelf();

    /**
     * Return ASCII of hex conversion
     * @return String
     */
    String toAsciiString();
}
