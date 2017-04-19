package sriver.w.tyler.router2017_22.networks.datagram_fields;

import sriver.w.tyler.router2017_22.networks.datagram.TextDatagram;
import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler.w.sriver on 2/23/17.
 *
 * Payload for text type
 */
public class TextPayload implements DatagramHeaderField {

    // -- Fields
    // --------------------------------------------------------------
    private String payload;

    // -- Methods
    // --------------------------------------------------------------
    public TextPayload(String text){
        payload = text;
    }

    @Override
    public String toHexString() {
        return payload;
    }

    @Override
    public String explainSelf() {
        return "Text Payload Contents: " + payload;
    }

    @Override
    public String toAsciiString() {
        return payload;
    }
}
