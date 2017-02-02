package sriver.w.tyler.router2017_22.networks.datagram;

import java.util.Arrays;
import java.util.concurrent.ConcurrentNavigableMap;

import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram_fields.CRC;
import sriver.w.tyler.router2017_22.networks.datagram_fields.DatagramHeaderField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.DatagramPayloadField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL2PAddressField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL2PTypeField;
import sriver.w.tyler.router2017_22.support.Factory;

/**
 * Created by tyler.w.sriver on 1/26/17.
 *
 * This class is the full structure for
 * an LL2P frame
 */
public class LL2PFrame implements Datagram {

    // -- Fields
    // --------------------------------------------------------------
    private LL2PAddressField destinationAddress;
    private LL2PAddressField sourceAddress;
    private LL2PTypeField type;
    private DatagramPayloadField payload;
    private CRC crc;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Constructor with all fields
     * @param destinationAddress LL2PAddressField
     * @param sourceAddress LL2PAddressField
     * @param type LL2PTypeField
     * @param payload DatagramPayloadField
     * @param crc CRC
     */
    public LL2PFrame(LL2PAddressField destinationAddress, LL2PAddressField sourceAddress,
                     LL2PTypeField type, DatagramPayloadField payload, CRC crc ){
        this.destinationAddress = destinationAddress;
        this.sourceAddress = sourceAddress;
        this.type = type;
        this.payload = payload;
        this.crc = crc;
    }

    /**
     * Constructor with byte array for frame
     * @param byteArray byte[]
     */
    public LL2PFrame(byte[] byteArray){
        // -- Put array into string
        String bytes = new String(byteArray);

        // -- Get Substrings for fields
        String destAddr =  bytes.substring(Constants.LL2P_DEST_ADDRESS_OFFSET, Constants.LL2P_ADDRESS_LENGTH*2);
        String srcAddr = bytes.substring(Constants.LL2P_SOURCE_ADDRESS_OFFSET, Constants.LL2P_SOURCE_ADDRESS_OFFSET + Constants.LL2P_ADDRESS_LENGTH*2);
        String type = bytes.substring(Constants.LL2P_TYPE_FIELD_OFFSET, Constants.LL2P_TYPE_FIELD_OFFSET + Constants.LL2P_TYPE_FIELD_LENGTH*2);
        String payload = bytes.substring(Constants.LL2P_PAYLOAD_OFFSET, bytes.length() - Constants.LL2P_CRC_FIELD_LENGTH*2-1);
        String crc = bytes.substring(bytes.length() - Constants.LL2P_CRC_FIELD_LENGTH*2, bytes.length());

        // -- Instantiate Fields
        Factory factory = Factory.getInstance();
        this.destinationAddress = (LL2PAddressField) factory.getDatagramHeaderField(Constants.LL2P_DEST_ADDRESS, destAddr);
        this.sourceAddress = (LL2PAddressField) factory.getDatagramHeaderField(Constants.LL2P_DEST_ADDRESS, srcAddr);
        this.type = (LL2PTypeField) factory.getDatagramHeaderField(Constants.LL2P_TYPE_FIELD, type);
        this.payload = (DatagramPayloadField) factory.getDatagramHeaderField(Integer.valueOf(this.type.toString(), 16), payload);
        this.crc = (CRC) factory.getDatagramHeaderField(Constants.LL2P_CRC_FIELD, crc);
    }

    // -- Getters
    // --------------------------------------------------------------

    /**
     * Get the destAddress
     * @return LL2PAddressField
     */
    public LL2PAddressField getDestinationAddress() {
        return destinationAddress;
    }

    /**
     * get the source address
     * @return LL2PAddressField
     */
    public LL2PAddressField getSourceAddress() {
        return sourceAddress;
    }

    /**
     * get the type
     * @return LL2PTypeField
     */
    public LL2PTypeField getType() {
        return type;
    }

    /**
     * get the payload
     * @return DatagramPayloadField
     */
    public DatagramPayloadField getPayload() {
        return payload;
    }

    /**
     * get the crc
     * @return CRC
     */
    public CRC getCrc() {
        return crc;
    }

    // -- Interface Methods
    // --------------------------------------------------------------
    @Override
    public String toString() {
        return  destinationAddress.toString() +
                sourceAddress.toString() +
                type.toString() +
                payload.toString() +
                crc.toString();
    }

    @Override
    public String toHexString() {
        return  destinationAddress.toHexString() +
                sourceAddress.toHexString() +
                type.toHexString() +
                payload.toHexString() +
                crc.toHexString();
    }

    @Override
    public String toProtocolExplanationString() {
        return "LL2P Frame: " + "\n" +
                "Destination Address: " + destinationAddress.toString() + "\n" +
                "Source Address: " + sourceAddress.toString() + "\n" +
                "Type: " + type.toString() + "\n" +
                "Payload: " + payload.toString() + "\n" +
                "CRC: " + crc.toString();
    }

    @Override
    public String toSummaryString() {
        return "LL2P frame: DestAddr: " + destinationAddress.toString() +
                "SrcAddr: " + sourceAddress.toString() +
                "Payload: " + payload.explainSelf();
    }
}
