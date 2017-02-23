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
     * Remvoe expired records and return list of removed records
     * @param maxAgeAllowed Integer
     * @return ArrayList
     */
    public ArrayList<TableRecord> expireRecords(Integer maxAgeAllowed){
        ArrayList<TableRecord> removedRecords = new ArrayList<TableRecord>();
        for(TableRecord record : table){
            if(record.getAgeInSec() > maxAgeAllowed){
                removedRecords.add(record);
                table.remove(record);
            }
        }
        return removedRecords;
    }

    public void Touch(Integer key) throws LabException {
        TableRecordClass record = (TableRecordClass) this.getItem(key);
        record.updateTime();
    }
}
