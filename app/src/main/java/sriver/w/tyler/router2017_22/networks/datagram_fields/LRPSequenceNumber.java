package sriver.w.tyler.router2017_22.networks.datagram_fields;

import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler.w.sriver on 3/25/17.
 *
 * This contains a unique Sequence number for this routing update.
 * The field is only 4 bits long so sequence numbers wrap from 15 to 0
 */
public class LRPSequenceNumber implements DatagramHeaderField {

    // -- Fields
    // --------------------------------------------------------------
    private Integer sequenceNumber;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Set the sequence number
     * @param seqeunceNumber String
     */
    public LRPSequenceNumber(String seqeunceNumber){
        this.sequenceNumber = Integer.valueOf(seqeunceNumber, 16);
    }

    /**
     * Return hex string of sequence number
     * @return String
     */
    @Override
    public String toHexString() {
        return Integer.toHexString(sequenceNumber);
    }

    /**
     * Explain the sequence number
     * @return String
     */
    @Override
    public String explainSelf() {
        return "Sequence Number: " + Integer.toHexString(sequenceNumber);
    }

    /**
     * Return ASCII string of sequence number
     * @return String
     */
    @Override
    public String toAsciiString() {
        return Utilities.intToAscii(sequenceNumber);
    }
}
