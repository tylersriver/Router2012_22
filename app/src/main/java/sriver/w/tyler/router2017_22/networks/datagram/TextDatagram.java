package sriver.w.tyler.router2017_22.networks.datagram;

/**
 * Created by tyler.w.sriver on 2/2/17.
 *
 * Basic datagram with text
 */
public class TextDatagram implements Datagram {
    // -- Fields
    // --------------------------------------------------------------
    public String text;

    // -- Methods
    // --------------------------------------------------------------
    public TextDatagram(String contents){
        text = contents;
    }

    @Override
    public String toHexString() {
        return Integer.toHexString(Integer.valueOf(this.text, 16));
    }

    @Override
    public String toString() {
        return text;
    }

    @Override
    public String toProtocolExplanationString() {
        return "Text Datagram: " + text;
    }

    @Override
    public String toSummaryString() {
        return "Text datagram with text of: " + text;
    }
}
