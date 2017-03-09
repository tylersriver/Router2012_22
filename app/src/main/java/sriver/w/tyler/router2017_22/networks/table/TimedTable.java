package sriver.w.tyler.router2017_22.networks.table;

import java.util.ArrayList;

import sriver.w.tyler.router2017_22.networks.tablerecord.TableRecord;
import sriver.w.tyler.router2017_22.networks.tablerecord.TableRecordClass;
import sriver.w.tyler.router2017_22.support.LabException;

/**
 * Created by tyler.w.sriver on 2/23/17.
 *
 * Table for Time stuff
 */
public class TimedTable extends Table {

    // -- Methods
    // --------------------------------------------------------------

    public TimedTable() {
        super();
    }

    /**
     * Remove expired records and return list of removed records
     * @param maxAgeAllowed Integer
     * @return ArrayList
     */
    public ArrayList<TableRecord> expireRecords(Integer maxAgeAllowed){
        ArrayList<TableRecord> removedRecords = new ArrayList<TableRecord>();
        TableRecord record = null;
        do {
            record = removeOneRecord(maxAgeAllowed);
            removedRecords.add(record);
        } while (record != null);
        return removedRecords;
    }

    /**
     * Remove one record in loop
     * @param maxAgeAllowed int
     * @return TableRecord
     */
    private TableRecord removeOneRecord(Integer maxAgeAllowed){
        for(TableRecord record : table){
            if(record.getAgeInSec() > maxAgeAllowed){
                table.remove(record);
                return record;
            }
        }
        return null;
    }

    /**
     * Get the record and update time
     * @param key Integer
     * @throws LabException
     */
    public void Touch(Integer key) throws LabException {
        TableRecordClass record = (TableRecordClass) this.getItem(key);
        record.updateTime();
    }
}
