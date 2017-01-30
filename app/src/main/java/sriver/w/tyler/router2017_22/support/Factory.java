package sriver.w.tyler.router2017_22.support;

import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram_fields.CRC;
import sriver.w.tyler.router2017_22.networks.datagram_fields.DatagramHeaderField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.DatagramPayloadField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL2PAddressField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL2PTypeField;

/**
 * Created by tyler.w.sriver on 1/26/17.
 *
 * This factory class builds datagrams
 */
public class Factory {

    // -- Fields
    // --------------------------------------------------------------
    private static Factory ourInstance = new Factory();

    // -- Methods
    // --------------------------------------------------------------
    public static Factory getInstance() {
        return ourInstance;
    }

    private Factory() {
    }

    public DatagramHeaderField getDatagramHeaderField(int FieldValue, String contents){
        // TODO: 1/26/17 Implement

        if(FieldValue == Constants.LL2P_SOURCE_ADDRESS){
            return new LL2PAddressField(contents, true);
        } else if(FieldValue == Constants.LL2P_DEST_ADDRESS){
            return new LL2PAddressField(contents, false);
        } else if (FieldValue == Constants.LL2P_TYPE_FIELD){
            return new LL2PTypeField(contents);
        } else if (FieldValue == Constants.LL2P_PAYLOAD_FIELD){
            // TODO: 1/29/2017
        } else if (FieldValue == Constants.LL2P_CRC_FIELD){
            return new CRC(contents);
        } else{
            return null;
        }
    }
}
