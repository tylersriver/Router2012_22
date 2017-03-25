package sriver.w.tyler.router2017_22.networks.datagram;

import java.util.List;

import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PAddressField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LRPRouteCount;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LRPSequenceNumber;
import sriver.w.tyler.router2017_22.networks.datagram_fields.NetworkDistancePair;
import sriver.w.tyler.router2017_22.support.Factory;

/**
 * Created by tyler.w.sriver on 3/25/17.
 *
 * TODO: Fill in
 */
public class LRPPacket implements Datagram {

    // -- Fields
    // --------------------------------------------------------------
    private LL3PAddressField sourceLL3P;
    private LRPSequenceNumber sequenceNumber;
    private LRPRouteCount count;
    private List<NetworkDistancePair> routes;

    // -- Methods
    // ==============================================================

    /**
     * Parse byte array and create appropriate fields
     * @param byteArray byte[]
     */
    public LRPPacket(byte[] byteArray){

        // -- Put array into string
        String bytes = new String(byteArray);

        // -- Get substrings for fields
        String sourceLL3P = bytes.substring(Constants.LL3P_SOURCE_ADDRESS_OFFSET, Constants.LL3P_ADDRESS_FIELD_LENGTH*2);
        String sequenceNumber = bytes.substring(Constants.SEQUENCE_NUMBER_OFFSET, (int) Constants.SEQUENCE_NUMBER_LENGTH*2);
        String count = bytes.substring(Constants.COUNT_OFFSET, (int) Constants.COUNT_LENGTH*2);
        String netDistList = bytes.substring(Constants.FIRST_NETWORK_OFFSET, bytes.length());

        // -- Store fields
        this.sourceLL3P = (LL3PAddressField) Factory.getInstance().getDatagramHeaderField(Constants.LL3P_SOURCE_ADDRESS, sourceLL3P);
        this.sequenceNumber = (LRPSequenceNumber) Factory.getInstance().getDatagramHeaderField(Constants.LRP_SEQUENCE_NUMBER, sequenceNumber);
        this.count = (LRPRouteCount) Factory.getInstance().getDatagramHeaderField(Constants.LRP_ROUTE_COUNT, count);

        // -- Store Routes in List
        for (int i = 0; i < this.count.getRouteCount(); i++){
            String pair = netDistList.substring(i, i+4);
            NetworkDistancePair temp = new NetworkDistancePair(pair);
            routes.add(temp);
        }
    }

    /**
     * Fill fields manually
     * @param ll3p Integer
     * @param sequenceNumber Integer
     * @param count Integer
     * @param networkDistancePairList List
     */
    public LRPPacket(Integer ll3p, Integer sequenceNumber,
                     Integer count, List<NetworkDistancePair> networkDistancePairList){
        // -- Set fields
        this.sourceLL3P = new LL3PAddressField(Integer.toHexString(ll3p), true);
        this.sequenceNumber = new LRPSequenceNumber(Integer.toHexString(sequenceNumber));
        this.count = new LRPRouteCount(Integer.toHexString(count));
        this.routes = networkDistancePairList;
    }

    /**
     * Get byte array of ASCII characters ordered for transmission
     * @return byte[]
     */
    public byte[] getBytes(){
        // TODO: How?
    }

    /**
     * Return number of routes
     * @return Integer
     */
    public Integer getRouteCount(){
        return count.getRouteCount();
    }

    // -- Interface Methods
    // --------------------------------------------------------------
    @Override
    public String toHexString() {
        // TODO: 3/25/17 Needs implementation
        return null;
    }

    @Override
    public String toProtocolExplanationString() {
        // TODO: 3/25/17 Needs implementation
        return null;
    }

    @Override
    public String toSummaryString() {
        // TODO: 3/25/17 needs implementation
        return null;
    }

    // -- Getters
    // --------------------------------------------------------------

    /**
     * Return source ll3p
     * @return LL3PAddressField
     */
    public LL3PAddressField getSourceLL3P() {
        return sourceLL3P;
    }

    /**
     * Return sequence number
     * @return LRPSequenceNumber
     */
    public LRPSequenceNumber getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Get the route count
     * @return LRPRouteCount
     */
    public LRPRouteCount getCount() {
        return count;
    }

    /**
     * Get list of routes
     * @return List
     */
    public List<NetworkDistancePair> getRoutes() {
        return routes;
    }
}
