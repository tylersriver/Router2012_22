package sriver.w.tyler.router2017_22.networks.datagram;

import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram_fields.TextPayload;
import sriver.w.tyler.router2017_22.support.Factory;

/**
 * Created by tyler.w.sriver on 2/2/17.
 *
 * Basic datagram with text
 */
public class TextDatagram implements Datagram {
    // -- Fields
    // --------------------------------------------------------------
    public TextPayload payload;

    // -- Methods
    // --------------------------------------------------------------
    public TextDatagram(String contents){
        payload = new TextPayload(contents);
//        payload = (TextPayload) Factory.getInstance().createPayload(Constants.LL2P_TYPE_IS_TEXT, contents);
    }

    @Override
    public String toHexString() {
        return payload.toHexString();
    }

    @Override
    public String toString() {
        return payload.toAsciiString();
    }

    @Override
    public String toProtocolExplanationString() {
        return "Text Datagram: " + payload.toAsciiString();
    }

    @Override
    public String toSummaryString() {
        return "Text datagram with text of: " + payload.toAsciiString();
    }
}
