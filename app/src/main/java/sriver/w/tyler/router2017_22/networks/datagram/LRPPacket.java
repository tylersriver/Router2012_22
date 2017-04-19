package sriver.w.tyler.router2017_22.networks.datagram;

import java.util.ArrayList;
import java.util.List;

import sriver.w.tyler.router2017_22.networks.Constants;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LL3PAddressField;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LRPRouteCount;
import sriver.w.tyler.router2017_22.networks.datagram_fields.LRPSequenceNumber;
import sriver.w.tyler.router2017_22.networks.datagram_fields.NetworkDistancePair;
import sriver.w.tyler.router2017_22.support.Factory;
import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler.w.sriver on 3/25/17.
 *
 * This class defines the structure for a LRP packet
 * interacting with it
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
        String sequenceNumber = bytes.substring(Constants.SEQUENCE_NUMBER_OFFSET, Constants.SEQUENCE_NUMBER_OFFSET+1);
        String count = bytes.substring(Constants.COUNT_OFFSET, Constants.COUNT_OFFSET+1);
        String netDistList = bytes.substring(Constants.FIRST_NETWORK_OFFSET, bytes.length());

        // -- Store fields
        this.sourceLL3P = (LL3PAddressField) Factory.getInstance().getDatagramHeaderField(Constants.LL3P_SOURCE_ADDRESS, sourceLL3P);
        this.sequenceNumber = (LRPSequenceNumber) Factory.getInstance().getDatagramHeaderField(Constants.LRP_SEQUENCE_NUMBER, sequenceNumber);
        this.count = (LRPRouteCount) Factory.getInstance().getDatagramHeaderField(Constants.LRP_ROUTE_COUNT, count);

        // -- Store Routes in List
        routes = new ArrayList<>();
        for (int i = 0; i < netDistList.length(); i+=4){
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
        this.sourceLL3P = new LL3PAddressField(Utilities.padHexString(Integer.toHexString(ll3p),2), true);
        this.sequenceNumber = new LRPSequenceNumber(Integer.toHexString(sequenceNumber));
        this.count = new LRPRouteCount(Integer.toHexString(count));
        this.routes = networkDistancePairList;
    }

    /**
     * Get byte array of ASCII characters ordered for transmission
     * @return byte[]
     */
    public byte[] getBytes(){
        return toHexString().getBytes();
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
        // Build Hex String for the routes
        StringBuilder routesHexString = new StringBuilder();
        for (NetworkDistancePair route: routes) {
            routesHexString.append(route.toHexString());
        }

        // Return complete hex string
        return sourceLL3P.toTransmissionString() +
                sequenceNumber.toHexString() +
                count.toHexString() +
                "00" +
                routesHexString.toString();
    }

    @Override
    public String toProtocolExplanationString() {

        // Build string of the routes
        StringBuilder routesString = new StringBuilder();
        for (NetworkDistancePair route:
             routes) {
            routesString.append(route.explainSelf());
            routesString.append(" ");
        }

        // Return packet explanation
        return "LRP Packet: \n" +
                "LL3P Address: " + sourceLL3P.toString() + "\n" +
                "Sequence Number: " + sequenceNumber.toString() + "\n" +
                "Route Count: " + count.toString() + "\n" +
                "Routes: " + routesString;
    }

    @Override
    public String toSummaryString() {
        return "LRP Packet: " + sourceLL3P.toString() +
                " | " + count.toString() + " routes";
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
