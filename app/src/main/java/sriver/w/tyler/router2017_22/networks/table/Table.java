package sriver.w.tyler.router2017_22.networks.table;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

import sriver.w.tyler.router2017_22.networks.tablerecord.TableRecord;
import sriver.w.tyler.router2017_22.support.LabException;

/**
 * Created by tyler.w.sriver on 2/5/17.
 *
 * This table is used for keeping records of various types
 */
public class Table extends Observable implements TableInterface {

    // -- Fields
    // --------------------------------------------------------------
    protected  ArrayList<TableRecord> table;

    // -- Methods
    // --------------------------------------------------------------
    public Table(){
        table = new ArrayList<TableRecord>();
    }

    // -- Interface Methods
    @Override
    public List<TableRecord> getTableAsArrayList() {
        return table;
    }

    @Override
    public TableRecord addItem(TableRecord record) {
        setChanged();
        notifyObservers();
        table.add(record);
        return record;
    }

    @Override
    public TableRecord getItem(TableRecord record) throws LabException {
        for (TableRecord item: table) {
            if(item.getKey().equals(record.getKey())) {
                return item;
            }
        }
        throw new LabException("Record not found");
    }

    @Override
    public TableRecord getItem(Integer key) throws LabException {
        for (TableRecord record : table) {
            if(Objects.equals(record.getKey(), key)) {
                return record;
            }
        } throw new LabException("Record not found");
    }

    @Override
    public TableRecord removeItem(Integer key) {
        // Search for matching record
        for (TableRecord record : table) {
            if(key.equals(record.getKey())){
                table.remove(table.indexOf(record));
                return record;
            }
        } return null;
    }

    @Override
    public void clear() {
        table.clear();
    }
}
