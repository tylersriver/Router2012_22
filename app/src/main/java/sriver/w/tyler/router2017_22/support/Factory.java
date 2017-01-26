package sriver.w.tyler.router2017_22.support;

import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram_fields.DatagramHeaderField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL2PAddressField;

/**
 * Created by tyler.w.sriver on 1/26/17.
 *
 * This factory class builds datagrams
 */
public class Factory {
    private static Factory ourInstance = new Factory();

    public static Factory getInstance() {
        return ourInstance;
    }

    private Factory() {
    }

    public DatagramHeaderField getDatagramHeaderField(int FieldValue, String contents){
        // TODO: 1/26/17 Implement

        DatagramHeaderField dataGramHeader;
        if(FieldValue == Constants.LL2P_SOURCE_ADDRESS){
            dataGramHeader = new LL2PAddressField(contents, true);
        } else if(FieldValue == Constants.LL2P_DEST_ADDRESS){

        }
    }
}
