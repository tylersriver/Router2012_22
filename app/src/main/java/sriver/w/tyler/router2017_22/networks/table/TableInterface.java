package sriver.w.tyler.router2017_22.networks.table;

import java.util.List;

import sriver.w.tyler.router2017_22.networks.tablerecord.TableRecord;
import sriver.w.tyler.router2017_22.support.LabException;

/**
 * Created by tyler.w.sriver on 2/5/17.
 *
 * This interface shows the common methods for tables
 */
public interface TableInterface {

    /**
     * This method will return the table's complete list of records as a List of TableRecords
     * @return List of TableRecords
     */
    List<TableRecord> getTableAsArrayList();

    /**
     * This method adds the TableRecord to the List
     * and returns the record back
     * @return record
     */
    TableRecord addItem(TableRecord record);

    /**
     * Return the matching the specified TableRecord
     * @param record TableRecord
     * @return TableRecord
     * @throws LabException
     */
    TableRecord getItem(TableRecord record) throws LabException;

    /**
     * Remove the TableRecord with the given key
     * @param key Integer
     * @return return removed record
     */
    TableRecord removeItem(Integer key);

    /**
     * Return record with matching key
     * @param key Integer
     * @return TableRecord
     * @throws LabException
     */
    TableRecord getItem(Integer key) throws LabException;

    /**
     * Clear the table
     */
    void clear();
}
