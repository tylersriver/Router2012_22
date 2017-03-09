package sriver.w.tyler.router2017_22.networks.tablerecord;

import sriver.w.tyler.router2017_22.support.Utilities;

/**
 * Created by tyler.w.sriver on 2/5/17.
 *
 * Base class for the records in the table
 */
public class TableRecordClass implements TableRecord {

    // -- Fields
    // --------------------------------------------------------------
    private int lastTimeTouched;

    // -- Methods
    // --------------------------------------------------------------

    /**
     * Constructor; updates time
     */
    public TableRecordClass(){
        updateTime();
    }

    /**
     * Update the time of the record
     */
    public void updateTime(){
        lastTimeTouched = Utilities.getTimeinSec();
    }

    /**
     * Compare keys of the table records
     * @param tableRecord TableRecord
     * @return int
     */
    public int compareTo(TableRecord tableRecord){
        return getKey().compareTo(tableRecord.getKey());
    }

    @Override
    public Integer getKey() {
        return null;
    }

    @Override
    public Integer getAgeInSec() {
        return Utilities.getTimeinSec() - lastTimeTouched;
    }
}
