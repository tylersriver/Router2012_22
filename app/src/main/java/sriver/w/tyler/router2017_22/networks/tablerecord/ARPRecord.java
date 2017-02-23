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
    public ARPRecord(Integer ll2pAddress, Integer ll3pAddress){
        super();
        this.ll2pAddress = ll2pAddress;
        this.ll3pAddress = ll3pAddress;
    }

    public ARPRecord(){
        this.ll2pAddress = 0;
        this.ll3pAddress = 0;
    }

    @Override
    public Integer getAgeInSec() {
        return super.getAgeInSec();
    }

    @Override
    public Integer getKey() {
        return ll2pAddress;
    }

    @Override
    public int compareTo(TableRecord tableRecord) {
        return super.compareTo(tableRecord);
    }

    @Override
    public void updateTime() {
        super.updateTime();
    }

    public Integer getLl2pAddress() {
        return ll2pAddress;
    }

    public void setLl2pAddress(Integer ll2pAddress) {
        this.ll2pAddress = ll2pAddress;
    }

    public Integer getLl3pAddress() {
        return ll3pAddress;
    }

    public void setLl3pAddress(Integer ll3pAddress) {
        this.ll3pAddress = ll3pAddress;
    }

    public String toString(){
        return "LL2P: " + ll2pAddress + " | " + "LL3P: " + ll3pAddress;
    }
}

