package sriver.w.tyler.router2017_22.networks.tablerecord;

/**
 * Created by tyler.w.sriver on 2/23/17.
 *
 * Class to hold records for the ARP table
 */
public class ARPRecord extends TableRecordClass {

    // -- Fields
    // --------------------------------------------------------------
    private Integer ll2pAddress;
    private Integer ll3pAddress;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Constructor
     * @param ll2pAddress Integer
     * @param ll3pAddress Integer
     */
    public ARPRecord(Integer ll2pAddress, Integer ll3pAddress){
        super();
        this.ll2pAddress = ll2pAddress;
        this.ll3pAddress = ll3pAddress;
    }

    /**
     * Empty Constructor
     */
    public ARPRecord(){
        this.ll2pAddress = 0;
        this.ll3pAddress = 0;
    }

    /**
     * Get the age of the record
     * @return Integer
     */
    @Override
    public Integer getAgeInSec() {
        return super.getAgeInSec();
    }

    /**
     * Get the key for the record
     * @return Integer
     */
    @Override
    public Integer getKey() {
        return ll2pAddress;
    }

    /**
     * Compare the records
     * @param tableRecord TableRecord
     * @return int
     */
    @Override
    public int compareTo(TableRecord tableRecord) {
        return super.compareTo(tableRecord);
    }

    /**
     * Update the time of the record
     */
    @Override
    public void updateTime() {
        super.updateTime();
    }

    /**
     * Return the LL2PAddress
     * @return Integer
     */
    public Integer getLl2pAddress() {
        return ll2pAddress;
    }

    /**
     * Set the LL2PAddress
     * @param ll2pAddress Integer
     */
    public void setLl2pAddress(Integer ll2pAddress) {
        this.ll2pAddress = ll2pAddress;
    }

    /**
     * Get the LL3P address
     * @return Integer
     */
    public Integer getLl3pAddress() {
        return ll3pAddress;
    }

    /**
     * Set the LL3P address
     * @param ll3pAddress Integer
     */
    public void setLl3pAddress(Integer ll3pAddress) {
        this.ll3pAddress = ll3pAddress;
    }

    /**
     * To String Override
     * @return String
     */
    public String toString(){
        return "LL2P: " + ll2pAddress + " | " + "LL3P: " + ll3pAddress + " | " + "Age: " + getAgeInSec().toString();
    }
}

