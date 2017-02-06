package sriver.w.tyler.router2017_22.networks.tablerecord;

/**
 * Created by tyler.w.sriver on 2/5/17.
 *
 * This interface is for functions common
 * to records in a table
 */
public interface TableRecord {

    /**
     * Get the key for this record
     * @return Integer
     */
    Integer getKey();

    /**
     * Get the time passed in seconds since the record was
     * last referenced.
     * Note: if no age return 0
     * @return
     */
    Integer getAgeInSec();
}
