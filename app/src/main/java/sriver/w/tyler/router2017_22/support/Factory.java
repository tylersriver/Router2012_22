package sriver.w.tyler.router2017_22.support;

import android.provider.Contacts;
import android.widget.Switch;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.util.Map;

import sriver.w.tyler.router2017_22.UI.UIManager;
import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram.Datagram;
import sriver.w.tyler.router2017_22.networks.datagram.TextDatagram;
import sriver.w.tyler.router2017_22.networks.datagram_fields.CRC;
import sriver.w.tyler.router2017_22.networks.datagram_fields.DatagramHeaderField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.DatagramPayloadField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL2PAddressField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL2PTypeField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PAddressField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LRPRouteCount;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LRPSequenceNumber;
import sriver.w.tyler.router2017_22.networks.tablerecord.AdjacencyRecord;
import sriver.w.tyler.router2017_22.networks.tablerecord.TableRecord;

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

    /**
     * This function returns the requested header field
     * @param FieldValue int
     * @param contents String
     * @return requested field
     */
    public DatagramHeaderField getDatagramHeaderField(int FieldValue, String contents){
        if (FieldValue == Constants.LL2P_SOURCE_ADDRESS)   return new LL2PAddressField(contents, true);
        if (FieldValue == Constants.LL2P_DEST_ADDRESS)     return new LL2PAddressField(contents, false);
        if (FieldValue == Constants.LL2P_TYPE_FIELD)       return new LL2PTypeField(contents);
        if (checkIfPayload(FieldValue))                    return new DatagramPayloadField(FieldValue, contents);
        if (FieldValue == Constants.LL2P_CRC_FIELD)        return new CRC(contents);
        if (FieldValue == Constants.LL3P_SOURCE_ADDRESS)   return new LL3PAddressField(contents, true);
        if (FieldValue == Constants.LL3P_DEST_ADDRESS)     return new LL3PAddressField(contents, false);
        if (FieldValue == Constants.LRP_SEQUENCE_NUMBER)   return new LRPSequenceNumber(contents);
        if (FieldValue == Constants.LRP_ROUTE_COUNT)       return new LRPRouteCount(contents);
        return null;
    }

    /**
     * Check if the type is one of the payloads
     * @param FieldValue int
     * @return boolean
     */
    private boolean checkIfPayload(int FieldValue){
        return FieldValue == Constants.LL2P_TYPE_IS_LL3P ||
                FieldValue == Constants.LL2P_TYPE_IS_RESERVED ||
                FieldValue == Constants.LL2P_TYPE_IS_LRP ||
                FieldValue == Constants.LL2P_TYPE_IS_ECHO_REQUEST ||
                FieldValue == Constants.LL2P_TYPE_IS_ECHO_REPLY ||
                FieldValue == Constants.LL2P_TYPE_IS_ARP_REQUEST ||
                FieldValue == Constants.LL2P_TYPE_IS_ARP_REPLY ||
                FieldValue == Constants.LL2P_TYPE_IS_TEXT;
    }

    /**
     * Create a payload object based on given FieldValue
     * @param FieldValue int
     * @param Contents String
     * @return datagram
     */
    public Datagram createPayload(int FieldValue, String Contents){
        switch (FieldValue){
            case Constants.LL2P_TYPE_IS_RESERVED:
            case Constants.LL2P_TYPE_IS_LRP:
            case Constants.LL2P_TYPE_IS_LL3P:
            case Constants.LL2P_TYPE_IS_ECHO_REQUEST:
            case Constants.LL2P_TYPE_IS_ECHO_REPLY:
            case Constants.LL2P_TYPE_IS_ARP_REQUEST:
            case Constants.LL2P_TYPE_IS_ARP_REPLY:
            case Constants.LL2P_TYPE_IS_TEXT:
                return new TextDatagram(Contents);
            default:
                return null;
        }
    }

    public void displayMsg() {
        UIManager.getInstance().raiseToast("This is the factory");
    }


    /**
     * Generate different table records
     * @param recordType int
     * @param params Object[]
     * @return TableRecord
     */
    public TableRecord getTableRecord(int recordType, Object[] params){
        switch (recordType){
            case Constants.ADJACENCY_TABLE_RECORD:
                return new AdjacencyRecord(params);
            case Constants.ARP_TABLE_RECORD:
                return null;
            case Constants.ROUTING_TABLE_RECORD:
                return null;
            default:
                return null;
        }
    }
}
