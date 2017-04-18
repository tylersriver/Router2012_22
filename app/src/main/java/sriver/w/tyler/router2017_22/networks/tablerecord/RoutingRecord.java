package sriver.w.tyler.router2017_22.networks.tablerecord;

import sriver.w.tyler.router2017_22.networks.datagram_fields.NetworkDistancePair;
import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler.w.sriver on 4/12/17.
 *
 * TODO: Fill description
 */
public class RoutingRecord extends TableRecordClass {

    // -- Fields
    // --------------------------------------------------------------
    private NetworkDistancePair networkDistancePair;
    private Integer nextHop;
    private Integer key;

    // -- Methods
    // --------------------------------------------------------------

    /**
     *
     * @param networkNumber Integer
     * @param distance distance
     * @param nextHop nextHop
     */
    public RoutingRecord(Integer networkNumber, Integer distance, Integer nextHop){
        super();
        this.networkDistancePair = new NetworkDistancePair(networkNumber, distance);
        this.nextHop = nextHop;
        this.key = networkNumber * 256 * 256 + nextHop;
    }

    /**
     * Get the network number from pair
     * @return Integer
     */
    public Integer getNetworkNumber(){
        return networkDistancePair.getNetwork();
    }

    /**
     * Get the distance from pair
     * @return Integer
     */
    public Integer getDistance(){
        return networkDistancePair.getDistance();
    }

    /**
     * Return the Hop
     * @return Integer
     */
    public Integer getNextHop() {
        return nextHop;
    }

    /**
     * Return the key
     * @return Integer
     */
    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "RR. " + networkDistancePair.explainSelf() +
                " Next hop: " + Utilities.padHexString(Integer.toHexString(nextHop), 2) + " age: " + getAgeInSec();
    }
}
