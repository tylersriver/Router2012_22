package sriver.w.tyler.router2017_22.networks.datagram;

/**
 * Created by tyler.w.sriver on 1/26/17.
 *
 * This is our datagram interface class
 */
public interface Datagram {

    /**
     * Appropriately display contents of the field
     * @return string
     */
    String toString();

    /**
     * Return string of the conents in hex
     * @return string
     */
    String toHexString();

    /**
     * Return a string that is a full explanation
     * of the datagram and all it's fields
     * @return string
     */
    String toProtocolExplanationString();

    /**
     * Returns string that can be displayed in one line
     * @return string
     */
    String toSummaryString();
}
