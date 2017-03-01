package sriver.w.tyler.router2017_22.networks.datagram;

import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PAddressField;

/**
 * Created by tyler on 2/28/2017.
 *
 * Class to describe the ARPDatagram
 */
public class ARPDatagram implements Datagram {

    // -- Fields
    // --------------------------------------------------------------
    private LL3PAddressField ll3paddress;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Constructor
     * @param ll3paddress String
     */
    public ARPDatagram(String ll3paddress){
        this.ll3paddress = new LL3PAddressField(ll3paddress, true);
        // TODO: 3/1/2017 need factory builder
    }

    /**
     * Get the hex string for the address
     * @return String
     */
    @Override
    public String toHexString() {
        return ll3paddress.toHexString();
    }

    /**
     * Get the Ascii String
     * @return String
     */
    @Override
    public String toProtocolExplanationString() {
        return ll3paddress.toAsciiString();
    }

    /**
     * Get the explanation string
     * @return String
     */
    @Override
    public String toSummaryString() {
        return ll3paddress.explainSelf();
    }
}
