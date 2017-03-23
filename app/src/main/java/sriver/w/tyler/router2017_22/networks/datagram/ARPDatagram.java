package sriver.w.tyler.router2017_22.networks.datagram;

import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PAddressField;
import sriver.w.tyler.router2017_22.support.Factory;

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
        this.ll3paddress = (LL3PAddressField) Factory.getInstance().getDatagramHeaderField(Constants.LL3P_SOURCE_ADDRESS, ll3paddress);
    }

    /**
     * Return the transmission string
     * @return String
     */
    public String toTransmissionString(){
        return ll3paddress.toTransmissionString();
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
     * Get the Ascii Stringa
     * @return String
     */
    @Override
    public String toProtocolExplanationString() {
        return "LL3P Frame: " + toHexString();
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
