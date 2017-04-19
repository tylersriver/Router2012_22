package sriver.w.tyler.router2017_22.networks.datagram;

import java.util.Objects;

import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram_fields.DatagramPayloadField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PAddressField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PChecksum;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PIdentifierField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PTTLField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PTypeField;

/**
 * Created by tyler.w.sriver on 4/18/17.
 *
 * TODO: Describe
 */
public class LL3PDatagram implements Datagram {

    // -- Fields
    // --------------------------------------------------------------
    private LL3PAddressField sourceAddress;
    private LL3PAddressField destinationAddress;
    private LL3PTTLField ttl;
    private LL3PIdentifierField identifier;
    private LL3PTypeField type;
    private LL3PChecksum crc;
    private DatagramPayloadField payload;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Base Constructor with multiple arguments
     * @param sourceAddress LL3PAddressField
     * @param destinationAddress LL3PAddressField
     * @param ttl LL3PTTLField
     * @param identifier LL3PIdentifierField
     * @param type LL3PTypeField
     * @param crc LL3PChecksum
     */
    public LL3PDatagram (LL3PAddressField sourceAddress, LL3PAddressField destinationAddress,
                         LL3PTTLField ttl, LL3PIdentifierField identifier,
                         LL3PTypeField type, LL3PChecksum crc)
    {
        this.sourceAddress = sourceAddress;
        this.destinationAddress = destinationAddress;
        this.ttl = ttl;
        this.identifier = identifier;
        this.type = type;
        this.crc = crc;
    }

    /**
     * Constructor OVerload that takes byte array
     * @param frameString byte[]
     */
    public LL3PDatagram(byte[] frameString){

        // -- Put array into string
        String bytes = new String(frameString);

        // -- Get Field Substrings
        String sourceString  = bytes.substring(Constants.SOURCE_LL3P_OFFSET, Constants.SOURCE_LL3P_OFFSET + Constants.LL3P_ADDRESS_FIELD_LENGTH * 2);
        String destString    = bytes.substring(Constants.DEST_LL3P_OFFSET, Constants.DEST_LL3P_OFFSET + Constants.LL3P_ADDRESS_FIELD_LENGTH * 2);
        String ttlString     = bytes.substring(Constants.LL3P_TTL_OFFSET, Constants.LL3P_TTL_OFFSET + Constants.LL3P_TTL_LENGTH * 2);
        String idString      = bytes.substring(Constants.LL3P_IDENTIFIER_OFFSET, Constants.LL3P_IDENTIFIER_OFFSET + Constants.LL3P_IDENTIFIER_LENGTH * 2);
        String typeString    = bytes.substring(Constants.LL3P_TYPE_OFFSET, Constants.LL3P_TYPE_OFFSET + Constants.LL3P_TYPE_LENGTH * 2);
        String payloadString = bytes.substring(Constants.LL3P_PAYLOAD_OFFSET, bytes.length() - Constants.LL3P_CRC_LENGTH * 2);
        String crcString     = bytes.substring(bytes.length() - Constants.LL3P_CRC_LENGTH * 2, bytes.length());

        // -- Instantiate Fields
        sourceAddress = new LL3PAddressField(sourceString, true);
        destinationAddress = new LL3PAddressField(destString, false);
        ttl = new LL3PTTLField(ttlString);
        identifier = new LL3PIdentifierField(idString);
        type = new LL3PTypeField(typeString);
        payload = new DatagramPayloadField(Constants.LL2P_TYPE_IS_TEXT, payloadString);
        crc = new LL3PChecksum(crcString);
    }

    // -- Getter Methods
    // --------------------------------------------------------------

    /**
     * Get Source Address
     * @return LL3PAddressField
     */
    public LL3PAddressField getSourceAddress() {
        return sourceAddress;
    }

    /**
     * Get Destination Address
     * @return LL3PAddressField
     */
    public LL3PAddressField getDestinationAddress() {
        return destinationAddress;
    }

    /**
     * Get Time To Live
     * @return LL3PTTLField
     */
    public LL3PTTLField getTtl() {
        return ttl;
    }

    /**
     * Get Identifier
     * @return LL3PIdentifierField
     */
    public LL3PIdentifierField getIdentifier() {
        return identifier;
    }

    /**
     * Get Type
     * @return LL3PTypeField
     */
    public LL3PTypeField getType() {
        return type;
    }

    /**
     * Get CRC
     * @return LL3PChecksum
     */
    public LL3PChecksum getCrc() {
        return crc;
    }


    // -- Interface Methods
    // --------------------------------------------------------------

    /**
     * Return datagram as hex string
     * @return String
     */
    @Override
    public String toHexString() {
        return destinationAddress.toHexString() +
                sourceAddress.toHexString() +
                type.toHexString() +
                payload.toHexString() +
                crc.toHexString();
    }

    /**
     * Return datagram explanation
     * @return String
     */
    @Override
    public String toProtocolExplanationString() {
        return "LL3P Frame: " + "\n" +
                "Destination Address: " + destinationAddress.toString() + "\n" +
                "Source Address: " + sourceAddress.toString() + "\n" +
                "Type: " + type.toString() + "\n" +
                "Payload: " + payload.toString() + "\n" +
                "CRC: " + crc.toString();
    }

    /**
     * Summarize datagram
     * @return String
     */
    @Override
    public String toSummaryString() {
        return "LL2P frame: " + destinationAddress.toString() +
                " | " + sourceAddress.toString() +
                " | " + payload.explainSelf();
    }

    /**
     * To String Override
     * @return String
     */
    @Override
    public String toString() {
        return  destinationAddress.toString() +
                sourceAddress.toString() +
                type.toString() +
                payload.toString() +
                crc.toString();
    }

    // -- Other Methods
    // --------------------------------------------------------------

    /**
     * Reutnr datagram as Ascii String
     * @return String
     */
    public String toAsciiString() {
        return destinationAddress.toAsciiString() +
                sourceAddress.toAsciiString() +
                type.toAsciiString() +
                payload.toAsciiString() +
                crc.toAsciiString();
    }
}
