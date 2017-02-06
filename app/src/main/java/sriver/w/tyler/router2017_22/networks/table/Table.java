package sriver.w.tyler.router2017_22.networks.table;

import android.app.ActionBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

import sriver.w.tyler.router2017_22.networks.tablerecord.TableRecord;
import sriver.w.tyler.router2017_22.support.LabException;

/**
 * Created by tyler.w.sriver on 2/5/17.
 */
public class Table extends Observable implements TableInterface {

    // -- Fields
    // --------------------------------------------------------------
    protected  ArrayList<TableRecord> table;

    // -- Methods
    // --------------------------------------------------------------


    // -- Interface Methods
    @Override
    public List<TableRecord> getTableAsArrayList() {
        return table;
    }

    @Override
    public TableRecord addItem(TableRecord record) {
        table.add(record);
        return record;
    }

    @Override
    public TableRecord getItem(TableRecord record) throws LabException {
        // TODO: 2/5/17 will this throw the exception if doesn't exist?
        return table.get(table.indexOf(record));
    }


    @Override
    public TableRecord getItem(Integer key) throws LabException {
        for (TableRecord record : table) {
            if(Objects.equals(record.getKey(), key)) {
                return record;
            }
        }
    }

    @Override
    public TableRecord removeItem(Integer key) {
        // Search for matching record
        for (TableRecord record : table) {
            if(Objects.equals(record.getKey(), key)){
                table.remove(table.indexOf(record));
                return record;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        table.clear();
    }
}
